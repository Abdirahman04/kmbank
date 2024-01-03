package com.abdirahman.kmbank.controller;

import com.abdirahman.kmbank.model.entity.User;
import com.abdirahman.kmbank.model.request.UserRequestBody;
import com.abdirahman.kmbank.model.response.UserResponseBody;
import com.abdirahman.kmbank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private UserService userService;

    public UserController() {
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserResponseBody>> getUsers() {
        List<UserResponseBody> users = userService.getAllUsers();
        if (users.isEmpty()) return ResponseEntity.noContent().build();
        else return ResponseEntity.ok(users);
    }

    @GetMapping("/user/raw")
    public ResponseEntity<List<User>> getRawUsers() {
        List<User> users = userService.getAllUsersRaw();
        if (users.isEmpty()) return ResponseEntity.noContent().build();
        else return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseBody> getUserById(@PathVariable Long id) {
        UserResponseBody user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/user/raw/{accountNumber}")
    public ResponseEntity<User> getUserByIdRaw(@PathVariable Long accountNumber) {
        User user = userService.findByIdRaw(accountNumber.toString());
        return new ResponseEntity<>(user, HttpStatusCode.valueOf(200));
    }

    @GetMapping("user/account/{accountNumber}")
    public ResponseEntity<UserResponseBody> getUserByAccountNumber(@PathVariable Long accountNumber) {
        UserResponseBody user = userService.findByAccountNumber(accountNumber.toString());
        return new ResponseEntity<>(user, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/user/login")
    public boolean login(@RequestBody Map<String, String> body) {
        String accNumber = body.get("accountNumber");
        String password = body.get("password");
        return userService.login(accNumber,password);
    }

    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody UserRequestBody userRequestBody) {
        return ResponseEntity.ok(userService.addUser(userRequestBody));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserResponseBody> updateUser(@PathVariable Long id, @RequestBody User user) {

        UserResponseBody updatedUser = userService.updateUser(id, user);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

}
