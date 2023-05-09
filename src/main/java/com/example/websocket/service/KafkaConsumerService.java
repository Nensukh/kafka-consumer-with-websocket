package com.example.websocket.service;


import com.example.websocket.model.PaymentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    PaymentEventWebsocketService paymentEventWebsocketService;
    @KafkaListener(topics = "payment-events", groupId = "sample-group",containerFactory = "kafkaListener")
    public void consume(PaymentEvent paymentEvent){
        paymentEventWebsocketService.publishPaymentEventOverWebsocket(paymentEvent);
        paymentEventWebsocketService.publishPaymentSummary(paymentEvent);
    }
}