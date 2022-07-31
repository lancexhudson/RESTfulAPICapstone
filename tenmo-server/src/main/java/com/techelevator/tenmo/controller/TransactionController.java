package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransactionDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.InsufficientFundsException;
import com.techelevator.tenmo.model.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


// accounts/transaction ???
@RequestMapping ("/transaction")
@RestController
public class TransactionController {

    private TransactionDao transactionDao;
    private AccountDao accountDao;

    public TransactionController(TransactionDao transactionDao, AccountDao accountDao) {
        this.transactionDao = transactionDao;
        this.accountDao = accountDao;
    }
// add authenication for user to send money
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public void sendTransaction(@RequestBody Transaction transaction) throws InsufficientFundsException,  AccountNotFoundException {
       if (transaction.getAmount().compareTo(BigDecimal.ZERO) <= 0 ) {
           throw new InsufficientFundsException();
       }
        Account senderAccount = accountDao.getByAccountId(transaction.getSender_id());
        Account receiverAccount = accountDao.getByAccountId(transaction.getReceiver_id());
        if(senderAccount.getBalance().compareTo(transaction.getAmount()) < 0) {
            throw new InsufficientFundsException();
        } //update accounts, not balance
        accountDao.updateBalance(senderAccount,senderAccount.getBalance().subtract(transaction.getAmount()));
        accountDao.updateBalance(receiverAccount,receiverAccount.getBalance().add(transaction.getAmount()));
        transactionDao.sendTransaction(transaction);
    }


    @RequestMapping (path = "{account_id}", method = RequestMethod.GET)
    public List<Transaction> seeTransactions (@PathVariable int account_id) throws AccountNotFoundException {
        return transactionDao.seeTransactions(account_id);
    }

    @RequestMapping (path = "getBy/{transaction_id}", method = RequestMethod.GET)
    public Transaction getTransactionById (@PathVariable int transaction_id) throws AccountNotFoundException {
        return transactionDao.getTransactionById(transaction_id);
    }



    }









