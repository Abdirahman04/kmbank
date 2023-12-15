package com.abdirahman.kmbank.service;

import com.abdirahman.kmbank.model.TransferTransaction;
import com.abdirahman.kmbank.model.User;
import com.abdirahman.kmbank.repository.TransferTransactionRepository;
import com.abdirahman.kmbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransferTransactionService {
    @Autowired
    TransferTransactionRepository transferTransactionRepository;
    @Autowired
    UserRepository userRepository;

    public String send(TransferTransaction transfer) {
        Optional<User> senderOptional = userRepository.findById(transfer.getSenderId());
        Optional<User> recipientOptional = userRepository.findById(transfer.getRecipientId());

        if (senderOptional.isPresent() && recipientOptional.isPresent()) {
            User sender = senderOptional.get();
            User recipient = recipientOptional.get();

            if (sender.getBalance() >= transfer.getBalance()) {
                sender.setBalance(sender.getBalance() - transfer.getBalance());
                recipient.setBalance(recipient.getBalance() + transfer.getBalance());
                userRepository.save(sender);
                userRepository.save(recipient);
                transferTransactionRepository.save(transfer);
                return "Money sent successfully";
            }

            return "Not enough funds to make the transaction";
        }

        return "Invalid input";
    }

    public List<TransferTransaction> getAllTransferTransactions() {
        return transferTransactionRepository.findAll();
    }

    public List<TransferTransaction> getTransferTransactionsById(Long id) {
        Optional<List<TransferTransaction>> optionalTransferTransactions1 = transferTransactionRepository.findBySenderId(id);
        Optional<List<TransferTransaction>> optionalTransferTransactions2 = transferTransactionRepository.findByRecipientId(id);

        if (optionalTransferTransactions1.isPresent() && optionalTransferTransactions2.isPresent()) {
            List<TransferTransaction> transferTransactions = optionalTransferTransactions1.get();
            transferTransactions.addAll(optionalTransferTransactions2.get());
            return transferTransactions;
        }

        else return optionalTransferTransactions1.orElseGet(() -> optionalTransferTransactions2.orElse(null));
    }
}
