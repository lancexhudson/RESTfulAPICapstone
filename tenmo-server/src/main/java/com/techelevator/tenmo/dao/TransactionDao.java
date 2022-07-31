package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transaction;

import java.util.List;

public interface TransactionDao  {

    void sendTransaction(Transaction transaction);
    List<Transaction> seeTransactions (int account_id);
    Transaction getTransactionById (int transaction_id);


}
