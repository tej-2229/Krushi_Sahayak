package com.c2w.View;

import com.c2w.firebaseConfig.FirebaseInit;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import java.util.List;

public class Posts {
    private String imagePath;
    private String imageName;
    public String usernameTextField;
    public String username;

    public VBox postsfun() {
        BorderPane mainPane = new BorderPane();
        mainPane.setPadding(new Insets(10));

        VBox mainVBox = new VBox();
        mainVBox.setStyle("-fx-background-color: #E6FAC9;");
        mainVBox.setMinWidth(1500);
        mainVBox.setMinHeight(910);
        mainVBox.setPadding(new Insets(25));

        ScrollPane scrollPane = new ScrollPane();
        VBox contentVBox = new VBox();
        contentVBox.setStyle("-fx-background-color: #E6FAC9;");
        contentVBox.setSpacing(40);
        contentVBox.setPadding(new Insets(0, 0, 0, 375));

        fetchAndDisplayPosts(contentVBox);

        scrollPane.setContent(contentVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        Button addPostButton = new Button("Add Post");
        addPostButton.setStyle("-fx-background-color: #29bf12;");
        addPostButton.setPadding(new Insets(10, 20, 10, 20));

        addPostButton.setOnAction(event -> {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Add New Post");

            VBox dialogContent = new VBox(10);
            dialogContent.setMinSize(650, 550);
            dialogContent.setPadding(new Insets(30, 0, 30, 0));

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Image File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));

            Button imageButton = new Button("Choose Image");
            ImageView selectedImageView = new ImageView();
            selectedImageView.setFitWidth(150);
            selectedImageView.setFitHeight(100);

            TextField descriptionField = new TextField();
            descriptionField.setPrefSize(100, 40);
            descriptionField.setStyle(
                    "-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-margin: 30;");
            descriptionField.setPromptText("Enter description");

            TextField usernameTextField = new TextField();
            usernameTextField.setPromptText("Enter your name");

            imageButton.setOnAction(e -> {
                File selectedFile = fileChooser.showOpenDialog(addPostButton.getScene().getWindow());
                imagePath = selectedFile.getPath();
                imageName = selectedFile.getName();
                if (selectedFile != null) {
                    String imageUrl = selectedFile.toURI().toString();
                    selectedImageView.setImage(new Image(imageUrl));
                }
            });

            dialogContent.getChildren().addAll(imageButton, selectedImageView, descriptionField, usernameTextField);
            dialog.getDialogPane().setContent(dialogContent);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            dialog.showAndWait().ifPresent(result -> {
                if (result == ButtonType.OK) {
                    String description = descriptionField.getText();
                    String username = usernameTextField.getText();
                    Image selectedImage = selectedImageView.getImage();
                    if (description != null && !description.isEmpty() && selectedImage != null && username != null) {
                        String imageUrl = FirebaseInit.uploadImages(username, imagePath, imageName, description);
                        if (imageUrl != null) {
                            VBox newPostBox = createPostBox(contentVBox.getChildren().size() + 1, username, imageUrl,
                                    description, imageUrl, 0);
                            contentVBox.getChildren().add(newPostBox);
                        }
                    }
                }
            });
        });

        HBox bottomHBox = new HBox(10, addPostButton);
        bottomHBox.setAlignment(Pos.BOTTOM_CENTER);
        bottomHBox.setPadding(new Insets(15, 0, 0, 0));

        mainVBox.getChildren().addAll(scrollPane, bottomHBox);
        VBox.setVgrow(mainVBox, Priority.ALWAYS);
        mainPane.setCenter(mainVBox);
        return mainVBox;
    }

    private VBox createPostBox(int index, String username, String imagePath, String description, String postId,
            int initialLikeCount) {
        VBox postBox = new VBox(10);
        ImageView img;
        if (imagePath != null) {
            img = new ImageView(new Image(imagePath));
        } else {
            img = new ImageView(new Image("assets\\images\\posts_imgs\\images.jpeg")); // Default image
        }
        img.setFitWidth(750);
        img.setFitHeight(450);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(0);
        dropShadow.setOffsetY(0);
        dropShadow.setRadius(30);
        dropShadow.setSpread(-0.5);
        dropShadow.setColor(Color.rgb(50, 50, 93, 0.50));
        img.setEffect(dropShadow);
        img.setOnMouseEntered(e -> img.setStyle("-fx-opacity: 0.7; -fx-border-color: blue; -fx-border-width: 2px;"));
        img.setOnMouseExited(e -> img.setStyle("-fx-opacity: 1.0; -fx-border-color: none;"));
        img.setOnMouseEntered(e -> img.setStyle("-fx-scale-x: 1.1; -fx-scale-y: 1.03;"));
        img.setOnMouseExited(e -> img.setStyle("-fx-scale-x: 1.0; -fx-scale-y: 1.0;"));

        HBox userNameHBox = new HBox(0);
        Label userName = new Label(username);
        userName.maxWidth(Double.MAX_VALUE);
        Font userNameFont = Font.font("Roboto", 18);
        userName.setFont(userNameFont);
        userNameHBox.getChildren().addAll(userName);

        Label descriptionLabel = new Label(description);
        descriptionLabel.setWrapText(true);
        descriptionLabel.setPadding(new Insets(6, 0, 0, 2));
        Font descriptionFont = Font.font("Roboto", 18);
        descriptionLabel.setFont(descriptionFont);

        VBox.setVgrow(descriptionLabel, Priority.ALWAYS);
        VBox descriptionBox = new VBox(descriptionLabel);
        descriptionBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
        descriptionBox.setMinHeight(Region.USE_PREF_SIZE);
        descriptionBox.setPrefWidth(688);
        descriptionBox.setStyle("-fx-background-color: #E6FAC9;");

        final class HeartLikeButton extends Button {
            private boolean isLiked = false;
            private int likeCount = initialLikeCount;
            private final Label likeCountLabel = new Label(String.valueOf(likeCount));

            public HeartLikeButton() {
                SVGPath path = new SVGPath();
                path.setContent(
                        "M23.6,0c-3.4,0-6.3,2.7-7.6,5.6C14.7,2.7,11.8,0,8.4,0C3.8,0,0,3.8,0,8.4c0,9.4,9.5,11.9,16,21.2c6.1-9.3,16-12.1,16-21.2C32,3.8,28.2,0,23.6,0z");
                path.setScaleX(1); // Adjust size
                path.setScaleY(1);
                path.setFill(Color.LIGHTGREY);
                setGraphic(path);
                setStyle("-fx-border-color: none; -fx-background-color: transparent;");
                setOnAction(event -> {
                    if (isLiked) {
                        path.setFill(Color.LIGHTGREY);
                        likeCount--;
                    } else {
                        path.setFill(Color.RED);
                        likeCount++;
                    }
                    isLiked = !isLiked;
                    likeCountLabel.setText(String.valueOf(likeCount));
                    updateLikeCountInFirestore(postId, likeCount);
                });
            }

            private void updateLikeCountInFirestore(String postId, int likeCount) {
                Firestore db = FirebaseInit.getFirestore();
                db.collection("posts").document(postId).update("likeCount", likeCount);
                // .addOnSuccessListener(aVoid -> System.out.println("Like count updated
                // successfully"))
                // .addOnFailureListener(e -> System.err.println("Error updating like count: " +
                // e));
            }

            public Label getLikeCountLabel() {
                return likeCountLabel;
            }
        }

        HeartLikeButton heartButton = new HeartLikeButton();
        HBox hb = new HBox(10, heartButton, heartButton.getLikeCountLabel(), descriptionBox);
        postBox.getChildren().addAll(userNameHBox, img, hb);

        return postBox;
    }

    public void fetchAndDisplayPosts(VBox contentVBox) {
        FirestoreOptions firestoreOptions = null;
        Firestore db = null;
        try {
            firestoreOptions = FirestoreOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(new FileInputStream(
                            "farming\\src\\main\\resources\\krushi.json")))
                    .setProjectId("krushi-sahayak-5c2db")
                    .build();
            db = firestoreOptions.getService();

            ApiFuture<QuerySnapshot> query = db.collection("posts").get();
            List<QueryDocumentSnapshot> documents = query.get().getDocuments();
            for (DocumentSnapshot document : documents) {
                String imageUrl = document.getString("imageUrl");
                String description = document.getString("description");
                String username = document.getString("usernameTextField");
                String postId = document.getId();
                int likeCount = document.contains("likeCount") ? document.getLong("likeCount").intValue() : 0;

                VBox postBox = createPostBox(contentVBox.getChildren().size() + 1, username, imageUrl, description,
                        postId, likeCount);
                contentVBox.getChildren().add(postBox);
            }
        } catch (InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                try {
                    db.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
