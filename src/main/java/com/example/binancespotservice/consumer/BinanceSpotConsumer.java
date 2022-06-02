package com.example.binancespotservice.consumer;


import com.example.binancespotservice.dto.SpotEvent;
import com.example.binancespotservice.service.SpotEventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leansoft.bigqueue.BigQueueImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinanceSpotConsumer implements Runnable {

    private static Logger LOGGER = LoggerFactory.getLogger(BinanceSpotConsumer.class);

    private final static long MAX_BATCH_SIZE = 50;

    private final static long BATCH_INTERVAL_TIME_MS = 5000L;

    private BigQueueImpl bigQueue;

    private ObjectMapper objectMapper;

    private SpotEventService eventService;

    public BinanceSpotConsumer(BigQueueImpl bigQueue,
                               ObjectMapper objectMapper,
                               SpotEventService eventService) {
        this.bigQueue = bigQueue;
        this.objectMapper = objectMapper;
        this.eventService = eventService;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (this.bigQueue.size() > 0) {
                    long batchSize = Math.min(this.bigQueue.size(), MAX_BATCH_SIZE);
                    LOGGER.info("Batch size: " + batchSize);

                    List<SpotEvent> events = createBatch(batchSize);

                    this.eventService.saveAll(events);
                    LOGGER.info("Successfully saved entities: " + batchSize);
                } else {
                    LOGGER.info("Queue is empty");
                }
                LOGGER.info("Wait 5(s) for the next batch");
                Thread.sleep(BATCH_INTERVAL_TIME_MS);
            } catch (Exception e) {
                // log and ignore exceptions, continue processing
                LOGGER.error("Can't process events due to: ", e);
            }
        }
    }

    private List<SpotEvent> createBatch(long batchSize) throws IOException {
        List<SpotEvent> eventsBatch = new ArrayList<>();
        for (int i = 0; i < batchSize; i++) {
            byte [] rawEvent = this.bigQueue.dequeue();
            SpotEvent spotEvent = this.objectMapper.readValue(rawEvent, SpotEvent.class);
            eventsBatch.add(spotEvent);
        }
        return eventsBatch;
    }

    public void shutdown() {
        try {
            this.bigQueue.close();
        } catch (IOException e) {
            LOGGER.error("Can't close queue connection", e);
        }
    }
}

