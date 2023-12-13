package com.abdirahman.kmbank.repository;

import com.abdirahman.kmbank.model.User;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findById(Long id);
    void deleteById(Long id);
}
