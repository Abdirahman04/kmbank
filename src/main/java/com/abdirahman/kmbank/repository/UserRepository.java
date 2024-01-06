package com.abdirahman.kmbank.repository;

import com.abdirahman.kmbank.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccountNumber(String accountNumber);
    Optional<User> findByEmail(String email);
    Long countBy();
    @Query("SELECT SUM(u.balance) FROM User u")
    Double sumBalance();

}
