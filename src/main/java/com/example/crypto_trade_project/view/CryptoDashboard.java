package com.example.crypto_trade_project.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.net.http.*;
import java.net.URI;
import com.google.gson.*;
import java.util.*;

public class CryptoDashboard extends Application {

    private final LineChart<String, Number> chart =
            new LineChart<>(new CategoryAxis(), new NumberAxis());

    @Override
    public void start(Stage stage) {
        chart.setTitle("Crypto Price Trends");

        new Thread(this::updatePrices).start();

        VBox root = new VBox(chart);
        stage.setScene(new Scene(root, 800, 600));
        stage.setTitle("CryptoTradeFX Dashboard");
        stage.show();
    }

    private void updatePrices() {
        try {
            while (true) {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest req = HttpRequest.newBuilder()
                        .uri(URI.create("https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum,solana&vs_currencies=usd"))
                        .build();
                HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

                JsonObject data = JsonParser.parseString(res.body()).getAsJsonObject();
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName("Latest Prices");

                data.entrySet().forEach(entry -> {
                    double price = entry.getValue().getAsJsonObject().get("usd").getAsDouble();
                    series.getData().add(new XYChart.Data<>(entry.getKey(), price));
                });

                javafx.application.Platform.runLater(() -> {
                    chart.getData().clear();
                    chart.getData().add(series);
                });

                Thread.sleep(10000); // refresh every 10 seconds
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

