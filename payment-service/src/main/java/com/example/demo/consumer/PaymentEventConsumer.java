package com.example.demo.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentEventConsumer {

    @KafkaListener(topics = "order.created", groupId = "payment-group")
    public void handleOrderCreated(String message) {

        System.out.println("=== EVENT RECEIVED ===");
        System.out.println(message);
        System.out.println("Processing payment...");
    }
}