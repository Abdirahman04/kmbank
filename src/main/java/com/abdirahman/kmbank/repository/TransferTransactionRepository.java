package com.abdirahman.kmbank.repository;

import com.abdirahman.kmbank.model.entity.TransferTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransferTransactionRepository extends JpaRepository<TransferTransaction, Long> {
    Optional<List<TransferTransaction>> findBySenderId(Long senderId);
    Optional<List<TransferTransaction>> findByRecipientId(Long recipientId);

    @Query("SELECT COUNT(e) FROM transfer_transactions")
    Long countAllTransferTransactions();
}
