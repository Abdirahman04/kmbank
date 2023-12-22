package com.abdirahman.kmbank.service;

import com.abdirahman.kmbank.exception.ApiRequestException;
import com.abdirahman.kmbank.model.entity.User;
import com.abdirahman.kmbank.model.request.UserRequestBody;
import com.abdirahman.kmbank.model.response.UserResponseBody;
import com.abdirahman.kmbank.repository.UserRepository;
import com.abdirahman.kmbank.util.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserService() {
    }

    public String addUser(UserRequestBody userRequestBody) {
        User user = EntityMapper.mapUserRequestBodyToUser(userRequestBody);

        if (isStringEmpty(user.getFirstName())) throw new ApiRequestException("First name required");
        if (isStringEmpty(user.getLastName())) throw new ApiRequestException("Last name required");
        if (isStringEmpty(user.getAccountNumber())) throw new ApiRequestException("Account number required");
        String accountNumber = user.getAccountNumber();
        if (userRepository.findByAccountNumber(accountNumber).isPresent()) throw new ApiRequestException("Account number exists");
        if (!isNumeric(accountNumber)) throw new ApiRequestException("Account number should be a number");
        long accNumber = Long.parseLong(accountNumber);
        if (accountNumber.length() < 9 || accountNumber.length() > 10) throw new ApiRequestException("Account Number should be 9-10 digits long");
        if (user.getDob() == null) throw new ApiRequestException("Date of birth required");
        if (!isValidEmail(user.getEmail())) throw new ApiRequestException("Not a valid email");
        if (userRepository.findByEmail(user.getEmail()).isPresent()) throw new ApiRequestException("Email exists");
        if (isStringEmpty(user.getPassword())) throw new ApiRequestException("Password required");

        try {
            userRepository.save(user);
            return "User account created successfully";
        } catch (Exception e) {
            throw new RuntimeException("Error creating account", e);
        }
    }

    public List<UserResponseBody> getAllUsers() {
        List<User> userList =  userRepository.findAll();
        List<UserResponseBody> userResponseBodyList = new ArrayList<>();
        for (User user : userList) {
            userResponseBodyList.add(EntityMapper.mapUserToUserResponseBody(user));
        }
        return userResponseBodyList;
    }

    public UserResponseBody findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) throw new ApiRequestException("No user with id { " + id + " } found");
        return EntityMapper.mapUserToUserResponseBody(userOptional.get());
    }

    public UserResponseBody findByAccountNumber(String accNumber) {
        Optional<User> userOptional = userRepository.findByAccountNumber(accNumber);
        if (userOptional.isEmpty()) throw new ApiRequestException("No user with account number {" + accNumber + "}");
        return EntityMapper.mapUserToUserResponseBody(userOptional.get());
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public UserResponseBody updateUser(Long id, User user) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setAccountNumber(user.getAccountNumber());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setDob(user.getDob());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());

            User user1 =  userRepository.save(existingUser);
            return EntityMapper.mapUserToUserResponseBody(user1);
        }
        else throw new ApiRequestException("User with id { " + id + " } does not exist");
    }

    public boolean login(String accNumber, String password) {
        Optional<User> userOptional = userRepository.findByAccountNumber(accNumber);
        User user = userOptional.orElse(null);
        return (user != null && user.getPassword().equals(password));
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
