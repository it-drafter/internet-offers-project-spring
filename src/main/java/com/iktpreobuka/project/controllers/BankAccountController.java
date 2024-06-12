package com.iktpreobuka.project.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.project.entities.BankAccountEntity;
import com.iktpreobuka.project.entities.dto.BankAccountDto;
import com.iktpreobuka.project.security.Views;
import com.iktpreobuka.project.services.BankAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project/bankAccounts")
public class BankAccountController {
    @Autowired
    protected BankAccountDao bankAccountService;

    @GetMapping("/customer")
    @JsonView(Views.Customer.class)
    public Iterable<BankAccountEntity> getAllBankAccounts() {
        return bankAccountService.getAllBankAccounts();
    }

    @GetMapping("/seller")
    @JsonView(Views.Seller.class)
    public Iterable<BankAccountEntity> getAllBankAccountsForSeller() {
        return bankAccountService.getAllBankAccounts();
    }

    @GetMapping("/admin")
    @JsonView(Views.Admin.class)
    public Iterable<BankAccountEntity> getAllBankAccountsForAdmin() {
        return bankAccountService.getAllBankAccounts();
    }

//    @PostMapping("/user/{userId}")
//    public BankAccountEntity addNewBankAccount(@RequestBody BankAccountEntity newBankAccount, @PathVariable String userId) {
//        return bankAccountService.addNewBankAccount(newBankAccount, userId);
//    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<?> addNewBankAccount(@RequestBody BankAccountDto bankAccountDto, @PathVariable String userId) {
        return new ResponseEntity<BankAccountDto>(bankAccountService.addNewBankAccount(bankAccountDto, userId), HttpStatus.OK);
    }

    // Not sure how to do this (PUT modification) using DTOs - I get a new entity instead of modifying an existing one. I need to look into this. // SOLVED!
    // SOLVED!
//    @PutMapping("/{id}")
//    public ResponseEntity<?> modifyExistingBankAccount(@PathVariable String id, @RequestBody BankAccountDto bankAccountDto) {
////        return bankAccountService.modifyExistingBankAccount(id, changedBankAccount);
//        return new ResponseEntity<BankAccountDto>(bankAccountService.modifyExistingBankAccount(id, bankAccountDto), HttpStatus.OK);
//    }

//    @PutMapping("/{id}")
//    public BankAccountDto modifyExistingBankAccount(@PathVariable String id, @RequestBody BankAccountDto changedBankAccount) {
//        return bankAccountService.modifyExistingBankAccount(id, changedBankAccount);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyExistingBankAccount(@PathVariable String id, @RequestBody BankAccountDto changedBankAccount) {
        return new ResponseEntity<BankAccountDto>(bankAccountService.modifyExistingBankAccount(id, changedBankAccount), HttpStatus.OK);
    }

//    @DeleteMapping("/{id}")
//    public BankAccountDto deleteBankAccountById(@PathVariable String id) {
//        return bankAccountService.deleteBankAccountById(id);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBankAccountById(@PathVariable String id) {
        return new ResponseEntity<BankAccountDto>(bankAccountService.deleteBankAccountById(id), HttpStatus.OK);
    }
}
