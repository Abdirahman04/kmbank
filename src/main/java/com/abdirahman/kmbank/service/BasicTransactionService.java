package com.abdirahman.kmbank.service;

import com.abdirahman.kmbank.model.entity.BasicTransaction;
import com.abdirahman.kmbank.model.entity.User;
import com.abdirahman.kmbank.repository.BasicTransactionRepository;
import com.abdirahman.kmbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BasicTransactionService {
    BasicTransactionRepository basicTransactionRepository;
    UserRepository userRepository;

    public BasicTransactionService() {
    }

    @Autowired
    public BasicTransactionService(BasicTransactionRepository basicTransactionRepository, UserRepository userRepository) {
        this.basicTransactionRepository = basicTransactionRepository;
        this.userRepository = userRepository;
    }

    public String deposit(Long id, Double balance) {
        if (balance <= 10) return "Cannot make a deposit of less than 10";

        Optional<User> thisUserOptional = userRepository.findById(id);
        BasicTransaction transaction = new BasicTransaction(id,"deposit",balance);

        if (thisUserOptional.isPresent()) {
            User thisUser = thisUserOptional.get();
            thisUser.setBalance(thisUser.getBalance() + balance);

            basicTransactionRepository.save(transaction);
            userRepository.save(thisUser);
            return "Deposit made successfully";
        }

        return "User with id " + id + " does not exist!";
    }

    public String withdraw(Long id, Double balance) {
        if (balance <= 10) return "Cannot make a withdrawal of less than 10";
        Optional<User> thisUserOptional = userRepository.findById(id);

        if (thisUserOptional.isPresent()) {
            User thisUser = thisUserOptional.get();

            if (thisUser.getBalance() >= balance) {
                var transaction = new BasicTransaction(id, "withdrawal", balance);
                thisUser.setBalance(thisUser.getBalance() - balance);

                basicTransactionRepository.save(transaction);
                userRepository.save(thisUser);
                return "Withdrawal made successfully";
            }

            return "Not enough funds to make the transaction";
        }
        return "User with id " + id + " does not exist!";
    }

    public List<BasicTransaction> getAllBasicTransactions() {
        return basicTransactionRepository.findAll();
    }

    public List<BasicTransaction> getBasicTransactionsPerId(Long id) {
        return basicTransactionRepository.findByUserId(id).orElse(null);
    }
}
