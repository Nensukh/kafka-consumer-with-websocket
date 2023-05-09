package com.example.websocket.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentSummary {

    private double totalCredit;
    private double totalDebit;
    private double totalBalance;
}
