package com.example.crypto_trade_project.service;

import com.example.crypto_trade_project.model.Portfolio;
import com.example.crypto_trade_project.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    public Optional<Portfolio> getPortfolioByUserId(Long userId) {
        return portfolioRepository.findByUserId(userId);
    }

    public void buyCrypto(Long userId, String cryptocurrency, Double amount) {
        Optional<Portfolio> optionalPortfolio = portfolioRepository.findByUserId(userId);
        if (optionalPortfolio.isPresent()) {
            Portfolio portfolio = optionalPortfolio.get();
            portfolio.getHoldings().merge(cryptocurrency, amount, Double::sum);
            portfolioRepository.save(portfolio);
        }
    }

    public void sellCrypto(Long userId, String cryptocurrency, Double amount) {
        Optional<Portfolio> optionalPortfolio = portfolioRepository.findByUserId(userId);
        if (optionalPortfolio.isPresent()) {
            Portfolio portfolio = optionalPortfolio.get();
            Double currentAmount = portfolio.getHoldings().get(cryptocurrency);
            if (currentAmount != null && currentAmount >= amount) {
                portfolio.getHoldings().put(cryptocurrency, currentAmount - amount);
                portfolioRepository.save(portfolio);
            }
        }
    }
}
