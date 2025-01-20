package com.c2w.initialize;

import com.c2w.controller.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;
public class InitializeApp extends Application {
    // Entry point for the JavaFX application
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create an instance of LoginController with the primary stage
        LoginController loginController = new LoginController(primaryStage);
        
        // Set the initial scene to the login scene from the LoginController
        primaryStage.setScene(loginController.getLoginScene());
        
        // Set the title of the primary stage window
        primaryStage.setTitle("Login");
        // primaryStage.setMaxHeight(800);
        // primaryStage.setMaxWidth(1200);
        primaryStage.setMaximized(true);
        
        // Show the primary stage window
        primaryStage.show();
    }
}

