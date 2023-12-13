package com.abdirahman.kmbank.repository;

import com.abdirahman.kmbank.model.TransferTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferTransactionRepository extends JpaRepository<TransferTransaction, Long> {
}
