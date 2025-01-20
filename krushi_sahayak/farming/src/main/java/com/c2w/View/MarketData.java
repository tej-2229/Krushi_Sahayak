package com.c2w.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MarketData {

    Stage primaryStage;

    public static void launchWebView() {
        Stage newStage = new Stage();

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        webEngine.load("https://www.napanta.com/market-price/maharashtra/pune/pune");

        Label closeLabel = new Label("Close");
        closeLabel.setStyle(
                "-fx-font-family: 'Arial';" +
                        "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #2a52be;" +
                        "-fx-background-color: #F5F5F5;" +
                        "-fx-padding: 10px;" +
                        "-fx-border-color: #2E8B57;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 5px;" +
                        "-fx-background-radius: 5px;" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);" +
                        "-fx-alignment: center;" +
                        "-fx-cursor: hand;");
        closeLabel.setOnMouseClicked(event -> {
            newStage.close();
        });
        HBox closeLabelBox = new HBox(closeLabel);
        closeLabelBox.setAlignment(Pos.BOTTOM_RIGHT);
        closeLabelBox.setPadding(new Insets(30, 30, 0, 30));
        VBox root = new VBox();
        root.getChildren().addAll(webView, closeLabelBox);

        Scene scene = new Scene(root, 1000, 700);
        newStage.setScene(scene);
        newStage.setTitle("Market Data");
        newStage.show();
    }
}