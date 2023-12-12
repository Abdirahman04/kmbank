package com.abdirahman.kmbank.repository;

import com.abdirahman.kmbank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findById(Long id);
}
