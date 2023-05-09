package com.example.websocket.service;

import com.example.websocket.model.PaymentEvent;
import com.example.websocket.model.PaymentSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentEventWebsocketService {

    @Autowired
    SimpMessagingTemplate websocketMessagePublisher;


    static PaymentSummary paymentSummary = new PaymentSummary();

    public PaymentEventWebsocketService(SimpMessagingTemplate websocketMessagePublisher){
        this.websocketMessagePublisher = websocketMessagePublisher;
    }
    public void publishPaymentEventOverWebsocket(PaymentEvent paymentEvent){
        websocketMessagePublisher.convertAndSend("/topic/paymentEvent", paymentEvent);
        this.publishPaymentSummary(paymentEvent);
    }

    public void publishPaymentSummary(PaymentEvent paymentEvent){
        if(paymentEvent.getPaymentType().equals("Credit")){
            paymentSummary.setTotalCredit(paymentSummary.getTotalCredit()+paymentEvent.getAmount());
        }else{
            paymentSummary.setTotalDebit(paymentSummary.getTotalDebit()+paymentEvent.getAmount());
        }
        paymentSummary.setTotalBalance(paymentSummary.getTotalCredit() - paymentSummary.getTotalDebit());
        websocketMessagePublisher.convertAndSend("/topic/paymentSummary", paymentSummary);
    }

}
