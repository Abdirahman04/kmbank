package com.abdirahman.kmbank.repository;

import com.abdirahman.kmbank.model.entity.BasicTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BasicTransactionRepository extends JpaRepository<BasicTransaction, Long> {
    Optional<List<BasicTransaction>> findByUserId(Long userId);
    @Query("SELECT COUNT(e) FROM basic_transactions")
    Long countAllBasicTransactions();
    @Query("SELECT COUNT(e) FROM basic_transactions e WHERE e.transaction_type = :transaction")
    Long countTransactions(@Param("someValue") String transaction);
}
