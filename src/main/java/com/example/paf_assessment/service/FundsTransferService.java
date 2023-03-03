package com.example.paf_assessment.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.paf_assessment.exception.TransferException;
import com.example.paf_assessment.model.Accounts;
import com.example.paf_assessment.model.TransferRequest;
import com.example.paf_assessment.repository.AccountRepo;

@Service
public class FundsTransferService {

    @Autowired
    private AccountRepo accountRepo;
    
    @Transactional(rollbackFor = TransferException.class)
    public boolean transfer(String fromAccountId, String toAccountId, float amount, String comments) throws TransferException {

        // Generate orderId
        String transactionId = UUID.randomUUID().toString().substring(0, 8);
        System.out.printf(">>>> TransactionId: %s\n", transactionId);

        TransferRequest.setTransactionId(transactionId);
        // Get the account objects for the from and to accounts
        Accounts fromAccount = accountRepo.getAccountById(fromAccountId);
        Accounts toAccount = accountRepo.getAccountById(toAccountId);
        
        // Check that both accounts exist and the from and to accounts are not the same
        if (fromAccount == null || toAccount == null) {
            return false;
        }
        if (fromAccount.getName().equals(toAccount.getName())) {
            return false;
        }
        
        // Check that the transfer amount is valid
        if (amount <= 0) {
            return false;
        }
        if (amount > fromAccount.getBalance()) {
            return false;
        }
        
        // Perform the transfer
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);
        
        // Update the account balances in the data source
        accountRepo.updateAccountBalance(fromAccount);
        accountRepo.updateAccountBalance(toAccount);
        
        // Log the transfer
        String logMessage = String.format("Transfer of %f from account %s to account %s with comments: %s", amount, fromAccountId, toAccountId, comments);
        System.out.println(logMessage);
        
        return true;
    }
}
