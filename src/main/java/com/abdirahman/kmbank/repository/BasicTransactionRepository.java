package com.abdirahman.kmbank.repository;

import com.abdirahman.kmbank.model.BasicTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicTransactionRepository extends JpaRepository<BasicTransaction, Long> {
}
