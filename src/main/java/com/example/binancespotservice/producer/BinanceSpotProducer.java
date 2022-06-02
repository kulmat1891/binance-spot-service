package com.example.binancespotservice.producer;

import com.binance.connector.client.impl.WebsocketClientImpl;
import com.leansoft.bigqueue.BigQueueImpl;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinanceSpotProducer implements Runnable {

    private static Logger LOGGER = LoggerFactory.getLogger(BinanceSpotProducer.class);

    private int streamId;

    private String symbol;

    private BigQueueImpl bigQueue;

    private WebsocketClientImpl websocketClient;

    public BinanceSpotProducer(String symbol,
                               BigQueueImpl bigQueue,
                               WebsocketClientImpl websocketClient) {
        this.symbol = symbol;
        this.bigQueue = bigQueue;
        this.websocketClient = websocketClient;
    }

    @Override
    public void run() {
        streamId = this.websocketClient.aggTradeStream(symbol, ((event) -> {
            try {
                // add to queue
                bigQueue.enqueue(event.getBytes());

                if (bigQueue.size() % 10 == 0) {
                    LOGGER.info("Queue size: " + bigQueue.size());
                }
            } catch (IOException e) {
                // log and ignore exceptions, continue processing
                LOGGER.error("Can't push events to the queue due to: ", e);
            }
        }));
    }

    public void shutdown() {
        this.websocketClient.closeConnection(streamId);
        try {
            this.bigQueue.close();
        } catch (IOException e) {
            LOGGER.error("Can't close queue connection", e);
        }
    }
}