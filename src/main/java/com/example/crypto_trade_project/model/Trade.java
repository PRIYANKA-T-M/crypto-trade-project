package com.example.crypto_trade_project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a trade made by a user in the cryptocurrency trading system.
 */
@Entity
@Table(name = "trades")
public class Trade {
    
    public enum TradeType {
        BUY, SELL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Trade type is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TradeType type;

    @NotNull(message = "Quantity is required")
    @DecimalMin(value = "0.00000001", message = "Quantity must be greater than 0")
    @Column(precision = 19, scale = 8, nullable = false)
    private BigDecimal quantity;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.00000001", message = "Price must be greater than 0")
    @Column(precision = 19, scale = 8, nullable = false)
    private BigDecimal price;

    @Column(name = "trade_date", nullable = false, updatable = false)
    private LocalDateTime tradeDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crypto_id", nullable = false)
    private Cryptocurrency cryptocurrency;

    // Constructors
    public Trade() {}

    public Trade(TradeType type, BigDecimal quantity, BigDecimal price, User user, Cryptocurrency cryptocurrency) {
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.user = user;
        this.cryptocurrency = cryptocurrency;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TradeType getType() {
        return type;
    }

    public void setType(TradeType type) {
        this.type = type;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(LocalDateTime tradeDate) {
        this.tradeDate = tradeDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cryptocurrency getCryptocurrency() {
        return cryptocurrency;
    }

    public void setCryptocurrency(Cryptocurrency cryptocurrency) {
        this.cryptocurrency = cryptocurrency;
    }

    // Helper method to calculate the total value of the trade
    public BigDecimal getTotalValue() {
        return price.multiply(quantity);
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", type=" + type +
                ", quantity=" + quantity +
                ", price=" + price +
                ", tradeDate=" + tradeDate +
                ", userId=" + (user != null ? user.getId() : "null") +
                ", cryptoId=" + (cryptocurrency != null ? cryptocurrency.getId() : "null") +
                '}';
    }
}
