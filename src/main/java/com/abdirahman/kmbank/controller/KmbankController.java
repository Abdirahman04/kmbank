package com.abdirahman.kmbank.controller;

import com.abdirahman.kmbank.model.BasicTransaction;
import com.abdirahman.kmbank.model.User;
import com.abdirahman.kmbank.service.BasicTransactionService;
import com.abdirahman.kmbank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class KmbankController {
    @Autowired
    private UserService userService;

    @Autowired
    private BasicTransactionService basicTransactionService;

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

    @GetMapping("/basicTransaction")
    public ResponseEntity<List<BasicTransaction>> getAllTransactions() {
        List<BasicTransaction> basicTransactions = basicTransactionService.getAllTransactions();
        if (basicTransactions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(basicTransactions);
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return userService.hello();
    }
}
