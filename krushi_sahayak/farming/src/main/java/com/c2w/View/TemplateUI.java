package com.c2w.View;

import com.c2w.controller.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TemplateUI {
    Shopping sh = new Shopping();
    private Label currentActiveLabel;
    private Stage primaryStage;
    public Scene thisScene;
    private String userName;

    public TemplateUI(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public TemplateUI() {
        mainScene();

    }

    public void mainScene() {
        primaryStage.setTitle("KRUSHI SAHAYAK");

        // Top navigation bar
        HBox topNavBar = new HBox(10);
        topNavBar.setStyle("-fx-background-color: #54D454;");
        topNavBar.setAlignment(Pos.CENTER_LEFT);
        topNavBar.setPrefWidth(1920);

        ImageView logo = new ImageView(new Image("/assets/images/11.png")); // Replace with your logo path
        logo.setFitWidth(260);
        logo.setFitHeight(70);

        HBox profileLabelHBox = new HBox(logo);
        profileLabelHBox.setStyle("-fx-background-color: #32CD32;");
        profileLabelHBox.setPrefWidth(380);
        profileLabelHBox.setPadding(new Insets(10, 50, 10, 50));

        TextField searchBar = new TextField();
        searchBar.setPromptText("Search...");
        searchBar.setPrefWidth(600);
        searchBar.setLayoutX(600);
        searchBar.setStyle("-fx-background-radius: 15;");

        HBox searchBarHBox = new HBox(searchBar);
        searchBarHBox.setAlignment(Pos.TOP_RIGHT);
        searchBarHBox.setPadding(new Insets(20, 0, 15, 0));
        searchBarHBox.setPrefWidth(1480);

        topNavBar.getChildren().addAll(profileLabelHBox, searchBarHBox);

        // Left side menu
        VBox leftMenu = new VBox(10);
        leftMenu.setPadding(new Insets(30, 0, 0, 0));
        leftMenu.setStyle("-fx-background-color: #ffffff;");
        leftMenu.setPrefWidth(380);

        ImageView profileImageView = new ImageView(
                new Image(getClass().getResourceAsStream("/assets/images/farmer_logo.jpeg")));
        profileImageView.setFitWidth(200);
        profileImageView.setFitHeight(150);

        Text userNameLabel = LoginController.userNameMethod(userName);
        Font fontUserLabel = Font.font("Roboto", 27);
        userNameLabel.setFont(fontUserLabel);

        VBox profileBox = new VBox(10, profileImageView, userNameLabel);
        profileBox.setAlignment(Pos.CENTER);

        Separator separator = new Separator();
        separator.setPadding(new Insets(40, 0, 10, 0));

        Label dashboard = createLabelWithHoverEffect("Dashboard", "/assets/images/dash.png", 23);
        Separator separator1 = new Separator();
        separator1.setPadding(new Insets(10, 0, 10, 0));

        Label cropInfo = createLabelWithHoverEffect("Crop Information", "/assets/images/plant.png", 23);
        Separator separator2 = new Separator();
        separator2.setPadding(new Insets(10, 0, 10, 0));

        Label weather = createLabelWithHoverEffect("Weather", "/assets/images/weather.png", 23);
        Separator separator3 = new Separator();
        separator3.setPadding(new Insets(10, 0, 10, 0));

        Label posts = createLabelWithHoverEffect("Posts", "/assets/images/posts.png", 23);
        Separator separator4 = new Separator();
        separator4.setPadding(new Insets(10, 0, 10, 0));

        Label shopping = createLabelWithHoverEffect("Shopping", "/assets/images/shop.png", 23);
        Separator separator5 = new Separator();
        separator5.setPadding(new Insets(10, 0, 10, 0));

        Label marketData = createLabelWithHoverEffect("Market Updates", "/assets/images/shop.png", 23);
        Separator separator7 = new Separator();
        separator7.setPadding(new Insets(10, 0, 10, 0));

        Label about_Us = createLabelWithHoverEffect("About Us", "/assets/images/about_us.png", 23);
        Separator separator6 = new Separator();
        separator6.setPadding(new Insets(10, 0, 10, 0));

        Label signOutButton = createLabelWithHoverEffect("Sign out", "/assets/images/logout.png", 23);
        signOutButton.setPadding(new Insets(50, 0, 10, 50));
        // Separator separator7 = new Separator();
        // separator7.setPadding(new Insets(10, 0, 10, 0));

        leftMenu.getChildren().addAll(profileBox, separator, dashboard, separator1, cropInfo, separator2, weather,
                separator3, posts, separator4, shopping, separator5, marketData, separator7, about_Us, separator6,
                signOutButton);

        // Main content area
        BorderPane mainContent = new BorderPane();
        mainContent.setPadding(new Insets(0));

        // ImageView mainImage = new ImageView(new Image("/assets/images/agri.jpeg"));
        // mainImage.setFitWidth(1500);
        // mainImage.setFitHeight(840);

        VBox contentBox = new VBox();
        contentBox.setAlignment(Pos.TOP_LEFT);
        contentBox.setPrefHeight(3100);
        contentBox.setPrefWidth(1800);

        contentBox.getChildren().add(Dashboard.dash());
        setActiveLabel(dashboard);

        mainContent.setCenter(contentBox);

        // Event handlers for labels
        dashboard.setOnMouseClicked(event -> {
            // contentBox.getChildren().clear();
            // // Add dashboard content here
            // setActiveLabel(dashboard);
            contentBox.getChildren().clear();
            contentBox.getChildren().add(Dashboard.dash());
            setActiveLabel(dashboard);
        });

        cropInfo.setOnMouseClicked(event -> {
            contentBox.getChildren().clear();
            contentBox.getChildren().add(CropInfo.cropinformation());
            setActiveLabel(cropInfo);
        });

        weather.setOnMouseClicked(event -> {
            contentBox.getChildren().clear();
            contentBox.getChildren().add(Weather.createMainWeatherBox());
            setActiveLabel(weather);
        });

        posts.setOnMouseClicked(event -> {
            contentBox.getChildren().clear();
            Posts postobj = new Posts();
            contentBox.getChildren().add(postobj.postsfun());
            setActiveLabel(posts);
        });

        shopping.setOnMouseClicked(event -> {
            contentBox.getChildren().clear();
            contentBox.getChildren().add(sh.shopping);
            setActiveLabel(shopping);
        });

        marketData.setOnMouseClicked(event -> {
            contentBox.getChildren().clear();
            // Shopping sh = new Shopping();
            MarketData.launchWebView();
            setActiveLabel(marketData);
        });

        about_Us.setOnMouseClicked(event -> {
            contentBox.getChildren().clear();
            contentBox.getChildren().add(AboutUs.about());
            setActiveLabel(about_Us);
        });

        signOutButton.setOnMouseClicked(event -> {
            contentBox.getChildren().clear();
            // Add sign out content here
            LoginController obj = new LoginController(primaryStage);
            obj.showLoginScene();
            setActiveLabel(signOutButton);
        });

        // Footer
        HBox footer = new HBox();
        footer.setPadding(new Insets(10));
        footer.setStyle("-fx-background-color: #f2f2f2;");
        Label footerLabel = new Label("Copyright © 2022 CodeLikho. All rights reserved.");
        footer.getChildren().add(footerLabel);
        footer.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setTop(topNavBar);
        root.setLeft(leftMenu);
        root.setCenter(mainContent);
        root.setBottom(footer);

        Scene scene = new Scene(root, 1920, 1080);
        thisScene = scene;
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private ImageView createImageView(String path) {
        Image icon = new Image(getClass().getResourceAsStream(path));
        ImageView imageView = new ImageView(icon);
        imageView.setFitWidth(24);
        imageView.setFitHeight(24);
        return imageView;
    }

    private Label createLabelWithHoverEffect(String text, String imagePath, int fontSize) {
        ImageView imageView = createImageView(imagePath);
        Label label = new Label(text, imageView);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setPadding(new Insets(0, 0, 0, 50));
        Font labelFont = Font.font("Roboto", fontSize);
        label.setFont(labelFont);

        label.setOnMouseEntered(event -> {
            if (label != currentActiveLabel) {
                label.setFont(Font.font("Roboto", FontWeight.BOLD, fontSize));
            }
        });
        label.setOnMouseExited(event -> {
            if (label != currentActiveLabel) {
                label.setFont(Font.font("Roboto", FontWeight.NORMAL, fontSize));
            }
        });

        return label;
    }

    private void setActiveLabel(Label label) {
        if (currentActiveLabel != null) {
            currentActiveLabel
                    .setFont(Font.font("Roboto", FontWeight.NORMAL, ((Font) currentActiveLabel.getFont()).getSize()));
        }
        label.setFont(Font.font("Roboto", FontWeight.BOLD, ((Font) label.getFont()).getSize()));
        currentActiveLabel = label;
    }

}