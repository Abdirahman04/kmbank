package com.abdirahman.kmbank.controller;

import com.abdirahman.kmbank.model.entity.BasicTransaction;
import com.abdirahman.kmbank.model.request.BasicTransactionRequestBody;
import com.abdirahman.kmbank.service.BasicTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BasicTransactionController {

    private BasicTransactionService basicTransactionService;

    public BasicTransactionController() {
    }

    @Autowired
    public BasicTransactionController(BasicTransactionService basicTransactionService) {
        this.basicTransactionService = basicTransactionService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody BasicTransactionRequestBody body) {
        return  ResponseEntity.ok(basicTransactionService.deposit(body.id(), body.balance()));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody BasicTransactionRequestBody body) {
        return  ResponseEntity.ok(basicTransactionService.withdraw(body.id(), body.balance()));
    }

    @GetMapping("/basicTransaction")
    public ResponseEntity<List<BasicTransaction>> getAllBasicTransactions() {
        List<BasicTransaction> basicTransactions = basicTransactionService.getAllBasicTransactions();
        if (basicTransactions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(basicTransactions);
    }

    @GetMapping("/basicTransaction/{id}")
    public ResponseEntity<List<BasicTransaction>> getBasicTransactionsById(@PathVariable Long id) {
        List<BasicTransaction> basicTransactions = basicTransactionService.getBasicTransactionsPerId(id);
        if (basicTransactions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(basicTransactions);
    }
}
