package com.example.crypto_trade_project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a cryptocurrency in the trading system.
 */
@Entity
@Table(name = "cryptocurrencies")
public class Cryptocurrency {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be at most 100 characters")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Symbol is required")
    @Size(max = 10, message = "Symbol must be at most 10 characters")
    @Column(nullable = false, unique = true)
    private String symbol;

    @NotNull(message = "Current price is required")
    @DecimalMin(value = "0.00000001", message = "Price must be greater than 0")
    @Column(precision = 19, scale = 8, nullable = false)
    private BigDecimal currentPrice;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated = LocalDateTime.now();

    @Column(precision = 19, scale = 2)
    private BigDecimal marketCap;

    @Column(precision = 19, scale = 2)
    private BigDecimal volume24h;

    @Column(precision = 10, scale = 2)
    private Double priceChange24h;

    @Column(precision = 10, scale = 2)
    private Double priceChangePercentage24h;

    @OneToMany(mappedBy = "cryptocurrency", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trade> trades = new ArrayList<>();

    @OneToMany(mappedBy = "cryptocurrency", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CryptoPrice> priceHistory = new ArrayList<>();

    // Constructors
    public Cryptocurrency() {}

    public Cryptocurrency(String name, String symbol, BigDecimal currentPrice) {
        this.name = name;
        this.symbol = symbol;
        this.currentPrice = currentPrice;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
        this.lastUpdated = LocalDateTime.now();
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public BigDecimal getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(BigDecimal marketCap) {
        this.marketCap = marketCap;
    }

    public BigDecimal getVolume24h() {
        return volume24h;
    }

    public void setVolume24h(BigDecimal volume24h) {
        this.volume24h = volume24h;
    }

    public Double getPriceChange24h() {
        return priceChange24h;
    }

    public void setPriceChange24h(Double priceChange24h) {
        this.priceChange24h = priceChange24h;
    }

    public Double getPriceChangePercentage24h() {
        return priceChangePercentage24h;
    }

    public void setPriceChangePercentage24h(Double priceChangePercentage24h) {
        this.priceChangePercentage24h = priceChangePercentage24h;
    }

    public List<Trade> getTrades() {
        return trades;
    }

    public void setTrades(List<Trade> trades) {
        this.trades = trades;
    }

    public List<CryptoPrice> getPriceHistory() {
        return priceHistory;
    }

    public void setPriceHistory(List<CryptoPrice> priceHistory) {
        this.priceHistory = priceHistory;
    }

    // Helper methods
    public void addTrade(Trade trade) {
        trades.add(trade);
        trade.setCryptocurrency(this);
    }

    public void removeTrade(Trade trade) {
        trades.remove(trade);
        trade.setCryptocurrency(null);
    }

    public void addPriceHistory(CryptoPrice price) {
        priceHistory.add(price);
        price.setCryptocurrency(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cryptocurrency that = (Cryptocurrency) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, symbol);
    }

    @Override
    public String toString() {
        return "Cryptocurrency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", currentPrice=" + currentPrice +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
