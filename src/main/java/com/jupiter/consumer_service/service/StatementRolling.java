package com.jupiter.consumer_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.StaticMessageHeaderAccessor;
import org.springframework.integration.acks.AckUtils;
import org.springframework.integration.acks.AcknowledgmentCallback;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
public class StatementRolling {
    @Bean
    public Consumer<Message<String>> consumer() {
        log.info("started compiling data");
        return message -> {
            AcknowledgmentCallback ackCallback = StaticMessageHeaderAccessor.getAcknowledgmentCallback(message);
            try {
                String payload = message.getPayload();
                log.info("test -> {}", message.getPayload());
                log.info("successfully rolled out the statement.");
                AckUtils.accept(ackCallback);
            } catch (Exception e) {
                log.error("Error processing message", e);

                AckUtils.requeue(ackCallback);
            }
        };
    }
}
