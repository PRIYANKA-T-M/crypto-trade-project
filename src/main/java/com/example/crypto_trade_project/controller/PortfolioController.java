package com.example.crypto_trade_project.controller;

import com.example.crypto_trade_project.model.Trade;
import com.example.crypto_trade_project.model.User;
import com.example.crypto_trade_project.service.TradeService;
import com.example.crypto_trade_project.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class PortfolioController {

    private final TradeService tradeService;
    private final UserService userService;

    @FXML
    private Label balanceLabel;

    @FXML
    private TableView<Trade> tradesTable;

    @FXML
    private TableColumn<Trade, String> cryptoNameColumn;

    @FXML
    private TableColumn<Trade, Trade.TradeType> tradeTypeColumn;

    @FXML
    private TableColumn<Trade, BigDecimal> quantityColumn;

    @FXML
    private TableColumn<Trade, BigDecimal> priceColumn;

    @FXML
    private TableColumn<Trade, LocalDateTime> dateColumn;

    private final ObservableList<Trade> trades = FXCollections.observableArrayList();

    public PortfolioController(TradeService tradeService, UserService userService) {
        this.tradeService = tradeService;
        this.userService = userService;
    }

    @FXML
    public void initialize() {
        cryptoNameColumn.setCellValueFactory(new PropertyValueFactory<>("cryptoName"));
        tradeTypeColumn.setCellValueFactory(new PropertyValueFactory<>("tradeType"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("tradeDate"));
        tradesTable.setItems(trades);

        loadPortfolio();
    }

    private void loadPortfolio() {
        // For simplicity, we'll use the first user
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return;
        }
        User user = users.get(0);

        balanceLabel.setText("Balance: $" + user.getBalance().toPlainString());
        trades.setAll(tradeService.getTradesByUserId(user.getId()));
    }
}
