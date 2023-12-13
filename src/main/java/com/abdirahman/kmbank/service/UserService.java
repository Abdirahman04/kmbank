package com.abdirahman.kmbank.service;

import com.abdirahman.kmbank.model.User;
import com.abdirahman.kmbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String addUser(User user) {
        try {
            userRepository.save(user);
            return "User added successfully";
        } catch (Exception e) {
            return "Error adding user";
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id);
        if (existingUser == null) {
            return null;
        }

        existingUser.setAccountNumber(user.getAccountNumber());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setDob(user.getDob());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setBalance(user.getBalance());

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public String hello() {
        return "hello";
    }
}
