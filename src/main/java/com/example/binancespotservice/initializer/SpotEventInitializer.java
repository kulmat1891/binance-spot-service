package com.example.binancespotservice.initializer;

import com.binance.connector.client.impl.WebsocketClientImpl;
import com.example.binancespotservice.consumer.BinanceSpotConsumer;
import com.example.binancespotservice.producer.BinanceSpotProducer;
import com.example.binancespotservice.service.SpotEventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leansoft.bigqueue.BigQueueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpotEventInitializer {

    private static Logger LOGGER = LoggerFactory.getLogger(SpotEventInitializer.class);

    private BigQueueImpl bigQueue;

    private ObjectMapper objectMapper;

    private WebsocketClientImpl websocketClient;

    private SpotEventService eventService;

    @Value("${spot.trades}")
    private String symbols;

    @Autowired
    public SpotEventInitializer(BigQueueImpl bigQueue,
                                ObjectMapper objectMapper,
                                WebsocketClientImpl websocketClient,
                                SpotEventService eventService) {
        this.bigQueue = bigQueue;
        this.objectMapper = objectMapper;
        this.websocketClient = websocketClient;
        this.eventService = eventService;
    }

    @PostConstruct
    public void init() {
        // start
        LOGGER.info("Initializing spot producers/consumers for trades=" + symbols);
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        List<BinanceSpotProducer> producers = Arrays.stream(symbols.split(","))
                .map(symbol -> new BinanceSpotProducer(symbol, this.bigQueue, this.websocketClient))
                .collect(Collectors.toList());
        BinanceSpotConsumer consumer = new BinanceSpotConsumer(this.bigQueue, this.objectMapper, this.eventService);

        producers.forEach(executor::execute);
        executor.execute(consumer);

        // stop
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            producers.forEach(BinanceSpotProducer::shutdown);
            consumer.shutdown();
            executor.shutdown();

            boolean terminated = false;
            try {
                terminated = executor.awaitTermination(30, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (!terminated) {
                executor.shutdownNow();
            }
        }));
    }
}
