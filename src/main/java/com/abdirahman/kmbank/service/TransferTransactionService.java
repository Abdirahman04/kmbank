package com.abdirahman.kmbank.service;

import com.abdirahman.kmbank.model.TransferTransaction;
import com.abdirahman.kmbank.model.User;
import com.abdirahman.kmbank.repository.TransferTransactionRepository;
import com.abdirahman.kmbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransferTransactionService {
    @Autowired
    TransferTransactionRepository transferTransactionRepository;
    @Autowired
    UserRepository userRepository;

    public void send(TransferTransaction transfer) {
        User sender = userRepository.findById(transfer.getSenderId());
        User recipient = userRepository.findById(transfer.getRecipientId());
        if (sender.getBalance() >= transfer.getBalance()) {
            sender.setBalance(sender.getBalance() - transfer.getBalance());
            recipient.setBalance(recipient.getBalance() + transfer.getBalance());
            userRepository.save(sender);
            userRepository.save(recipient);
            transferTransactionRepository.save(transfer);
        }
    }

    public List<TransferTransaction> getAllTransferTransactions() {
        return transferTransactionRepository.findAll();
    }
}
