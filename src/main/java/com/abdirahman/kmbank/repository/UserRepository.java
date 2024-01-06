package com.abdirahman.kmbank.repository;

import com.abdirahman.kmbank.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccountNumber(String accountNumber);
    Optional<User> findByEmail(String email);
    @Query("SELECT COUNT(e) FROM users e")
    Long countAllUsers();
    @Query("SELECT SUM(e.balance) FROM users e")
    Double getTotalOfBalance();

}
