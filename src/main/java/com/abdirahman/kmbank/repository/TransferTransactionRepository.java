package com.abdirahman.kmbank.repository;

import com.abdirahman.kmbank.model.TransferTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransferTransactionRepository extends JpaRepository<TransferTransaction, Long> {
    Optional<List<TransferTransaction>> findBySenderId(Long senderId);
    Optional<List<TransferTransaction>> findByRecipientId(Long recipientId);
}
