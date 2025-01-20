package com.c2w.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.c2w.firebaseConfig.FirebaseInit;

public class SignupController {
    private static LoginController loginController;

    public SignupController(LoginController loginController) {
        SignupController.loginController = loginController;
    }

    public static Scene createSignupScene(Stage primaryStage) {
        Image logo = new Image("/assets/images/11.png"); 
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(250);
        logoView.setFitHeight(180);
        logoView.setPreserveRatio(true);

        Label usernameLabel = new Label("Username:");
        usernameLabel.setGraphic(createIcon("/assets/images/user.png"));
        TextField usernameTextField = new TextField();
        usernameTextField.setPrefWidth(400);
        usernameTextField.setStyle("-fx-background-radius:10;");

        Label emailLabel = new Label("Email:");
        emailLabel.setGraphic(createIcon("/assets/images/email.png"));
        TextField emailTextField = new TextField();
        emailTextField.setPrefWidth(400);
        emailTextField.setStyle("-fx-background-radius:10;");

        Label phoneLabel = new Label("Phone Number:");
        phoneLabel.setGraphic(createIcon("/assets/images/call.png"));
        TextField phoneTextField = new TextField();
        phoneTextField.setPrefWidth(400);
        phoneTextField.setStyle("-fx-background-radius:10;");

        Label countryLabel = new Label("Country:");
        countryLabel.setGraphic(createIcon("/assets/images/flag.png"));
        ComboBox<String> countryComboBox = new ComboBox<>(getCountries());
        countryComboBox.setPromptText("Select Country");
        countryComboBox.setPrefWidth(700);
        countryComboBox.setStyle("-fx-background-radius:10;");

        Label stateLabel = new Label("State:");
        stateLabel.setGraphic(createIcon("/assets/images/flag.png"));
        ComboBox<String> stateComboBox = new ComboBox<>();
        stateComboBox.setPromptText("Select State");
        stateComboBox.setPrefWidth(700);
        stateComboBox.setStyle("-fx-background-radius:10;");

        Label regionLabel = new Label("Region:");
        regionLabel.setGraphic(createIcon("/assets/images/flag.png"));
        ComboBox<String> regionComboBox = new ComboBox<>();
        regionComboBox.setPromptText("Select Region");
        regionComboBox.setPrefWidth(700);
        regionComboBox.setStyle("-fx-background-radius:10;");

        countryComboBox.setOnAction(event -> updateStates(countryComboBox.getValue(), stateComboBox));
        stateComboBox.setOnAction(event -> updateRegions(stateComboBox.getValue(), regionComboBox));

        Label passLabel = new Label("Password:");
        passLabel.setGraphic(createIcon("/assets/images/key.png"));
        PasswordField passField = new PasswordField();
        passField.setPrefWidth(400);
        passField.setStyle("-fx-background-radius:10;");

        Label confirmPassLabel = new Label("Confirm Password:");
        confirmPassLabel.setGraphic(createIcon("/assets/images/confirmicon.png"));
        PasswordField confirmPassField = new PasswordField();
        confirmPassField.setPrefWidth(400);
        confirmPassField.setStyle("-fx-background-radius:10;");

        Button signupButton = new Button("Signup");
        signupButton.setPrefWidth(150);
        Label loginLabel = new Label("Already have an account? Login here.");
        loginLabel.setStyle("-fx-text-fill: blue; -fx-underline: true;");
        loginLabel.setOnMouseClicked(event -> loginController.showLoginScene());

        CheckBox showPasswordCheckBox = new CheckBox("Show Password");
        showPasswordCheckBox.setStyle("-fx-background-radius:20;");
        showPasswordCheckBox.setOnAction(event -> {
            if (showPasswordCheckBox.isSelected()) {
                passField.setPromptText(passField.getText());
                passField.setText("");
                confirmPassField.setPromptText(confirmPassField.getText());
                confirmPassField.setText("");
            } else {
                passField.setText(passField.getPromptText());
                confirmPassField.setText(confirmPassField.getPromptText());
            }
        });

        VBox fieldBox1 = new VBox(5, usernameLabel, usernameTextField);
        fieldBox1.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-padding: 6px;");
        VBox fieldBox2 = new VBox(5, emailLabel, emailTextField);
        fieldBox2.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-padding: 6px;");
        VBox fieldBox3 = new VBox(5, phoneLabel, phoneTextField);
        fieldBox3.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-padding: 6px;");
        VBox fieldBox4 = new VBox(5, countryLabel, countryComboBox);
        fieldBox4.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-padding: 6px;");
        VBox fieldBox5 = new VBox(5, stateLabel, stateComboBox);
        fieldBox5.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-padding: 6px;");
        VBox fieldBox6 = new VBox(5, regionLabel, regionComboBox);
        fieldBox6.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-padding: 6px;");
        VBox fieldBox7 = new VBox(5, passLabel, passField, showPasswordCheckBox);
        fieldBox7.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-padding: 6px;");
        VBox fieldBox8 = new VBox(5, confirmPassLabel, confirmPassField);
        fieldBox8.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-padding: 10px;");
        HBox buttonBox = new HBox(50, signupButton);
        buttonBox.setAlignment(Pos.CENTER);

        signupButton.setOnAction(event -> handleSignup(primaryStage, usernameTextField.getText(), emailTextField.getText(), phoneTextField.getText(),
                countryComboBox.getValue(), stateComboBox.getValue(), regionComboBox.getValue(), passField.getText(), confirmPassField.getText()));

        VBox signupvbox = new VBox(10, logoView, fieldBox1, fieldBox2, fieldBox3, fieldBox4, fieldBox5, fieldBox6, fieldBox7, fieldBox8, buttonBox, loginLabel);
        signupvbox.setAlignment(Pos.CENTER);
        signupvbox.setStyle("-fx-background-color: transparent; -fx-padding: 20px;");
        signupvbox.setPrefWidth(600);
        signupvbox.setPrefHeight(200);

        VBox signupVBox = new VBox();
        signupVBox.setOpacity(0.5);
        signupVBox.setStyle("-fx-background-color:#E6FAC9; -fx-border-radius: 10px; -fx-background-radius: 60px;");
        signupVBox.setMinWidth(600);
        signupVBox.setPrefHeight(200);

        StackPane signUpStackPane = new StackPane(signupVBox, signupvbox);
        signUpStackPane.setAlignment(Pos.CENTER);
        signUpStackPane.setMaxHeight(500);

        HBox hbox = new HBox(signUpStackPane);
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle(
                "-fx-background-image: url('https://img.freepik.com/premium-photo/portrait-young-handsome-indian-man_75648-1721.jpg?ga=GA1.1.1324769599.1719474414&semt=sph'); " +
                        "-fx-background-size: cover; " +
                        "-fx-background-position: center center;"
        );
        hbox.setPrefSize(1920, 1080);

        Scene signupScene = new Scene(hbox, 1920, 1080);
        primaryStage.setScene(signupScene);
        primaryStage.setTitle("Signup");
        primaryStage.show();

        return signupScene;
    }
  

    private static ImageView createIcon(String imagePath) {
        ImageView icon = new ImageView(new Image(imagePath));
        icon.setFitWidth(16); // Set the desired width
        icon.setFitHeight(16); // Set the desired height
        return icon;
    }

    private static void handleSignup(Stage primaryStage, String username, String email, String phone, String country, String state, String region, String password, String confirmPassword) {
        if (username.isEmpty() || email.isEmpty() || phone.isEmpty() || country == null || state == null || region == null || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert("Incomplete Form", "All fields are compulsory. Please fill in all fields.");
            return;
        }

        if (!isValidEmail(email)) {
            showAlert("Invalid Email", "Please enter a valid email address.");
            return;
        }

        if (!isValidPhoneNumber(phone)) {
            showAlert("Invalid Phone Number", "Please enter a 10-digit phone number.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert("Password Mismatch", "Passwords do not match.");
            return;
        }

        try {
            FirebaseInit dataService = new FirebaseInit();
            Map<String, Object> data = new HashMap<>();
            data.put("username", username);
            data.put("email", email);
            data.put("phone", phone);
            data.put("country", country);
            data.put("state", state);
            data.put("region", region);
            data.put("password", password);
            dataService.addData("users", email, data);
            System.out.println("User registered successfully");
            loginController.showLoginScene();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private static boolean isValidPhoneNumber(String phone) {
        return phone != null && phone.matches("\\d{10}");
    }

    private static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static ObservableList<String> getCountries() {
        return FXCollections.observableArrayList("USA", "Canada", "India");
    }

    private static void updateStates(String country, ComboBox<String> stateComboBox) {
        ObservableList<String> states = FXCollections.observableArrayList();
        if (country.equals("USA")) {
            states.addAll("California", "Texas", "New York");
        } else if (country.equals("Canada")) {
            states.addAll("Ontario", "Quebec", "British Columbia");
        } else if (country.equals("India")) {
            states.addAll("Maharashtra", "Karnataka", "Delhi");
        }
        stateComboBox.setItems(states);
    }

    private static void updateRegions(String state, ComboBox<String> regionComboBox) {
        ObservableList<String> regions = FXCollections.observableArrayList();
        if (state.equals("California")) {
            regions.addAll("Los Angeles", "San Francisco", "San Diego");
        } else if (state.equals("Texas")) {
            regions.addAll("Houston", "Dallas", "Austin");
        } else if (state.equals("New York")) {
            regions.addAll("New York City", "Buffalo", "Rochester");
        } else if (state.equals("Ontario")) {
            regions.addAll("Toronto", "Ottawa", "Mississauga");
        } else if (state.equals("Quebec")) {
            regions.addAll("Montreal", "Quebec City", "Laval");
        } else if (state.equals("British Columbia")) {
            regions.addAll("Vancouver", "Victoria", "Richmond");
        } else if (state.equals("Maharashtra")) {
            regions.addAll("Mumbai", "Pune", "Nagpur");
        } else if (state.equals("Karnataka")) {
            regions.addAll("Bangalore", "Mysore", "Mangalore");
        } else if (state.equals("Delhi")) {
            regions.addAll("Central Delhi", "North Delhi", "South Delhi");
        }
        regionComboBox.setItems(regions);
    }
}
