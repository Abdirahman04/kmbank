package com.abdirahman.kmbank.service;

import com.abdirahman.kmbank.model.BasicTransaction;
import com.abdirahman.kmbank.repository.BasicTransactionRepository;
import com.abdirahman.kmbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BasicTransactionService {
    @Autowired
    BasicTransactionRepository basicTransactionRepository;

    @Autowired
    UserRepository userRepository;

    public void deposit(Long id, Double balance) {
        var thisUser = userRepository.findById(id);
        var transaction = new BasicTransaction(id,"deposit",balance);

        thisUser.setBalance(thisUser.getBalance() + balance);

        basicTransactionRepository.save(transaction);
        userRepository.save(thisUser);
    }
}
