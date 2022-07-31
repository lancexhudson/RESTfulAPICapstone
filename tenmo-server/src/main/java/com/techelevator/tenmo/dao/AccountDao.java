package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.web.bind.annotation.PathVariable;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    public Account getUserAccount(String username);
   public Account getByAccountId(int account_id) throws AccountNotFoundException;
    public BigDecimal getBalance(int account_id) throws AccountNotFoundException;
    public List<Account> ListRegisteredUsersToSendMoney (int account_id) throws AccountNotFoundException;
    public Account updateBalance(Account account, BigDecimal amount) throws AccountNotFoundException; ;

}
