package com.example.crypto_trade_project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a historical price point for a cryptocurrency.
 * This entity is used to track price changes over time.
 */
@Entity
@Table(name = "crypto_prices")
public class CryptoPrice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.00000001", message = "Price must be greater than 0")
    @Column(precision = 19, scale = 8, nullable = false)
    private BigDecimal price;
    
    @Column(name = "timestamp", nullable = false, updatable = false)
    private LocalDateTime timestamp = LocalDateTime.now();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cryptocurrency_id", nullable = false)
    private Cryptocurrency cryptocurrency;
    
    // Constructors
    public CryptoPrice() {}
    
    public CryptoPrice(BigDecimal price, Cryptocurrency cryptocurrency) {
        this.price = price;
        this.cryptocurrency = cryptocurrency;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    public Cryptocurrency getCryptocurrency() {
        return cryptocurrency;
    }
    
    public void setCryptocurrency(Cryptocurrency cryptocurrency) {
        this.cryptocurrency = cryptocurrency;
    }
    
    @Override
    public String toString() {
        return "CryptoPrice{" +
                "id=" + id +
                ", price=" + price +
                ", timestamp=" + timestamp +
                ", cryptoId=" + (cryptocurrency != null ? cryptocurrency.getId() : "null") +
                '}';
    }
}
