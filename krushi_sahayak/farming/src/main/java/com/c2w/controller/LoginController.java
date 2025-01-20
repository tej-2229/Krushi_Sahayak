package com.c2w.controller;

import java.util.concurrent.ExecutionException;

import com.c2w.firebaseConfig.FirebaseInit;
import com.c2w.View.TemplateUI;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController {
    private Stage primaryStage;
    private Scene loginScene;
    private Scene userScene;
    private FirebaseInit dataService;
    public static String mail;
    public static String username;
    public static String key;
    public static TextField usernameTextField = new TextField();
    // public static TextField emailPhoneTextField = new TextField();
    public static String email;

    public LoginController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.dataService = new FirebaseInit();
        initScenes();
    }

    private void initScenes() {
        initLoginScene();
    }

    private void initLoginScene() {
        // Logo
        ImageView logo = new ImageView(new Image("/assets/images/11.png")); // Replace with your logo path
        logo.setFitWidth(300);
        logo.setFitHeight(100);

        // Icons
        ImageView usernameIcon = new ImageView(new Image("/assets/images/user.png"));
        usernameIcon.setFitWidth(20);
        usernameIcon.setFitHeight(20);

        ImageView emailIcon = new ImageView(new Image("/assets/images/email.png"));
        emailIcon.setFitWidth(20);
        emailIcon.setFitHeight(20);

        ImageView passIcon = new ImageView(new Image("/assets/images/key.png"));
        passIcon.setFitWidth(20);
        passIcon.setFitHeight(20);

        // Labels
        Label usernameLabel = new Label("Username");
        usernameLabel.setFont(Font.font("Roboto", 17));
        usernameLabel.setPrefWidth(200);
        HBox usernameBox = new HBox(10, usernameIcon, usernameLabel);
        usernameLabel.setStyle("-fx-background-radius: 10px; -fx-padding: 5px;");

        usernameTextField = new TextField();
        usernameTextField.setPrefWidth(200);
        usernameTextField.setStyle("-fx-background-radius: 10px;");

        Label emailPhoneLabel = new Label("Email");
        emailPhoneLabel.setFont(Font.font("Roboto", 17));
        emailPhoneLabel.setPrefWidth(200);
        HBox emailBox = new HBox(10, emailIcon, emailPhoneLabel);
        emailPhoneLabel.setStyle("-fx-background-radius: 10px; -fx-padding: 5px;");

        TextField emailPhoneTextField = new TextField();
        emailPhoneTextField.setPrefWidth(200);
        emailPhoneTextField.setStyle("-fx-background-radius: 10px;");
        email = emailPhoneTextField.getText().toString();

        Label passLabel = new Label("Password");
        passLabel.setFont(Font.font("Roboto", 17));
        passLabel.setPrefWidth(200);

        HBox passBox = new HBox(10, passIcon, passLabel);
        passLabel.setStyle("-fx-background-radius: 10px; -fx-padding: 5px;");

        PasswordField passField = new PasswordField();
        passField.setPrefWidth(400);
        passField.setStyle("-fx-background-radius: 10px;");

        TextField passTextField = new TextField();
        passTextField.setManaged(false);
        passTextField.setVisible(false);
        passTextField.setPrefWidth(400);
        passTextField.setStyle("-fx-background-radius: 10px;");

        ImageView eyeIcon = new ImageView(new Image("/assets/images/eye.png"));
        eyeIcon.setFitWidth(30);
        eyeIcon.setFitHeight(30);
        Button toggleButton = new Button();
        toggleButton.setGraphic(eyeIcon);
        toggleButton.setStyle("-fx-background-color: transparent;");
        toggleButton.setOnAction(event -> {
            if (passField.isVisible()) {
                passTextField.setText(passField.getText());
                passField.setVisible(false);
                passField.setManaged(false);
                passTextField.setVisible(true);
                passTextField.setManaged(true);
            } else {
                passField.setText(passTextField.getText());
                passTextField.setVisible(false);
                passTextField.setManaged(false);
                passField.setVisible(true);
                passField.setManaged(true);
            }
        });

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-radius: 10px;");
        loginButton.setPrefWidth(150);

        Label dontHaveAccountLabel = new Label("Don't have an account?");
        Label signupLabel = new Label("Signup here");
        signupLabel.setStyle("-fx-text-fill: blue; -fx-underline: true;");
        signupLabel.setOnMouseClicked(event -> showSignupScene());

        loginButton.setOnAction(event -> {
            handleLogin(usernameTextField.getText(), emailPhoneTextField.getText(),
                    passField.isVisible() ? passField.getText() : passTextField.getText());
            //usernameTextField.clear();
            // mail=emailPhoneTextField.getText();
            mail = new String(emailPhoneTextField.getText());
            username = new String(usernameTextField.getText());
            System.out.println(mail);
            // mail=new String(emailPhoneTextField.getText());
            // emailPhoneTextField.clear();
            passField.clear();
            passTextField.clear();
        });

        usernameLabel.setStyle("-fx-text-fill:black;");
        emailPhoneLabel.setStyle("-fx-text-fill:black;");
        passLabel.setStyle("-fx-text-fill:black;");
        dontHaveAccountLabel.setStyle("-fx-text-fill:black;");

        VBox fieldBox1 = new VBox(10, usernameBox, usernameTextField);
        fieldBox1.setMaxSize(400, 300);

        VBox fieldBox2 = new VBox(10, emailBox, emailPhoneTextField);
        fieldBox2.setMaxSize(400, 300);

        HBox passwordBox = new HBox(10, passField, passTextField, toggleButton);
        passwordBox.setAlignment(Pos.CENTER_LEFT);
        VBox fieldBox3 = new VBox(10, passBox, passwordBox);
        fieldBox3.setMaxSize(400, 300);

        HBox buttonBox = new HBox(50, loginButton);
        buttonBox.setMaxSize(350, 350);
        buttonBox.setAlignment(Pos.CENTER);

        VBox loginBox = new VBox(20, logo, fieldBox1, fieldBox2, fieldBox3, buttonBox, dontHaveAccountLabel,
                signupLabel);
        loginBox.setStyle(
                "-fx-background-color: transparent; -fx-padding: 20px; -fx-border-radius: 10px; -fx-background-radius: 20px;");
        loginBox.setAlignment(Pos.CENTER);
        loginBox.setMaxWidth(400);
        loginBox.setMinHeight(300);

        VBox loginvbox = new VBox();
        loginvbox.setOpacity(0.5);
        loginvbox.setStyle("-fx-background-color:#E6FAC9; -fx-border-radius: 10px; -fx-background-radius: 60px;");
        loginvbox.setMinWidth(500);
        loginvbox.setMinHeight(400);

        StackPane logiStackPane = new StackPane(loginvbox, loginBox);
        logiStackPane.setAlignment(Pos.CENTER);
        logiStackPane.setMaxHeight(650);

        HBox hbox = new HBox(logiStackPane);
        hbox.setStyle(
                "-fx-background-image: url('https://img.freepik.com/premium-photo/portrait-young-handsome-indian-man_75648-1721.jpg?ga=GA1.1.1324769599.1719474414&semt=sph'); "
                        +
                        "-fx-background-size: cover; " +
                        "-fx-background-position: center center;");
        hbox.setAlignment(Pos.CENTER);
        loginScene = new Scene(hbox, 1920, 1080); // Set scene size to 1920x1080
    }

    // private void initUserScene() {
    // UserPage userPage = new UserPage(dataService);
    // userScene = new Scene(userPage.createUserScene(this::handleLogout), 1200,
    // 800); // Set scene size to 1200x800
    // }

    public static Text userNameMethod(String userName) {
        userName = usernameTextField.getText();
        return new Text(userName);
    }

    public Scene getLoginScene() {
        return loginScene;
    }

    public void showLoginScene() {
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private void handleLogin(String username, String emailOrPhone, String password) {
        try {
            if (dataService.authenticateUser(emailOrPhone, password)) {
                key = emailOrPhone;
                TemplateUI templateUIObj = new TemplateUI(primaryStage);
                templateUIObj.mainScene();
            } else {
                System.out.println("Invalid credentials");
                key = null;
            }
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void showSignupScene() {
        SignupController signupController = new SignupController(this);
        Scene signupScene = SignupController.createSignupScene(primaryStage);
        primaryStage.setScene(signupScene);
        primaryStage.setTitle("Signup");
        primaryStage.show();
    }

    private void handleLogout() {
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
    }
}
