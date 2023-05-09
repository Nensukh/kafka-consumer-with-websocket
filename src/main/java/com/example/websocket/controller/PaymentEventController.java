package com.example.websocket.controller;

import com.example.websocket.model.PaymentEvent;
import com.example.websocket.service.PaymentEventWebsocketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentEventController {



    public PaymentEventController(){
    }

    @GetMapping("/health-check")
    public String processPaymentEvent() throws Exception {
        return "PaymentEvent Kafka consumer is up and running....!";
    }


}
