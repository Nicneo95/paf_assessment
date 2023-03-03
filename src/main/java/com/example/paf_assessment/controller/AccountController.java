package com.example.paf_assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.paf_assessment.repository.AccountRepo;

@Controller
@RequestMapping("/")
public class AccountController {

    @Autowired 
    private AccountRepo accountRepo;

    @GetMapping()
    public String showAccountNames(Model model) {
        List<String> accountNames = accountRepo.getAllAccountNames();
        model.addAttribute("accountNames", accountNames);
        return "index";
    }
    
}
