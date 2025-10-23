package com.example.crypto_trade_project.repository;

import com.example.crypto_trade_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
