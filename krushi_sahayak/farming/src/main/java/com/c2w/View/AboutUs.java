package com.c2w.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AboutUs {

    public static VBox about() {

        VBox mainContainer = new VBox(0);
        mainContainer.setAlignment(Pos.TOP_LEFT);
        //mainContainer.setStyle("-fx-background-color: #DEFADE;");
        mainContainer.setPadding(new Insets(0));

        // Root pane
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F2FCF1;");
        root.setPrefHeight(1000);
        root.setPadding(new Insets(5));

        Label projectNameLabel = new Label("Project Name: Krushi Sahayak");
        projectNameLabel.setStyle("-fx-background-color: #DEFADE; -fx-pref-width:450px; -fx-background-radius:30;");
        Font fontprojectNameLabel = Font.font("Roboto", FontWeight.BOLD, 30);
        projectNameLabel.setFont(fontprojectNameLabel);

        Label teamNameLabel = new Label("    Team Name: DevMinds");
        teamNameLabel.setStyle("-fx-background-color: #DEFADE; -fx-pref-width:450px; -fx-background-radius:30;");
        Font fontteamNameLabel = Font.font("Roboto", FontWeight.BOLD, 30);
        teamNameLabel.setFont(fontteamNameLabel);

        Label guideNameLabel = new Label("Mentor Name: Rahul Hatkar");
        guideNameLabel.setStyle("-fx-background-color:#DEFADE; -fx-pref-width:450px; -fx-background-radius:30;");
        Font fontguideNameLabel = Font.font("Roboto", FontWeight.BOLD, 30);
        guideNameLabel.setFont(fontguideNameLabel);

        HBox topBox = new HBox(10, projectNameLabel, teamNameLabel, guideNameLabel);
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(0, 30, 0, 30));
        //topBox.setStyle("-fx-background-color: #DEFADE; -fx-background-radius:40; -fx-pref-width:250px;");
        root.setTop(topBox);
        
        Label content = new Label("Concepts Used:");
        Font fontcontentLabel = Font.font("Roboto", FontWeight.BOLD, 36);
        content.setFont(fontcontentLabel);

        Label concept1 = new Label("Conditional Statements");
        Font fontconceptLabel = Font.font("Roboto",30);
        concept1.setFont(fontconceptLabel);

        Label concept2 = new Label("OOP");
        Font fontconcept2Label = Font.font("Roboto", 30);
        concept2.setFont(fontconcept2Label);

        Label concept3 = new Label("Classes & Objects");
        Font fontconcept3Label = Font.font("Roboto", 30);
        concept3.setFont(fontconcept3Label);

        Label concept4 = new Label("Inheritance");
        Font fontconcept4Label = Font.font("Roboto", 30);
        concept4.setFont(fontconcept4Label);

        Label concept5 = new Label("Method Inner Class");
        Font fontconcept5Label = Font.font("Roboto",30);
        concept5.setFont(fontconcept5Label);

        Label concept6 = new Label("Lambda Function");
        Font fontconcept6Label = Font.font("Roboto", 30);
        concept6.setFont(fontconcept6Label);

        Label concept7 = new Label("Exception Handling");
        Font fontconcept7Label = Font.font("Roboto",30);
        concept7.setFont(fontconcept7Label);

        Label devLabel = new Label("Developer Team");
        Font fontdevLabel = Font.font("Roboto",FontWeight.BOLD,30);
        devLabel.setFont(fontdevLabel);


        VBox leftBox = new VBox(20,content,concept1,concept2,concept3,concept4,concept5,concept6,concept7,devLabel);
        leftBox.setPadding(new Insets(50, 0, 0, 20)); 
        //leftBox.setStyle("-fx-background-color:  #DEFADE; -fx-background-radius:40;");
        leftBox.setPrefHeight(100);
        leftBox.setPrefWidth(400);
        root.setLeft(leftBox);


        // Center images
        ImageView teamImage = new ImageView(new Image("/assets/images/mentor.jpg"));
        teamImage.setFitWidth(500);
        teamImage.setFitHeight(600);

        // Clip the image to a rounded rectangle
        Rectangle clip = new Rectangle(teamImage.getFitWidth(), teamImage.getFitHeight());
        clip.setArcWidth(50);
        clip.setArcHeight(50);
        teamImage.setClip(clip);

        VBox centerBox = new VBox(0, teamImage);
        centerBox.setPadding(new Insets(20, 10, 0, 10)); // Adjusted padding
        root.setCenter(centerBox);

        ImageView mentorImage = new ImageView(new Image("/assets/images/Shashi_Sir.jpg"));
        mentorImage.setFitWidth(500);
        mentorImage.setFitHeight(600);

        // Clip the image to a rounded rectangle
        Rectangle mentorClip = new Rectangle(mentorImage.getFitWidth(), mentorImage.getFitHeight());
        mentorClip.setArcWidth(50);
        mentorClip.setArcHeight(50);
        mentorImage.setClip(mentorClip);

        VBox rightBox = new VBox(10, mentorImage);
        rightBox.setAlignment(Pos.TOP_RIGHT);
        rightBox.setPadding(new Insets(20, 20, 0, 5)); // Adjusted padding
        root.setRight(rightBox);

        // Bottom guide name and thank you message
        HBox bottomBox = new HBox(10);
        bottomBox.setAlignment(Pos.BOTTOM_CENTER);
        bottomBox.setPadding(new Insets(10, 0, 20, 20)); // Adjusted padding
        bottomBox.setStyle("-fx-background-radius:40; -fx-pref-width:250px;");
        bottomBox.setPrefHeight(100);
        bottomBox.setPrefWidth(100);
        bottomBox.setAlignment(Pos.BOTTOM_LEFT);

        // Add four VBox elements, each with a different image and label
        String[] imagePaths = {
                "/assets/images/tejas.jpg",
                "/assets/images/keshav.jpg",
                "/assets/images/atharva.jpg",
                "/assets/images/vishal.jpg"
        };
        String[] labels = {"Tejas Bhor", "Keshav Masavle", "Atharva Shinde", "Vishal Shinde"};

        for (int i = 0; i < 4; i++) {
            VBox vbox = new VBox(10);
            vbox.setAlignment(Pos.CENTER);

            ImageView imageView = new ImageView(new Image(imagePaths[i]));
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);

            // Clip the image to a circular shape
            Circle clip1 = new Circle(100, 100, 100);
            imageView.setClip(clip1);

            Label label = new Label(labels[i]);
            label.setFont(new Font("Roboto", 16));

            vbox.getChildren().addAll(imageView, label);
            bottomBox.getChildren().add(vbox);
        }

        Label thankYouLabel = new Label("THANK YOU!");
        Font fontthankYouLabel = Font.font("Roboto", FontWeight.BOLD, 36);
        thankYouLabel.setFont(fontthankYouLabel);
        thankYouLabel.setTextFill(Color.web("#1C791D"));
        thankYouLabel.setPadding(new Insets(0, 200, 0, 0));

        ImageView teamImage1 = new ImageView(new Image("/assets/images/logo1.png"));
        teamImage1.setFitWidth(90);
        teamImage1.setFitHeight(90);
        teamImage1.setLayoutX(100);

        VBox centerBox3 = new VBox(thankYouLabel,teamImage1);
        centerBox3.setStyle("-fx-background-radius:40;");
        centerBox3.setPrefHeight(40);
        centerBox3.setPrefWidth(800);
        centerBox3.setPadding(new Insets(30, 0, 0, 150));
        bottomBox.getChildren().add(centerBox3);

        root.setBottom(bottomBox);

        mainContainer.getChildren().add(root);

        return mainContainer;
    }
}
