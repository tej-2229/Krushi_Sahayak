package com.c2w.View;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

public class Dashboard {
    private static final int IMAGE_WIDTH = 600;
    private static final int IMAGE_HEIGHT = 470;
    private static final int SCROLL_SPEED = 12;
    private static ScrollPane scrollPane;
    private static HBox topImageBox;
    private static double position = 0.0;

    public static VBox dash() {
        // Top HBox with scrollable images
        topImageBox = new HBox(50);
        topImageBox.setPadding(new Insets(10, 5, 10, 5));
        topImageBox.setAlignment(Pos.CENTER_LEFT);
        topImageBox.setStyle("fx-background-radius:20");

        String[] imageUrls = {
                "/assets/images/apl.jpg", "/assets/images/ani.jpg", "/assets/images/strawberry.jpg",
                "/assets/images/carr.jpg", "/assets/images/grap.jpg",
                "/assets/images/pot.jpg", "/assets/images/oran.jpg", "/assets/images/mint.jpg",
                "/assets/images/bana.jpg", "/assets/images/man.jpg",
                "/assets/images/ri.jpg", "/assets/images/man.jpg"
        };

        for (String url : imageUrls) {
            ImageView imageView = new ImageView(new Image(url));
            imageView.setFitWidth(IMAGE_WIDTH);
            imageView.setFitHeight(IMAGE_HEIGHT);

            // Apply rounded corners to the image
            Rectangle clip = new Rectangle(IMAGE_WIDTH, IMAGE_HEIGHT);
            clip.setArcWidth(30);
            clip.setArcHeight(30);
            imageView.setClip(clip);

            topImageBox.getChildren().add(imageView);
        }

        scrollPane = new ScrollPane(topImageBox);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("fx-background-radius:100");
        scrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        Label rate = new Label("View Daily Market Updates");
        Label note = new Label("Note: Click On Left Side Market Updates");
        note.setStyle("-fx-font-size: 14px;" + "-fx-text-fill: GREEN;");

        rate.setStyle(
                "-fx-font-family: 'Arial';" +
                        "-fx-font-size: 30px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #2E8B57;" +
                        "-fx-background-color: #F5F5F5;" +
                        "-fx-padding: 10px;" +
                        "-fx-border-color: #4682B4;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 5px;" +
                        "-fx-background-radius: 5px;" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);" +
                        "-fx-alignment: center;" +
                        "-fx-cursor: hand;");
        VBox rateLabelBox = new VBox(10, rate, note);
        rateLabelBox.setStyle(
                "-fx-background-image: url('/assets/images/dashboard.jpeg');"
                        +
                        "-fx-background-size: cover;" +
                        "-fx-background-repeat: no-repeat;" +
                        "-fx-background-position: center center;");
        rateLabelBox.setAlignment(Pos.CENTER);
        rateLabelBox.setPadding(new Insets(150, 0, 300, 0));

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(scrollPane, rateLabelBox);
        // Start automatic scrolling using AnimationTimer
        startAutomaticScrolling();

        return vbox;
    }

    private static void startAutomaticScrolling() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double currentOffset = scrollPane.getHvalue();
                double newOffset = currentOffset + (double) SCROLL_SPEED / topImageBox.getWidth();
                if (newOffset >= 1) {
                    newOffset = 0;
                }
                scrollPane.setHvalue(newOffset);
            }
        }.start();
    }

}