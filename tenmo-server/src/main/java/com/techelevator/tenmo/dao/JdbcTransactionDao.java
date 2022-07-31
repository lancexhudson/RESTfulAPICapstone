package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransactionDao implements TransactionDao {

    private JdbcTemplate jdbcTemplate;
    public JdbcTransactionDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Transaction> seeTransactions (int account_id) {
        String sql = "select * from tenmo_transaction where sender_id = ? or receiver_id = ?;";
        List<Transaction> seeTransactions = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, account_id, account_id);
//        Transaction transaction = new Transaction();
        while (rowSet.next()) {
          Transaction transaction = mapRowToTransaction(rowSet);
            seeTransactions.add(transaction);
        }
        return seeTransactions;
    }


    @Override
    public Transaction getTransactionById (int transaction_id) {
        String sql = "select transaction_id, sender_id, receiver_id, amount, status from tenmo_transaction where transaction_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, transaction_id);
        Transaction transaction = new Transaction();
        if (rowSet.next()) {
            transaction = mapRowToTransaction(rowSet);
        }
        return  transaction;
    }


    @Override
    public void sendTransaction(Transaction transaction) {
            String sql = "INSERT INTO tenmo_transaction (sender_id, receiver_id, amount, status) VALUES (?, ?, ?, ?)";
     jdbcTemplate.update(sql, transaction.getSender_id(), transaction.getReceiver_id(), transaction.getAmount(), "Approved");
        }


   private Transaction mapRowToTransaction (SqlRowSet rowSet) {       // is this what we need? should it be SET or GET? we used set methods in Jdbc Account Dao
       Transaction transaction = new Transaction();
       transaction.setTransaction_id(rowSet.getInt("transaction_id"));
        transaction.setSender_id(rowSet.getInt("sender_id"));
        transaction.setReceiver_id(rowSet.getInt("receiver_id"));
        transaction.setAmount(rowSet.getBigDecimal("amount"));
        transaction.setStatus(rowSet.getString("status"));
        return transaction;
   }


}

