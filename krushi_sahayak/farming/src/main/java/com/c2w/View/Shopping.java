package com.c2w.View;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;
import com.c2w.Twilio.SmsSenderApp;
import com.c2w.controller.LoginController;
import com.c2w.firebaseConfig.FirebaseInit;
import com.google.api.client.auth.oauth2.TokenRequest;
import com.twilio.Twilio;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Shopping extends Application {

    public VBox shopping = shoppingpage();//D:\Downloads\Working Project\Working Project\Original\krushi_sahayak\farming\src\main\java\com\c2w\View\Shopping.java

    // public VBox add_page=add_page(st);
    // public VBox shop_page=shop(shopping,"rent","tractor");
    private static String image_path;

    public VBox shoppingpage() {
        // Rent
        ImageView tractor = createImageView("/assets/images/tractor.jpg");
        ImageView harvester = createImageView("/assets/images/harvester.jpg");
        ImageView powertiller = createImageView("/assets/images/powertiller.jpg");
        ImageView seeddriller = createImageView("/assets/images/seeddriller.jpg");
        ImageView wheat = createImageView_1("/assets/images/wheat.jpg");
        ImageView bajra = createImageView_1("/assets/images/bajra.jpg");
        ImageView jowar = createImageView_1("/assets/images/jowar.jpg");
        ImageView rice = createImageView_1("/assets/images/rice.jpg");
        ImageView urea = createImageView_1("/assets/images/urea.jpg");
        ImageView phosphate = createImageView_1("/assets/images/phosphate.jpg");
        ImageView potassium = createImageView_1("/assets/images/potassium.jpg");
        ImageView calcium = createImageView_1("/assets/images/calcium.jpg");

        Label add = new Label("Add");
        Label my_list = new Label("My List");
        Label my_orders = new Label("My Orders");
        HBox top_1 = new HBox(25, add, my_list, my_orders);
        top_1.setStyle("-fx-background-color:LIGHTGREY;-fx-background-radius:10;");
        top_1.setMinHeight(30);
        top_1.setMinWidth(400);
        top_1.setAlignment(Pos.CENTER_RIGHT);
        top_1.setPadding(new Insets(0, 0, 0, 0));// buttons.setStyle();

        Label back = new Label("BACK");
        HBox top_2 = new HBox(25, back);
        top_2.setStyle("-fx-background-color:LIGHTGREY;-fx-background-radius:10;");
        top_2.setMinHeight(30);
        top_2.setMinWidth(300);
        top_2.setAlignment(Pos.CENTER_LEFT);
        top_2.setPadding(new Insets(0, 0, 0, 20));// buttons.setStyle();
        HBox top = new HBox(500, top_2, top_1);
        top_1.setPadding(new Insets(0, 0, 0, 30));
        top.setStyle("-fx-background-color:LIGHTGREY;-fx-background-radius:10;");
        top.setMinWidth(1500);

        // RENT AND BUY HBOX

        Label buy_seeds_label = new Label("Purchase seeds with upto 30% off");
        buy_seeds_label.setFont(new Font(30));
        HBox buy_seeds_images_1 = new HBox(100, wheat, bajra);
        Label buy_seeds_label_1 = new Label("Wheat seeds                                          Bajra seeds");
        buy_seeds_label_1.setFont(new Font(20));
        HBox buy_seeds_images_2 = new HBox(100, jowar, rice);
        Label buy_seeds_label_2 = new Label("Jowar seeds                                          Rice seeds");
        buy_seeds_label_2.setFont(new Font(20));
        VBox buy_seeds = new VBox(5, buy_seeds_label, buy_seeds_images_1, buy_seeds_label_1, buy_seeds_images_2,
                buy_seeds_label_2);
        buy_seeds.setStyle("-fx-background-color:WHITE");
        buy_seeds.setAlignment(Pos.TOP_LEFT);
        buy_seeds.setMinWidth(750);
        buy_seeds.setPadding(new Insets(5, 50, 5, 50));// buy_seeds.setMaxHeight(200);

        Label buy_fertilizers_label = new Label("Purchase Fertilizers with upto 25% off");
        buy_fertilizers_label.setFont(new Font(25));
        HBox buy_fertilizers_images_1 = new HBox(100, urea, phosphate);
        Label buy_fertilizers_label_1 = new Label(
                "Urea                                                       Phosphate");
        buy_fertilizers_label_1.setFont(new Font(20));
        HBox buy_fertilizers_images_2 = new HBox(100, potassium, calcium);
        Label buy_fertilizers_label_2 = new Label("Potassium                                             Calcium");
        buy_fertilizers_label_2.setFont(new Font(20));
        VBox buy_fertilizers = new VBox(5, buy_fertilizers_label, buy_fertilizers_images_1, buy_fertilizers_label_1,
                buy_fertilizers_images_2, buy_fertilizers_label_2);
        buy_fertilizers.setStyle("-fx-background-color:WHITE");
        buy_fertilizers.setAlignment(Pos.TOP_LEFT);
        buy_fertilizers.setMinWidth(750);
        buy_fertilizers.setPadding(new Insets(5, 50, 5, 50));// buy_seeds.setMaxHeight(200);
        HBox buy = new HBox(10, buy_seeds, buy_fertilizers);
        buy.setStyle("-fx-background-color:LIGHTGREY;-fx-background-radius:10;");
        buy.setMaxHeight(450);
        buy.setMinWidth(750);
        buy.setPadding(new Insets(5));
        // Combining seeds & fertilizers

        Label rent_name = new Label(
                "  Tractor                                                  Harvester                                                 Power Tiller                                         Seed Driller   ");
        rent_name.setFont(new Font(20));
        Text rent_text = new Text(" RENT on Machines at an affordable rate ");
        rent_text.setFont(new Font(30));
        HBox rent_images = new HBox(40, tractor, harvester, powertiller, seeddriller);
        rent_images.setStyle("-fx-background-color:WHITE");
        rent_images.setAlignment(Pos.CENTER_LEFT);
        rent_images.setMinHeight(150);
        rent_images.setPadding(new Insets(10));
        VBox rent = new VBox(rent_text, rent_images, rent_name);
        rent.setStyle("-fx-background-color:white;-fx-background-radius:10;");
        rent.setAlignment(Pos.BOTTOM_LEFT);
        rent.setPadding(new Insets(20));
        rent.setMaxHeight(475);
        rent.setMaxWidth(1500);

        // ------------------------------------------------------------------------------------------------------
        ImageView aloevera = createImageView_2("/assets/images/aloevera_sapling.jpeg");
        ImageView curryleaves = createImageView_2("/assets/images/curryleaves_sapling.jpeg");
        ImageView tulsi = createImageView_2("/assets/images/tulsi_sapling.jpeg");
        ImageView neem = createImageView_2("/assets/images/neem_sapling.jpeg");
        Label buy_herbs_label = new Label("Purchase Herbs with upto 25% off");
        buy_herbs_label.setFont(new Font(30));
        HBox buy_herbs_image_1 = new HBox(100, aloevera, curryleaves);
        Label buy_herbs_label_1 = new Label(
                "Aloevera                                                           Curryleaves");
        buy_herbs_label_1.setFont(new Font(20));
        HBox buy_herbs_image_2 = new HBox(100, neem, tulsi);
        Label buy_herbs_label_2 = new Label("Neem                                                               Tulsi");
        buy_herbs_label_2.setFont(new Font(20));
        VBox buy_herbs = new VBox(5, buy_herbs_label, buy_herbs_image_1, buy_herbs_label_1, buy_herbs_image_2,
                buy_herbs_label_2);
        buy_herbs.setStyle("-fx-background-color:WHITE;-fx-background-radius:10;");
        buy_herbs.setAlignment(Pos.TOP_LEFT);
        buy_herbs.setMaxHeight(480);
        buy_herbs.setPadding(new Insets(5, 20, 5, 20));// buy_seeds.setMaxHeight(200);

        ImageView mango = createImageView("/assets/images/mango_sapling.jpeg");
        ImageView papaya = createImageView("/assets/images/papaya_sapling.jpeg");
        ImageView banana = createImageView("/assets/images/banana_sapling.jpeg");
        ImageView guava = createImageView("/assets/images/guava_sapling.jpeg");
        ImageView dragonfruit = createImageView("/assets/images/dragonfruit_sapling.jpeg");
        ImageView orange = createImageView("/assets/images/orange_sapling.jpeg");
        Label buy_fruits_label = new Label("Purchase Saplings with upto 20% off");
        buy_fruits_label.setFont(new Font(30));
        HBox buy_fruits_image_1 = new HBox(100, mango, papaya);
        Label buy_fruits_label_1 = new Label("Mango                                                Papaya");
        buy_fruits_label_1.setFont(new Font(25));
        HBox buy_fruits_image_2 = new HBox(100, banana, guava);
        Label buy_fruits_label_2 = new Label("Banana                                               Guava");
        buy_fruits_label_2.setFont(new Font(25));
        HBox buy_fruits_image_3 = new HBox(100, dragonfruit, orange);
        Label buy_fruits_label_3 = new Label("Dragon Fruit                                        Orange");
        buy_fruits_label_3.setFont(new Font(25));
        VBox buy_fruits = new VBox(20, buy_fruits_label, buy_fruits_image_1, buy_fruits_label_1, buy_fruits_image_2,
                buy_fruits_label_2, buy_fruits_image_3, buy_fruits_label_3);
        buy_fruits.setStyle("-fx-background-color:WHITE;-fx-background-radius:10;");
        buy_fruits.setAlignment(Pos.TOP_LEFT);
        buy_fruits.setMinHeight(620);
        buy_fruits.setPadding(new Insets(5, 20, 25, 20));// buy_seeds.setMaxHeight(200);
        // VBox buy_1=new VBox(10,buy_fruits);
        // buy_1.setStyle("-fx-background-color:LIGHTGREY;-fx-background-radius:10;");buy_1.setMaxHeight(380);buy_1.setMaxWidth(1200);buy_1.setPadding(new
        // Insets(5));

        HBox hb2 = new HBox(10, buy_herbs, buy_fruits);
        hb2.setStyle("-fx-background-color:rgbo(250, 249, 246,0.5)");
        hb2.setMinSize(1500, 390);
        hb2.setPadding(new Insets(10));
        // ----------------------------------------------------------------------------------------
        Text tech_text = new Text(" Buy Farming Objects with upto 15% off ");
        tech_text.setFont(new Font(30));
        ImageView drone = createImageView("/assets/images/drone.jpeg");
        ImageView sprinkle = createImageView("/assets/images/sprinkle.jpeg");
        ImageView footpump = createImageView("/assets/images/footpump.jpeg");
        ImageView drip = createImageView("/assets/images/drip.jpeg");
        Label object_name = new Label(
                "  Drone                                                       Sprinklers                                             Foot Pumps                                        Drippers");
        object_name.setFont(new Font(20));
        HBox object_images = new HBox(30, drone, sprinkle, footpump, drip);
        object_images.setStyle("-fx-background-color:WHITE");
        object_images.setAlignment(Pos.CENTER_LEFT);
        object_images.setMinHeight(150);
        object_images.setPadding(new Insets(10, 30, 10, 30));
        VBox object = new VBox(30, tech_text, object_images, object_name);
        object.setStyle("-fx-background-color:white;-fx-background-radius:10;");
        object.setAlignment(Pos.BOTTOM_LEFT);
        object.setPadding(new Insets(20, 30, 20, 30));
        object.setMaxHeight(550);
        object.setMaxWidth(1500);

        // ---------------------------------------------------------------------------------------------------------
        VBox shoppingpage = new VBox(15, top, buy, rent, hb2, object);
        shoppingpage.setStyle("-fx-background-color:rgbo(250, 249, 246,0.5)");
        shoppingpage.setPadding(new Insets(5));
        // shoppingpage.setMaxHeight(700);

        shoppingpage.setMinWidth(1500);
        shoppingpage.setMinHeight(2500);
        // shoppingpage.setMinWidth(1299);
        ScrollPane shoppingPane = new ScrollPane();
        shoppingPane.setContent(shoppingpage);
        // shoppingPane.setPadding(new Insets(0, 20, 0, 130));

        add.setOnMouseClicked(event -> {

            Stage st = new Stage();
            st.setMinWidth(1000);
            st.setMinHeight(500);
            Scene sc = new Scene(add_page(st));
            st.setScene(sc);
            // TemplateUI.shopping=add_page(st);
            st.show();

        });
        tractor.setOnMouseClicked(event -> {
            // this.shopping.getChildren().clear();
            shop(this.shopping, "rent", "tractor");
            // shoppingpage.getChildren().clear();
            // shoppingpage.getChildren().add(shop(shoppingpage,"rent","tractor"));
            System.out.println("tractor executed");
        });
        potassium.setOnMouseClicked(event -> {
            shop(this.shopping, "sell", "potassium");
            // shoppingpage.getChildren().add(shop(shoppingpage,"sell","urea"));
        });

        // ScrollPane shoppingPane=new
        // ScrollPane();shoppingPane.setContent(shoppingpage);
        shoppingPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        shoppingPane.setMinWidth(1200);
        shoppingPane.setMaxHeight(2500);
        VBox root = new VBox(shoppingPane);

        // tractor.setOnMouseClicked(event -> {
        // this.shopping.getChildren().clear();
        // shop(this.shopping, "rent", "tractor");
        // // shoppingpage.getChildren().clear();
        // // shoppingpage.getChildren().add(shop(shoppingpage,"rent","tractor"));
        // });
        drone.setOnMouseClicked(event -> {
            this.shopping.getChildren().clear();
            shop(this.shopping, "sell", "drone");
            // shoppingpage.getChildren().clear();
            // shoppingpage.getChildren().add(shop(shoppingpage,"rent","tractor"));
        });
        return root;

    }

    private ImageView createImageView(String path) {
        Image icon = new Image(path);
        ImageView imageView = new ImageView(icon);
        imageView.setFitHeight(200);
        imageView.setFitWidth(300);
        return imageView;
    }

    private ImageView createImageView_2(String path) {
        Image icon = new Image(path);
        ImageView imageView = new ImageView(icon);
        imageView.setFitHeight(400);
        imageView.setFitWidth(300);
        return imageView;
    }

    private ImageView createImageView_1(String path) {
        Image icon = new Image(path);
        ImageView imageView = new ImageView(icon);
        imageView.setFitHeight(150);
        imageView.setFitWidth(240);
        return imageView;
    }

    public VBox add_page(Stage primaryStage) {
        primaryStage.setHeight(600);
        primaryStage.setWidth(1500);
        VBox root_ = new VBox(70);
        root_.setAlignment(Pos.CENTER);
        HBox root__ = new HBox(30);
        root_.setPadding(new Insets(20));
        VBox root = new VBox(10);
        root.setMinHeight(500);
        root.setStyle("-fx-background-color:WHITE");
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));

        // Image view for displaying chosen image

        ImageView imageView = new ImageView();

        imageView.setFitWidth(300);
        imageView.setFitHeight(300);

        // Button to choose an image file
        Button chooseImageButton = new Button("Choose Image");

        // Text fields for name, region, rent price
        TextField nameField = new TextField();
        nameField.setMinSize(500, 30);
        nameField.setPromptText("Name");
        Label addLabel = new Label("Name:");

        TextField regionField = new TextField();
        regionField.setMinSize(300, 30);
        regionField.setPromptText("Region");
        Label regionLabel = new Label("Region:");
        TextField locationField = new TextField();
        locationField.setMinSize(300, 30);
        locationField.setPromptText("Region");
        Label locationLabel = new Label("Location:");
        HBox address_label = new HBox(350, locationLabel, regionLabel);
        HBox address_box = new HBox(100, locationField, regionField);
        VBox address = new VBox(address_label, address_box);

        ChoiceBox<String> choicebox = new ChoiceBox<String>();
        choicebox.setMinWidth(200);
        choicebox.setItems(FXCollections.observableArrayList("Rent", "Sell"));
        choicebox.setValue("Rent");

        ChoiceBox<String> choicebox_2 = new ChoiceBox<String>();
        choicebox_2.setMinWidth(200);
        choicebox_2.setItems(FXCollections.observableArrayList("Tractor", "Harvester", "PowerTiller", "SeedDriller"));
        choicebox_2.setValue("Tractor");
        HBox choiceboxes = new HBox(200, choicebox, choicebox_2);

        TextField modelField = new TextField();
        modelField.setMinSize(300, 30);
        modelField.setPromptText("Model");
        Label modelLabel = new Label("Model:");
        TextField registrationField = new TextField();
        registrationField.setMinSize(300, 30);
        registrationField.setPromptText("Registration Year");
        Label registrationLabel = new Label("Registration Year");
        TextField mobile_no = new TextField();
        mobile_no.setPromptText("Enter Mobile no");
        mobile_no.setMaxWidth(300);
        HBox type_label = new HBox(365, modelLabel, registrationLabel);
        HBox type_box = new HBox(100, modelField, registrationField);
        VBox type = new VBox(type_label, type_box);

        TextField rentField = new TextField();
        rentField.setMaxSize(500, 50);
        rentField.setPromptText("Price");
        Label rentLabel = new Label("Rent Price per Day:");

        // Buttons for add and remove
        Button addButton = new Button("Add");
        Button removeButton = new Button("Cancel");
        HBox add_cancel = new HBox(20, addButton, removeButton);
        add_cancel.setAlignment(Pos.CENTER);

        chooseImageButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Image File");
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                Image image = new Image(file.toURI().toString());
                image_path = file.getPath();

                imageView.setImage(image);
                // ImageUploader.uploadImage(file.getPath(), nameField.getText(),);

            }
        });

        // Add functionality to buttons (placeholder)

        choicebox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == "Rent") {
                rentField.setPromptText("Rent Price per Day");
                rentLabel.setText("Rent Price per Day");
                choicebox_2.setItems(
                        FXCollections.observableArrayList("Tractor", "Harvester", "PowerTiller", "SeedDriller"));
                choicebox_2.setValue("Tractor");
            } else {
                rentField.setPromptText("Sell Price per Kg");
                rentLabel.setText("Sell Price per Kg");
                choicebox_2.setItems(FXCollections.observableArrayList("Wheat", "Jowar", "Bajra", "Rice", "Urea",
                        "Phosphate", "Potassium", "Calcium"));
                choicebox_2.setValue("Urea");
            }
        });
        addButton.setOnAction(e -> {
            // Add functionality here
            try {
                // FirebaseInit obj_1 = new FirebaseInit();
                // FirebaseInit.initializeFirebase();
                String info = nameField.getText() + "," + locationField.getText() + "," + regionField.getText() + ","
                        + modelField.getText() + "," + registrationField.getText() + "," + rentField.getText() + ","
                        + mobile_no.getText();
                System.out.println(info);
                ImageUploader.uploadImage(image_path,
                        (nameField.getText() + choicebox.getValue() + choicebox_2.getValue()).toLowerCase(),
                        choicebox.getValue().toLowerCase() + "/" + choicebox_2.getValue().toLowerCase());
                System.out
                        .println(choicebox.getValue().toLowerCase() + "/" + choicebox_2.getValue().toLowerCase() + "/");
                System.out.println(choicebox.getValue().toLowerCase() + choicebox_2.getValue().toLowerCase());
                FirebaseInit.updateRec(choicebox.getValue().toLowerCase(), choicebox_2.getValue().toLowerCase(),
                        (nameField.getText() + choicebox.getValue() + choicebox_2.getValue()).toLowerCase(), info);
                System.out.println("doneeeee");
                primaryStage.close();
            } finally {
            }

        });

        removeButton.setOnAction(e -> {
            primaryStage.close();
        });

        // Add components to layout
        root.getChildren().addAll(
                addLabel, nameField,
                address, new Text("Rent/Sell"),
                choiceboxes, type, rentLabel, rentField, new Text("Enter Mobile no:"), mobile_no, add_cancel);
        root_.getChildren().addAll(imageView, chooseImageButton);
        root__.getChildren().addAll(root_, root);
        root__.setMinWidth(1000);
        root__.setAlignment(Pos.CENTER);
        VBox root_final = new VBox(root__);
        return root_final;
    }

    private void shop(VBox shoppingpage, String buy_rent, String object) {
        // shoppingpage.getChildren().clear();
        Button back_to_shopping_page = new Button("Back");
        back_to_shopping_page.setPadding(new Insets(25,20,20,25));
        HBox back_to_shopping_page_HBox = new HBox(back_to_shopping_page);
        VBox maincontent = new VBox(20);
        maincontent.setPadding(new Insets(20));
        ScrollPane scroll = new ScrollPane();
        scroll.setMinHeight(800);
        scroll.setMinWidth(1300);
        FirebaseInit obj = new FirebaseInit();
        try {// FirebaseInit.initializeFirebase();
            TextArea textArea = new TextArea();
            System.out.println("hello");
            obj.fetchDocumentKeys(buy_rent, object, textArea);
            System.out.println(buy_rent + object);
            String text = textArea.getText();
            String[] line = text.split("\n");

            for (int i = 0; i < line.length; i++) {
                StringTokenizer info = new StringTokenizer(line[i], ",");
                HBox hBox = new HBox(50);
                hBox.setMinWidth(1200);
                String imagename = info.nextToken();
                // Example loop to add multiple entries
                hBox.setStyle("-fx-padding: 10; -fx-border-radius: 10; -fx-background-radius: 10;");

                if (i % 2 == 0) {
                    hBox.setStyle(
                            "-fx-background-color: #E0E0E0; -fx-padding: 10; -fx-border-radius: 10; -fx-background-radius: 10;");
                } else {
                    hBox.setStyle(
                            "-fx-background-color: #FFFFFF; -fx-padding: 10; -fx-border-radius: 10; -fx-background-radius: 10;");
                }
                // String filepath="rent/tractor/"+imagename+"renttractor";
                // Create an ImageView and add an image to it
                Image image = ImageUploader.fetchImageFromFirebase(
                        buy_rent + "/" + object + "/" + imagename.toLowerCase() + buy_rent + object);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(600);
                imageView.setFitHeight(300);
                imageView.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.8), 10, 0, 0, 0);");

                // Create an inner VBox
                VBox innerVBox = new VBox(10);
                innerVBox.setMinWidth(500);
                innerVBox.setMinHeight(350);// if(i%2==0){hBox.setStyle("-fx-background-color:LIGHTGREY");innerVBox.setStyle("-fx-background-color:LIGHTGREY");}
                Label name_label = new Label("Name of Dealer :-  ");
                Label name_value = new Label(imagename);
                name_label.setFont(new Font(20));
                name_value.setFont(new Font(20));
                HBox name = new HBox(name_label, name_value);
                name.setStyle("-fx-background-color:#D3D3D3; -fx-background-radius: 10;");
                name.setPadding(new Insets(5, 5, 5, 10));

                Label location_label = new Label("Location :-  ");
                Label location_value = new Label(info.nextToken());
                location_label.setFont(new Font(20));
                location_value.setFont(new Font(20));
                Label region_label = new Label("Region :-  ");
                Label region_value = new Label(info.nextToken());
                region_label.setFont(new Font(20));
                region_value.setFont(new Font(20));
                HBox location = new HBox(location_label, location_value);
                location.setStyle("-fx-background-color:silver; -fx-background-radius: 10;");
                location.setPadding(new Insets(5, 5, 5, 10));
                HBox region = new HBox(region_label, region_value);
                region.setStyle("-fx-background-color:silver; -fx-background-radius: 10;");
                region.setPadding(new Insets(5));

                if (buy_rent == "rent") {
                    Label model_label = new Label("Model :-  ");
                    Label model_value = new Label(info.nextToken());
                    model_label.setFont(new Font(20));
                    model_value.setFont(new Font(20));
                    HBox model = new HBox(model_label, model_value);
                    model.setStyle("-fx-background-color:silver; -fx-background-radius: 10;");
                    model.setPadding(new Insets(5, 5, 5, 10));

                    Label registration_label = new Label("Registration :-  ");
                    Label registration_value = new Label(info.nextToken());
                    registration_label.setFont(new Font(20));
                    registration_value.setFont(new Font(20));
                    HBox registration = new HBox(registration_label, registration_value);
                    registration.setStyle("-fx-background-color:silver;-fx-background-radius: 10;");
                    registration.setPadding(new Insets(5, 5, 5, 10));

                    Label rent_label = new Label("Rent Price :-  ");
                    Label rent_value = new Label(info.nextToken());
                    rent_label.setFont(new Font(20));
                    rent_value.setFont(new Font(20));
                    HBox rent = new HBox(rent_label, rent_value);
                    rent.setStyle("-fx-background-color:silver; -fx-background-radius: 10;");
                    rent.setPadding(new Insets(5, 5, 5, 10));
                    innerVBox.getChildren().addAll(name, location, region, model, registration, rent);
                } else if (object == "potassium") {
                    Label model_label = new Label("Company :-  ");
                    Label model_value = new Label(info.nextToken());
                    model_label.setFont(new Font(20));
                    model_value.setFont(new Font(20));
                    HBox model = new HBox(model_label, model_value);
                    model.setStyle("-fx-background-color:silver; -fx-background-radius: 10;");
                    model.setPadding(new Insets(5, 5, 5, 10));

                    Label registration_label = new Label("NetWeight :-  ");
                    Label registration_value = new Label(info.nextToken());
                    registration_label.setFont(new Font(20));
                    registration_value.setFont(new Font(20));
                    Label kg = new Label("Kg");
                    kg.setFont(new Font(20));
                    HBox registration = new HBox(registration_label, registration_value, kg);
                    registration.setStyle("-fx-background-color:silver;-fx-background-radius: 10;");
                    registration.setPadding(new Insets(5, 5, 5, 10));

                    Label rent_label = new Label("Sell Price :-  ");
                    Label rent_value = new Label(info.nextToken());
                    rent_label.setFont(new Font(20));
                    rent_value.setFont(new Font(20));
                    HBox rent = new HBox(rent_label, rent_value);
                    rent.setStyle("-fx-background-color:silver; -fx-background-radius: 10;");
                    rent.setPadding(new Insets(5, 5, 5, 10));
                    innerVBox.getChildren().addAll(name, location, region, model, registration, rent);
                } else {
                    Label model_label = new Label("Company :-  ");
                    Label model_value = new Label(info.nextToken());
                    model_label.setFont(new Font(20));
                    model_value.setFont(new Font(20));
                    HBox model = new HBox(model_label, model_value);
                    model.setStyle("-fx-background-color:silver; -fx-background-radius: 10;");
                    model.setPadding(new Insets(5, 5, 5, 10));

                    Label registration_label = new Label("Model :-  ");
                    Label registration_value = new Label(info.nextToken());
                    registration_label.setFont(new Font(20));
                    registration_value.setFont(new Font(20));
                    HBox registration = new HBox(registration_label, registration_value);
                    registration.setStyle("-fx-background-color:silver;-fx-background-radius: 10;");
                    registration.setPadding(new Insets(5, 5, 5, 10));

                    Label rent_label = new Label("Sell Price :-  ");
                    Label rent_value = new Label(info.nextToken());
                    rent_label.setFont(new Font(20));
                    rent_value.setFont(new Font(20));
                    HBox rent = new HBox(rent_label, rent_value);
                    rent.setStyle("-fx-background-color:silver; -fx-background-radius: 10;");
                    rent.setPadding(new Insets(5, 5, 5, 10));
                    innerVBox.getChildren().addAll(name, location, region, model, registration, rent);
                }
                // innerVBox.getChildren().addAll(name,location,region,model,registration,rent);
                Button place_order = new Button("Place Order");
                place_order.setMinSize(150, 50);
                place_order.setStyle("-fx-background-radius:10");
                HBox place_order_hbox = new HBox(place_order);
                place_order_hbox.setAlignment(Pos.CENTER_RIGHT);
                innerVBox.getChildren().add(place_order_hbox);
                innerVBox.setAlignment(Pos.TOP_RIGHT);

                hBox.getChildren().addAll(imageView, innerVBox);
                hBox.setMinWidth(800);
                hBox.setMaxHeight(500);
                hBox.setAlignment(Pos.TOP_CENTER);
                hBox.setPadding(new Insets(0, 500, 0, 500));

                // Add the HBox to the main VBox
                maincontent.getChildren().add(hBox);
                scroll.setContent(maincontent);
                place_order.setOnAction(event -> {
                    try {
                        String mobile = FirebaseInit.readRec("users", LoginController.mail, "phone");
                        String message = "Your placed item is been ordered by " +LoginController.username+",To contact person call on "+ mobile;
                        SmsSenderApp.sendSms(info.nextToken(), message);
                        HBox bh=new HBox(new Text("Order has been placed !!!"));
                        Scene sc=new Scene(bh);
                        Stage st=new Stage();st.setHeight(300);st.setWidth(400);
                        st.setScene(sc);
                        st.show();
                    } catch (InterruptedException | ExecutionException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //SmsSenderApp.sendSms("+918668864412", "hiiii");
                });

            }
            shoppingpage.getChildren().clear();
            shoppingpage.getChildren().addAll(back_to_shopping_page, scroll);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
        }
        back_to_shopping_page.setOnAction(event_1 -> {
            this.shopping.getChildren().clear();
            this.shopping.getChildren().add(shoppingpage());
        });

        // VBox root=new VBox(shoppingpage);
        // return root;
    }

    @Override
    public void start(Stage primaryStage) {

    }

}
/*
 * package com.c2w;
 * import java.io.File;
 * import java.io.IOException;
 * import java.util.StringTokenizer;
 * import java.util.concurrent.ExecutionException;
 * 
 * import com.c2w.TemplateUI;
 * import com.c2w.firebaseConfig.FirebaseInit;
 * import com.google.api.client.auth.oauth2.TokenRequest;
 * 
 * import javafx.application.Application;
 * import javafx.collections.FXCollections;
 * import javafx.collections.ObservableList;
 * import javafx.geometry.Insets;
 * import javafx.geometry.Pos;
 * import javafx.scene.Scene;
 * import javafx.scene.control.Button;
 * import javafx.scene.control.ChoiceBox;
 * import javafx.scene.control.ComboBox;
 * import javafx.scene.control.Label;
 * import javafx.scene.control.ScrollPane;
 * import javafx.scene.control.TextArea;
 * import javafx.scene.control.TextField;
 * import javafx.scene.effect.Effect;
 * import javafx.scene.image.Image;
 * import javafx.scene.image.ImageView;
 * import javafx.scene.layout.Border;
 * import javafx.scene.layout.BorderPane;
 * import javafx.scene.layout.HBox;
 * import javafx.scene.layout.VBox;
 * import javafx.scene.text.Font;
 * import javafx.scene.text.Text;
 * import javafx.scene.text.TextAlignment;
 * import javafx.stage.FileChooser;
 * import javafx.stage.Stage;
 * 
 * public class Shopping extends Application {
 * 
 * public VBox shopping=shoppingpage();
 * // public VBox add_page=add_page(st);
 * //public VBox shop_page=shop(shopping,"rent","tractor");
 * private static String image_path;
 * 
 * 
 * 
 * public VBox shoppingpage(){
 * 
 * //Rent
 * ImageView tractor=createImageView("/assets/images/tractor.jpg");ImageView
 * harvester=createImageView("/assets/images/harvester.jpg");ImageView
 * powertiller=createImageView("/assets/images/powertiller.jpg");ImageView
 * seeddriller=createImageView("/assets/images/seeddriller.jpg");
 * ImageView wheat=createImageView_1("/assets/images/wheat.jpg");ImageView
 * bajra=createImageView_1("/assets/images/bajra.jpg");ImageView
 * jowar=createImageView_1("/assets/images/jowar.jpg");ImageView
 * rice=createImageView_1("/assets/images/rice.jpg");
 * ImageView urea=createImageView_1("/assets/images/urea.jpg");ImageView
 * phosphate=createImageView_1("/assets/images/phosphate.jpg");ImageView
 * potassium=createImageView_1("/assets/images/potassium.jpg");ImageView
 * calcium=createImageView_1("/assets/images/calcium.jpg");
 * 
 * Label add=new Label("Add");Label my_list=new Label("My List");Label
 * my_orders=new Label("My Orders");
 * HBox top_1 =new HBox(25,add,my_list,my_orders);top_1.setStyle(
 * "-fx-background-color:LIGHTGREY;-fx-background-radius:10;");top_1.
 * setMinHeight(30);top_1.setMinWidth(400);top_1.setAlignment(Pos.CENTER_RIGHT);
 * top_1.setPadding(new Insets(0,0,0,0));//buttons.setStyle();
 * 
 * Label back=new Label("BACK");
 * HBox top_2 =new HBox(25,back);top_2.setStyle(
 * "-fx-background-color:LIGHTGREY;-fx-background-radius:10;");top_2.
 * setMinHeight(30);top_2.setMinWidth(300);top_2.setAlignment(Pos.CENTER_LEFT);
 * top_2.setPadding(new Insets(0,0,0,20));//buttons.setStyle();
 * HBox top=new HBox(500,top_2,top_1);top_1.setPadding(new
 * Insets(0,0,0,30));top.setStyle(
 * "-fx-background-color:LIGHTGREY;-fx-background-radius:10;");top.setMaxWidth(
 * 1100);
 * 
 * 
 * // RENT AND BUY HBOX
 * 
 * Label buy_seeds_label=new
 * Label("Purchase seeds with upto 30% off");buy_seeds_label.setFont(new
 * Font(30));
 * HBox buy_seeds_images_1=new HBox(100,wheat,bajra);
 * Label buy_seeds_label_1=new
 * Label("Wheat seeds                                Bajra seeds");
 * buy_seeds_label_1.setFont(new Font(20));
 * HBox buy_seeds_images_2=new HBox(100,jowar,rice);
 * Label buy_seeds_label_2=new
 * Label("Jowar seeds                                 Rice seeds");
 * buy_seeds_label_2.setFont(new Font(20));
 * VBox buy_seeds=new
 * VBox(5,buy_seeds_label,buy_seeds_images_1,buy_seeds_label_1,
 * buy_seeds_images_2,buy_seeds_label_2);buy_seeds.setStyle(
 * "-fx-background-color:WHITE");buy_seeds.setAlignment(Pos.TOP_LEFT);buy_seeds.
 * setMinWidth(585);buy_seeds.setPadding(new
 * Insets(5,50,5,50));//buy_seeds.setMaxHeight(200);
 * 
 * Label buy_fertilizers_label=new
 * Label("Purchase Fertilizers with upto 25% off");buy_fertilizers_label.setFont
 * (new Font(25));
 * HBox buy_fertilizers_images_1=new HBox(100,urea,phosphate);
 * Label buy_fertilizers_label_1=new
 * Label("Urea                                             Phosphate");
 * buy_fertilizers_label_1.setFont(new Font(20));
 * HBox buy_fertilizers_images_2=new HBox(100,potassium,calcium);
 * Label buy_fertilizers_label_2=new
 * Label("Potassium                                        Calcium");
 * buy_fertilizers_label_2.setFont(new Font(20));
 * VBox buy_fertilizers=new
 * VBox(5,buy_fertilizers_label,buy_fertilizers_images_1,buy_fertilizers_label_1
 * ,buy_fertilizers_images_2,buy_fertilizers_label_2);buy_fertilizers.setStyle(
 * "-fx-background-color:WHITE");buy_fertilizers.setAlignment(Pos.TOP_LEFT);
 * buy_fertilizers.setMinWidth(585);buy_fertilizers.setPadding(new
 * Insets(5,50,5,50));//buy_seeds.setMaxHeight(200);
 * HBox buy=new HBox(10,buy_seeds,buy_fertilizers);
 * buy.setStyle("-fx-background-color:LIGHTGREY;-fx-background-radius:10;");buy.
 * setMaxHeight(330);buy.setMaxWidth(1200);buy.setPadding(new Insets(5));
 * // Combining seeds & fertilizers
 * 
 * 
 * Label rent_name=new
 * Label("  Tractor                                      Harvester                                  Power Tiller                              Seed Driller   "
 * );rent_name.setFont(new Font(20));
 * Text rent_text=new
 * Text(" RENT on Machines at an affordable rate ");rent_text.setFont(new
 * Font(30));
 * HBox rent_images=new
 * HBox(20,tractor,harvester,powertiller,seeddriller);rent_images.setStyle(
 * "-fx-background-color:WHITE");rent_images.setAlignment(Pos.CENTER_LEFT);
 * rent_images.setMinHeight(150);rent_images.setPadding(new Insets(10));
 * VBox rent=new VBox(rent_text,rent_images,rent_name);rent.setStyle(
 * "-fx-background-color:LIGHTGREY;-fx-background-radius:10;");rent.setAlignment
 * (Pos.BOTTOM_LEFT);rent.setPadding(new
 * Insets(20));rent.setMaxHeight(275);rent.setMaxWidth(1200);
 * 
 * //
 * -----------------------------------------------------------------------------
 * -------------------------
 * ImageView img_1=createImageView_1("/assets/images/tractor.jpg");ImageView
 * img_2=createImageView_1("/assets/images/tractor.jpg");ImageView
 * img_3=createImageView_1("/assets/images/tractor.jpg");ImageView
 * img_4=createImageView_1("/assets/images/tractor.jpg");
 * Label buy_seeds_label_dup=new
 * Label("Purchase seeds with upto 30% off");buy_seeds_label_dup.setFont(new
 * Font(30));
 * HBox buy_seeds_images_1_dup=new HBox(100,img_1,img_2);
 * Label buy_seeds_label_1_dup=new
 * Label("Wheat seeds                                Bajra seeds");
 * buy_seeds_label_1_dup.setFont(new Font(20));
 * HBox buy_seeds_images_2_dup=new HBox(100,img_3,img_4);
 * Label buy_seeds_label_2_dup=new
 * Label("Jowar seeds                                 Rice seeds");
 * buy_seeds_label_2_dup.setFont(new Font(20));
 * VBox buy_seeds_dup=new
 * VBox(5,buy_seeds_label_dup,buy_seeds_images_1_dup,buy_seeds_label_1_dup,
 * buy_seeds_images_2_dup,buy_seeds_label_2_dup);buy_seeds_dup.setStyle(
 * "-fx-background-color:WHITE;-fx-background-radius:10;");buy_seeds_dup.
 * setAlignment(Pos.TOP_LEFT);buy_seeds_dup.setMaxHeight(380);buy_seeds_dup.
 * setPadding(new Insets(5,50,5,50));//buy_seeds.setMaxHeight(200);
 * 
 * ImageView imga_1=createImageView_1("/assets/images/harvester.jpg");ImageView
 * imga_2=createImageView_1("/assets/images/harvester.jpg");ImageView
 * imga_3=createImageView_1("/assets/images/harvester.jpg");ImageView
 * imga_4=createImageView_1("/assets/images/harvester.jpg");
 * Label buy_fertilizers_label_dup=new
 * Label("Purchase Fertilizers with upto 25% off");buy_fertilizers_label_dup.
 * setFont(new Font(25));
 * HBox buy_fertilizers_images_1_dup=new HBox(100,imga_1,imga_2);
 * Label buy_fertilizers_label_1_dup=new
 * Label("Urea                                             Phosphate");
 * buy_fertilizers_label_1_dup.setFont(new Font(20));
 * HBox buy_fertilizers_images_2_dup=new HBox(100,imga_3,imga_4);
 * Label buy_fertilizers_label_2_dup=new
 * Label("Potassium                                        Calcium");
 * buy_fertilizers_label_2_dup.setFont(new Font(20));
 * VBox buy_fertilizers_dup=new
 * VBox(5,buy_fertilizers_label_dup,buy_fertilizers_images_1_dup,
 * buy_fertilizers_label_1_dup,buy_fertilizers_images_2_dup,
 * buy_fertilizers_label_2_dup);buy_fertilizers_dup.setStyle(
 * "-fx-background-color:WHITE;-fx-background-radius:10;");buy_fertilizers_dup.
 * setAlignment(Pos.TOP_LEFT);buy_fertilizers_dup.setMinWidth(585);
 * buy_fertilizers_dup.setPadding(new
 * Insets(5,50,5,50));//buy_seeds.setMaxHeight(200);
 * HBox buy_dup=new HBox(10,buy_seeds_dup,buy_fertilizers_dup);
 * buy_dup.setStyle("-fx-background-color:LIGHTGREY;-fx-background-radius:10;");
 * buy_dup.setMaxHeight(380);buy_dup.setMaxWidth(1200);buy_dup.setPadding(new
 * Insets(5));
 * 
 * HBox hb2=new
 * HBox(10,buy_dup);hb2.setStyle("-fx-background-color:TEAL");hb2.setMinSize(
 * 1200,390);hb2.setPadding(new Insets(10));
 * //---------------------------------------------------------------------------
 * -------------
 * ImageView imgae_1=createImageView("/assets/images/powertiller.jpg");ImageView
 * imgae_2=createImageView("/assets/images/powertiller.jpg");ImageView
 * imgae_3=createImageView("/assets/images/powertiller.jpg");ImageView
 * imgae_4=createImageView("/assets/images/powertiller.jpg");
 * Label rent_name_dup=new
 * Label("  Tractor                                      Harvester                                  Power Tiller                              Seed Driller   "
 * );rent_name_dup.setFont(new Font(20));
 * Text rent_text_dup=new
 * Text(" RENT on Machines at an affordable rate ");rent_text_dup.setFont(new
 * Font(30));
 * HBox rent_images_dup=new
 * HBox(30,imgae_1,imgae_2,imgae_3,imgae_4);rent_images_dup.setStyle(
 * "-fx-background-color:WHITE");rent_images_dup.setAlignment(Pos.CENTER_LEFT);
 * rent_images_dup.setMinHeight(150);rent_images_dup.setPadding(new
 * Insets(10,30,10,30));
 * VBox rent_dup=new
 * VBox(rent_text_dup,rent_images_dup,rent_name_dup);rent_dup.setStyle(
 * "-fx-background-color:LIGHTGREY;-fx-background-radius:10;");rent_dup.
 * setAlignment(Pos.BOTTOM_LEFT);rent_dup.setPadding(new
 * Insets(20));rent_dup.setMaxHeight(275);rent_dup.setMaxWidth(1200);
 * 
 * //---------------------------------------------------------------------------
 * ------------------------------
 * VBox shoppingpage=new
 * VBox(15,top,buy,rent,hb2,rent_dup);shoppingpage.setStyle(
 * "-fx-background-color:WHITE");shoppingpage.setPadding(new Insets(5));
 * //shoppingpage.setMaxHeight(700);
 * 
 * shoppingpage.setMinWidth(1500);
 * shoppingpage.setMinHeight(3700);
 * //shoppingpage.setMinWidth(1299);
 * ScrollPane shoppingPane=new
 * ScrollPane();shoppingPane.setContent(shoppingpage);
 * //shoppingPane.setPadding(new Insets(0, 20, 0, 130));
 * 
 * add.setOnMouseClicked(event->{
 * 
 * Stage st=new Stage();
 * st.setMinWidth(1000);st.setMinHeight(500);
 * Scene sc=new Scene(add_page(st));
 * st.setScene(sc);
 * //TemplateUI.shopping=add_page(st);
 * st.show();
 * 
 * 
 * });
 * tractor.setOnMouseClicked(event->{
 * //this.shopping.getChildren().clear();
 * shop(this.shopping,"rent","tractor");
 * // shoppingpage.getChildren().clear();
 * // shoppingpage.getChildren().add(shop(shoppingpage,"rent","tractor"));
 * System.out.println("tractor executed");
 * });
 * urea.setOnMouseClicked(event->{
 * shop(this.shopping,"sell","urea");
 * // shoppingpage.getChildren().add(shop(shoppingpage,"sell","urea"));
 * });
 * 
 * 
 * // ScrollPane shoppingPane=new
 * ScrollPane();shoppingPane.setContent(shoppingpage);
 * shoppingPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
 * shoppingPane.setMinWidth(1200);
 * shoppingPane.setMaxHeight(2500);
 * VBox root=new VBox(shoppingPane);
 * 
 * tractor.setOnMouseClicked(event->{
 * this.shopping.getChildren().clear();
 * shop(this.shopping,"rent","tractor");
 * // shoppingpage.getChildren().clear();
 * // shoppingpage.getChildren().add(shop(shoppingpage,"rent","tractor"));
 * });
 * return root;
 * 
 * }
 * private ImageView createImageView(String path) {
 * Image icon = new Image(path);
 * ImageView imageView = new ImageView(icon);
 * imageView.setFitHeight(200);imageView.setFitWidth(300);
 * return imageView;
 * }
 * private ImageView createImageView_1(String path) {
 * Image icon = new Image(path);
 * ImageView imageView = new ImageView(icon);
 * imageView.setFitHeight(150);imageView.setFitWidth(240);
 * return imageView;
 * }
 * public VBox add_page(Stage primaryStage){
 * 
 * VBox root_=new VBox(70);root_.setAlignment(Pos.CENTER);
 * HBox root__=new HBox(30);root_.setPadding(new Insets(20));
 * VBox root = new
 * VBox(10);root.setStyle("-fx-background-color:WHITE");root.setAlignment(Pos.
 * CENTER);root.setPadding(new Insets(20));
 * 
 * // Image view for displaying chosen image
 * 
 * ImageView imageView = new ImageView();
 * 
 * imageView.setFitWidth(300);
 * imageView.setFitHeight(300);
 * 
 * // Button to choose an image file
 * Button chooseImageButton = new Button("Choose Image");
 * 
 * // Text fields for name, region, rent price
 * TextField nameField = new TextField();nameField.setMinSize(500, 30);
 * nameField.setPromptText("Name");Label addLabel=new Label("Name:");
 * 
 * TextField regionField = new TextField();regionField.setMinSize(300, 30);
 * regionField.setPromptText("Region");Label regionLabel=new Label("Region:");
 * TextField locationField = new TextField();locationField.setMinSize(300, 30);
 * locationField.setPromptText("Region");Label locationLabel=new
 * Label("Location:");
 * HBox address_label=new HBox(350,locationLabel,regionLabel);
 * HBox address_box=new HBox(100,locationField,regionField);
 * VBox address=new VBox(address_label,address_box);
 * 
 * ChoiceBox<String> choicebox=new
 * ChoiceBox<String>();choicebox.setMinWidth(200);
 * choicebox.setItems(FXCollections.observableArrayList("Rent","Sell"));
 * choicebox.setValue("Rent");
 * 
 * ChoiceBox<String> choicebox_2=new
 * ChoiceBox<String>();choicebox_2.setMinWidth(200);
 * choicebox_2.setItems(FXCollections.observableArrayList("Tractor","Harvester",
 * "PowerTiller","SeedDriller"));
 * choicebox_2.setValue("Tractor");
 * HBox choiceboxes=new HBox(200,choicebox,choicebox_2);
 * 
 * 
 * TextField modelField = new TextField();modelField.setMinSize(300, 30);
 * modelField.setPromptText("Model");Label modelLabel=new Label("Model:");
 * TextField registrationField = new
 * TextField();registrationField.setMinSize(300, 30);
 * registrationField.setPromptText("Registration Year");Label
 * registrationLabel=new Label("Registration Year");
 * HBox type_label=new HBox(365,modelLabel,registrationLabel);
 * HBox type_box=new HBox(100,modelField,registrationField);
 * VBox type=new VBox(type_label,type_box);
 * 
 * TextField rentField = new TextField();rentField.setMaxSize(500, 50);
 * rentField.setPromptText("Price");Label rentLabel=new
 * Label("Rent Price per Day:");
 * 
 * // Buttons for add and remove
 * Button addButton = new Button("Add");
 * Button removeButton = new Button("Cancel");
 * HBox add_cancel=new
 * HBox(20,addButton,removeButton);add_cancel.setAlignment(Pos.CENTER);
 * 
 * chooseImageButton.setOnAction(e -> {
 * FileChooser fileChooser = new FileChooser();
 * fileChooser.setTitle("Choose Image File");
 * File file = fileChooser.showOpenDialog(primaryStage);
 * if (file != null) {
 * Image image = new Image(file.toURI().toString());
 * image_path=file.getPath();
 * 
 * imageView.setImage(image);
 * // ImageUploader.uploadImage(file.getPath(), nameField.getText(),);
 * 
 * 
 * }
 * });
 * 
 * // Add functionality to buttons (placeholder)
 * 
 * choicebox.getSelectionModel().selectedItemProperty().addListener((observable,
 * oldValue,newValue)->{
 * if(newValue=="Rent"){rentField.setPromptText("Rent Price per Day");rentLabel.
 * setText("Rent Price per Day");
 * choicebox_2.setItems(FXCollections.observableArrayList("Tractor","Harvester",
 * "PowerTiller","SeedDriller"));
 * choicebox_2.setValue("Tractor");
 * }
 * else {rentField.setPromptText("Sell Price per Kg");rentLabel.
 * setText("Sell Price per Kg");
 * choicebox_2.setItems(FXCollections.observableArrayList("Wheat","Jowar",
 * "Bajra","Rice","Urea","Phosphate","Potassium","Calcium"));choicebox_2.
 * setValue("Urea");
 * }
 * });
 * addButton.setOnAction(e -> {
 * // Add functionality here
 * try{
 * // FirebaseInit obj_1 = new FirebaseInit();
 * //FirebaseInit.initializeFirebase();
 * String
 * info=nameField.getText()+","+locationField.getText()+","+regionField.getText(
 * )+","+modelField.getText()+","+registrationField.getText()+","+rentField.
 * getText();
 * System.out.println(info);
 * ImageUploader.uploadImage(image_path,
 * (nameField.getText()+choicebox.getValue()+choicebox_2.getValue()).toLowerCase
 * (),choicebox.getValue().toLowerCase()+"/"+choicebox_2.getValue().toLowerCase(
 * ));
 * System.out.println(choicebox.getValue().toLowerCase()+"/"+choicebox_2.
 * getValue().toLowerCase()+"/");
 * System.out.println(choicebox.getValue().toLowerCase()+choicebox_2.getValue().
 * toLowerCase());
 * FirebaseInit.updateRec(choicebox.getValue().toLowerCase(),choicebox_2.
 * getValue().toLowerCase(),(nameField.getText()+choicebox.getValue()+
 * choicebox_2.getValue()).toLowerCase(),info);
 * System.out.println("doneeeee");
 * primaryStage.close();
 * }finally{}
 * 
 * 
 * });
 * 
 * 
 * removeButton.setOnAction(e -> {
 * primaryStage.close();
 * 
 * 
 * });
 * 
 * // Add components to layout
 * root.getChildren().addAll(
 * addLabel, nameField,
 * address,new Text("Rent/Sell"),
 * choiceboxes,type,rentLabel, rentField, add_cancel);
 * root_.getChildren().addAll(imageView, chooseImageButton);
 * root__.getChildren().addAll(root_,root);root__.setMinWidth(1000);
 * root__.setAlignment(Pos.CENTER);
 * VBox root_final=new VBox(root__);
 * return root_final;
 * }
 * 
 * private void shop(VBox shoppingpage,String buy_rent,String object){
 * 
 * // shoppingpage.getChildren().clear();
 * Button back_to_shopping_page=new Button("Back");
 * HBox back_to_shopping_page_HBox=new HBox(back_to_shopping_page);
 * VBox maincontent=new VBox(20);maincontent.setPadding(new Insets(20));
 * ScrollPane scroll=new
 * ScrollPane();scroll.setMinHeight(800);scroll.setMinWidth(1300);
 * FirebaseInit obj = new FirebaseInit();
 * try{//FirebaseInit.initializeFirebase();
 * TextArea textArea=new TextArea();
 * System.out.println("hello");
 * obj.fetchDocumentKeys(buy_rent,object,textArea);
 * System.out.println(buy_rent+object);
 * String text=textArea.getText();
 * String[]line=text.split("\n");
 * 
 * 
 * for(int i=0;i<line.length;i++){
 * StringTokenizer info=new StringTokenizer(line[i],",");
 * HBox hBox = new HBox(50);hBox.setMinWidth(1200);
 * String imagename=info.nextToken();
 * // Example loop to add multiple entries
 * hBox.
 * setStyle("-fx-padding: 10; -fx-border-radius: 10; -fx-background-radius: 10;"
 * );
 * 
 * if (i % 2 == 0) {
 * hBox.
 * setStyle("-fx-background-color: #E0E0E0; -fx-padding: 10; -fx-border-radius: 10; -fx-background-radius: 10;"
 * );
 * } else {
 * hBox.
 * setStyle("-fx-background-color: #FFFFFF; -fx-padding: 10; -fx-border-radius: 10; -fx-background-radius: 10;"
 * );
 * }
 * // String filepath="rent/tractor/"+imagename+"renttractor";
 * // Create an ImageView and add an image to it
 * Image image
 * =ImageUploader.fetchImageFromFirebase(buy_rent+"/"+object+"/"+imagename.
 * toLowerCase()+buy_rent+object);
 * ImageView imageView = new ImageView(image);
 * imageView.setFitWidth(600);
 * imageView.setFitHeight(300);
 * imageView.
 * setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.8), 10, 0, 0, 0);");
 * 
 * // Create an inner VBox
 * VBox innerVBox = new
 * VBox(10);innerVBox.setMinWidth(500);//if(i%2==0){hBox.setStyle(
 * "-fx-background-color:LIGHTGREY");innerVBox.setStyle(
 * "-fx-background-color:LIGHTGREY");}
 * Label name_label=new Label("Name of Dealer :-");Label name_value=new
 * Label(imagename);name_label.setFont(new Font(25));name_value.setFont(new
 * Font(25));
 * HBox name=new HBox(name_label,name_value);name.
 * setStyle("-fx-background-color:silver; -fx-background-radius: 10;");name.
 * setPadding(new Insets(5,5,5,10));
 * Label location_label=new Label("Location :-");Label location_value=new
 * Label(info.nextToken());location_label.setFont(new
 * Font(25));location_value.setFont(new Font(25));
 * Label region_label=new Label("Region :-");Label region_value=new
 * Label(info.nextToken());region_label.setFont(new
 * Font(25));region_value.setFont(new Font(25));
 * HBox location=new HBox(location_label,location_value);location.
 * setStyle("-fx-background-color:silver; -fx-background-radius: 10;");location.
 * setPadding(new Insets(5,5,5,10));
 * HBox region=new HBox(region_label,region_value);region.
 * setStyle("-fx-background-color:silver; -fx-background-radius: 10;");region.
 * setPadding(new Insets(5));
 * Label model_label=new Label("Model :-");Label model_value=new
 * Label(info.nextToken());model_label.setFont(new
 * Font(25));model_value.setFont(new Font(25));
 * HBox model=new HBox(model_label,model_value);model.
 * setStyle("-fx-background-color:silver; -fx-background-radius: 10;");model.
 * setPadding(new Insets(5,5,5,10));
 * Label registration_label=new Label("Registration :-");Label
 * registration_value=new Label(info.nextToken());registration_label.setFont(new
 * Font(25));registration_value.setFont(new Font(25));
 * HBox registration=new
 * HBox(registration_label,registration_value);registration.
 * setStyle("-fx-background-color:silver;-fx-background-radius: 10;");
 * registration.setPadding(new Insets(5,5,5,10));
 * 
 * Label rent_label=new Label("Rent Price :-");Label rent_value=new
 * Label(info.nextToken());rent_label.setFont(new
 * Font(25));rent_value.setFont(new Font(25));
 * HBox rent=new HBox(rent_label,rent_value);rent.
 * setStyle("-fx-background-color:silver; -fx-background-radius: 10;");rent.
 * setPadding(new Insets(5,5,5,10));
 * innerVBox.getChildren().addAll(name,location,region,model,registration,rent);
 * 
 * 
 * hBox.getChildren().addAll(imageView, innerVBox);
 * hBox.setMinWidth(800);hBox.setMaxHeight(500);
 * 
 * // Add the HBox to the main VBox
 * maincontent.getChildren().add(hBox);
 * scroll.setContent(maincontent);
 * }
 * shoppingpage.getChildren().clear();
 * shoppingpage.getChildren().addAll(back_to_shopping_page,scroll);}
 * catch (IOException e) {
 * // TODO Auto-generated catch block
 * e.printStackTrace();
 * } catch (ExecutionException e) {
 * // TODO Auto-generated catch block
 * e.printStackTrace();
 * } catch (InterruptedException e) {
 * // TODO Auto-generated catch block
 * e.printStackTrace();
 * }finally{}
 * back_to_shopping_page.setOnAction(event_1->{
 * this.shopping.getChildren().clear();
 * this.shopping.getChildren().add(shoppingpage());
 * });
 * // VBox root=new VBox(shoppingpage);
 * // return root;
 * }
 * 
 * @Override
 * public void start(Stage primaryStage) {
 * 
 * }
 * 
 * }
 * 
 */