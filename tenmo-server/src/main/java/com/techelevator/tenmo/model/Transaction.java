package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transaction {

    private int transaction_id;
    private int sender_id;
    private int receiver_id;
    private BigDecimal amount;
    private String status;

    public Transaction(int sender_id, int receiver_id, BigDecimal amount, String status) {
        this.sender_id =sender_id;
        this.receiver_id = receiver_id;
        this.amount = amount;
        this.status = status;
    }

    public Transaction() {

    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public int getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(int receiver_id) {
        this.receiver_id = receiver_id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
