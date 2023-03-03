package com.example.paf_assessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.paf_assessment.exception.TransferException;
import com.example.paf_assessment.model.Accounts;
import com.example.paf_assessment.model.TransferRequest;
import com.example.paf_assessment.repository.AccountRepo;
import com.example.paf_assessment.service.FundsTransferService;

@Controller
@RequestMapping("/transfer")
public class FundsTransferController {

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    FundsTransferService fundsTransferSvc;

    @PostMapping("/transfer")
    public String transferFunds(@ModelAttribute TransferRequest transferRequest, Model model) throws TransferException {
        String fromAccount = transferRequest.getFromAccount();
        String toAccount = transferRequest.getToAccount();
        float amount = transferRequest.getAmount();
        String comments = transferRequest.getComments();

        // Check that both account exists
        if (!accountRepo.accountExists(fromAccount)) {
            model.addAttribute("error", "Invalid from account");
            return "index";
        }
        if (!accountRepo.accountExists(toAccount)) {
            model.addAttribute("error", "Invalid from account");
            return "index";
        }
        // Check that the transfer amount is valid
        if (amount <= 0) {
            model.addAttribute("error", "Transfer amount must be greater than zero");
            return "index";
        }
        // Check that the from and to account names are not the same
        if (fromAccount.equals(toAccount)) {
            model.addAttribute("error", "Cannot transfer funds to the same account");
            return "index";
        }
        // Check that the from account has sufficient balance
        Accounts from = accountRepo.getAccountById(fromAccount);
        if (from.getBalance() < amount) {
            model.addAttribute("error", "Insufficient funds in from account");
            return "index";
        }

        // Perform the transfer
        fundsTransferSvc.transfer(fromAccount, toAccount, amount, comments);
    
        return "transfer";
    }
}