package com.abdirahman.kmbank.controller;

import com.abdirahman.kmbank.model.entity.TransferTransaction;
import com.abdirahman.kmbank.service.TransferTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TransferTransactionController {

    private TransferTransactionService transferTransactionService;

    public TransferTransactionController() {
    }

    @Autowired
    public TransferTransactionController(TransferTransactionService transferTransactionService) {
        this.transferTransactionService = transferTransactionService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> send(@RequestBody TransferTransaction transfer) {
        return ResponseEntity.ok(transferTransactionService.send(transfer));
    }

    @GetMapping("/transferTransaction")
    public ResponseEntity<List<TransferTransaction>> getAllTransferTransactions() {
        List<TransferTransaction> transferTransactions = transferTransactionService.getAllTransferTransactions();
        if (transferTransactions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(transferTransactions);
    }

    @GetMapping("/transferTransaction/{id}")
    public ResponseEntity<List<TransferTransaction>> getTransferTransactionById(@PathVariable Long id) {
        List<TransferTransaction> transferTransactions = transferTransactionService.getTransferTransactionsById(id);
        if (transferTransactions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(transferTransactions);
    }
}
