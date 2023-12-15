package com.abdirahman.kmbank.service;

import com.abdirahman.kmbank.model.User;
import com.abdirahman.kmbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String addUser(User user) {
        if (isStringEmpty(user.getFirstName())) return "First name required!";
        if (isStringEmpty(user.getLastName())) return "Last name required!";
        if (isStringEmpty(user.getAccountNumber())) return "Account number required!";
        String accountNumber = user.getAccountNumber();
        if (findByAccountNumber(accountNumber) != null) return "Account number exists!";
        if (!isNumeric(accountNumber)) return "Account number should be a number!";
        long accNumber = Long.parseLong(accountNumber);
        if (accountNumber.length() < 9 || accountNumber.length() > 10) return "Account Number should be 9-10 digits long!";
        if (user.getDob() == null) return "Date of birth required";
        if (!isValidEmail(user.getEmail())) return "Not a valid email!";
        if (findByEmail(user.getEmail()) != null) return "Email exists!";
        if (isStringEmpty(user.getPassword())) return "Password required";

        try {
            userRepository.save(user);
            return "User account created successfully";
        } catch (Exception e) {
            return "Error creating account!";
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    public User findByAccountNumber(String accNumber) {
        return userRepository.findByAccountNumber(accNumber).orElse(null);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User updateUser(Long id, User user) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setAccountNumber(user.getAccountNumber());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setDob(user.getDob());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setBalance(user.getBalance());

            return userRepository.save(existingUser);
        }
        return null;
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted successfully";
    }

    public String hello() {
        return "hello";
    }

    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isStringEmpty(String element) {
        return (element == null || element.isEmpty());
    }
}
