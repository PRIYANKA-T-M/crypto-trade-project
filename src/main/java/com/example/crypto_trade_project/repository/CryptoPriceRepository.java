package com.example.crypto_trade_project.repository;

import com.example.crypto_trade_project.model.CryptoPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoPriceRepository extends JpaRepository<CryptoPrice, Long> {}
