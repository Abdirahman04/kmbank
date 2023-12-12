package com.abdirahman.kmbank.controller;

import com.abdirahman.kmbank.model.User;
import com.abdirahman.kmbank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class KmbankController {
    @Autowired
    private UserService userService;

    public KmbankController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return userService.hello();
    }
}
