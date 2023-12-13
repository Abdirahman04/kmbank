package com.abdirahman.kmbank.controller;

import com.abdirahman.kmbank.model.BasicTransaction;
import com.abdirahman.kmbank.model.TransferTransaction;
import com.abdirahman.kmbank.model.User;
import com.abdirahman.kmbank.service.BasicTransactionService;
import com.abdirahman.kmbank.service.TransferTransactionService;
import com.abdirahman.kmbank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class KmbankController {
    @Autowired
    private UserService userService;

    @Autowired
    private BasicTransactionService basicTransactionService;

    @Autowired
    private TransferTransactionService transferTransactionService;

    public KmbankController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    @PostMapping("/user")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {

        User updatedUser = userService.updateUser(id, user);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody Map<String,Object> body) {
        Long id = Long.valueOf((Integer)body.get("id"));
        Double balance = Double.valueOf((Integer)body.get("balance"));

        basicTransactionService.deposit(id,balance);

        return  ResponseEntity.noContent().build();
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody Map<String,Object> body) {
        Long id = Long.valueOf((Integer)body.get("id"));
        Double balance = Double.valueOf((Integer)body.get("balance"));

        basicTransactionService.withdraw(id,balance);

        return  ResponseEntity.noContent().build();
    }

    @GetMapping("/basicTransaction")
    public ResponseEntity<List<BasicTransaction>> getAllBasicTransactions() {
        List<BasicTransaction> basicTransactions = basicTransactionService.getAllBasicTransactions();
        if (basicTransactions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(basicTransactions);
    }

    @GetMapping("/transferTransaction")
    public ResponseEntity<List<TransferTransaction>> getAllTransferTransactions() {
        List<TransferTransaction> transferTransactions = transferTransactionService.getAllTransferTransactions();
        if (transferTransactions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(transferTransactions);
    }

    @PostMapping("/send")
    public ResponseEntity<?> send(@RequestBody TransferTransaction transfer) {
        transferTransactionService.send(transfer);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return userService.hello();
    }
}
