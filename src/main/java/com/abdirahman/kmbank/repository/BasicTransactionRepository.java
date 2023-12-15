package com.abdirahman.kmbank.repository;

import com.abdirahman.kmbank.model.BasicTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BasicTransactionRepository extends JpaRepository<BasicTransaction, Long> {
    Optional<List<BasicTransaction>> findByUserId(Long userId);
}
