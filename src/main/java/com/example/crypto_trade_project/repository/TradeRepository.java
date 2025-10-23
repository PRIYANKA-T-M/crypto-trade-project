package com.example.crypto_trade_project.repository;

import com.example.crypto_trade_project.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {}