package com.c2w.Twilio;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsSenderApp extends Application {

    // Twilio credentials
    public static final String ACCOUNT_SID = "AC4091eb5f393a1475554f2c61e7e6d73f";
    public static final String AUTH_TOKEN = "b2f20db5a97ba530f16e456232ead814";
    public static final String TWILIO_NUMBER = "+14172443777";

    // public static void main(String[] args) {
    //     Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
       
    // }

    @Override
    public void start(Stage primaryStage) {
        // primaryStage.setTitle("SMS Sender");

        // GridPane grid = new GridPane();
        // grid.setPadding(new Insets(10, 10, 10, 10));
        // grid.setVgap(8);
        // grid.setHgap(10);

        // // Phone number input
        // Label phoneLabel = new Label("Phone Number:");
        // GridPane.setConstraints(phoneLabel, 0, 0);

        // TextField phoneInput = new TextField();
        // phoneInput.setPromptText("+123456789");
        // GridPane.setConstraints(phoneInput, 1, 0);

        // // Message input
        // Label messageLabel = new Label("Message:");
        // GridPane.setConstraints(messageLabel, 0, 1);

        // TextField messageInput = new TextField();
        // messageInput.setPromptText("Your message");
        // GridPane.setConstraints(messageInput, 1, 1);

        // // Send button
        // Button sendButton = new Button("Send");
        // GridPane.setConstraints(sendButton, 1, 2);

        // sendButton.setOnAction(e -> {
        //     String to = phoneInput.getText();
        //     String message = messageInput.getText();
        //     sendSms(to, message);
        // });

        // grid.getChildren().addAll(phoneLabel, phoneInput, messageLabel, messageInput, sendButton);

        // Scene scene = new Scene(grid, 300, 200);
        // primaryStage.setScene(scene);
        // primaryStage.show();
    }

    public static void sendSms(String to, String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator( new PhoneNumber(to),new PhoneNumber(TWILIO_NUMBER),message).create();
    }
}
