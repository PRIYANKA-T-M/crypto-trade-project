package com.example.crypto_trade_project.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<JavaFxApplication.StageReadyEvent> {

    @Override
    public void onApplicationEvent(JavaFxApplication.StageReadyEvent event) {
        try {
            Stage stage = event.getStage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/DashboardView.fxml"));
            Parent root = fxmlLoader.load();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("Crypto Trading System");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
