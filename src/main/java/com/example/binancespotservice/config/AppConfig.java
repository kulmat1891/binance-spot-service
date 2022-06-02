package com.example.binancespotservice.config;

import com.binance.connector.client.impl.WebsocketClientImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.leansoft.bigqueue.BigQueueImpl;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public WebsocketClientImpl websocketClient() {
        return new WebsocketClientImpl();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    public BigQueueImpl bigQueue(
            @Value("${bigqueue.directory}") String directory,
            @Value("${bigqueue.name}")String queueName
    ) throws IOException {
        return new BigQueueImpl(directory, queueName);
    }
}
