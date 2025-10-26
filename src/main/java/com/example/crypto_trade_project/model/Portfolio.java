package com.example.crypto_trade_project.model;

import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ElementCollection
    @CollectionTable(name = "portfolio_holdings", joinColumns = @JoinColumn(name = "portfolio_id"))
    @MapKeyColumn(name = "cryptocurrency")
    @Column(name = "amount")
    private Map<String, Double> holdings = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<String, Double> getHoldings() {
        return holdings;
    }

    public void setHoldings(Map<String, Double> holdings) {
        this.holdings = holdings;
    }
}
