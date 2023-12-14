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
        if (user.getFirstName().isEmpty() || user.getFirstName() ==null) return "First name required!";
        if (user.getLastName().isEmpty() || user.getLastName() ==null) return "Last name required!";
        String accountNumber = user.getAccountNumber();
        if (!isNumeric(accountNumber)) return "Account number should be a number!";
        long accNumber = Long.parseLong(accountNumber);
        if (accNumber < 9 || accNumber > 10) return "Account Number should be 9-10 digits long!";
        if (!isValidEmail(user.getEmail())) return "Not a valid email!";
        userRepository.save(user);
        return "User account created successfully";
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

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
