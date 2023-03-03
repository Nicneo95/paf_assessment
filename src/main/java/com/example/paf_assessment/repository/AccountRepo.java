package com.example.paf_assessment.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.paf_assessment.model.Accounts;

@Repository
public class AccountRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // using this method to only display the name 
    public List<String> getAllAccountNames() {
        return jdbcTemplate.queryForList("SELECT name FROM accounts", String.class);
    }
    // to display id and account not resolve 
    public List<Accounts> getAllAccounts() {
        return jdbcTemplate.query("SELECT account_id, name FROM accounts", (rs, rowNum) -> {
            Accounts account = new Accounts();
            account.setAccount_id(rs.getString("account_id"));
            account.setName(rs.getString("name"));
            return account;
        });
    }
    // get account by id 
    public boolean accountExists(String accountId) {
        String query = "SELECT COUNT(*) FROM accounts WHERE account_id = ?";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, accountId);
        return count != null && count > 0;
      }
    
    // yet to complete this method  
    public Accounts getAccountById(String fromAccount) {
        return null;
    }
    public void updateAccountBalance(Accounts fromAccount) {
    }
}
