package com.example.crypto_trade_project.repository;

import com.example.crypto_trade_project.model.Cryptocurrency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoRepository extends JpaRepository<Cryptocurrency, Long> {}
