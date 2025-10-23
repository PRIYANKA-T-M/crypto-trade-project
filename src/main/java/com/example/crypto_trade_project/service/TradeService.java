package com.example.crypto_trade_project.service;

import com.example.crypto_trade_project.model.Trade;
import com.example.crypto_trade_project.model.User;
import com.example.crypto_trade_project.repository.TradeRepository;
import com.example.crypto_trade_project.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TradeService {

    private final TradeRepository tradeRepository;
    private final UserRepository userRepository;

    public TradeService(TradeRepository tradeRepository, UserRepository userRepository) {
        this.tradeRepository = tradeRepository;
        this.userRepository = userRepository;
    }

    public Trade createTrade(Trade trade) {
        // Additional business logic for trade execution can be added here
        return tradeRepository.save(trade);
    }

    public List<Trade> getTradesByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getTrades();
    }
}
