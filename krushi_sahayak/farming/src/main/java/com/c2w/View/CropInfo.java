package com.c2w.View;

import java.util.concurrent.ExecutionException;

import com.c2w.firebaseConfig.FirebaseInit;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class CropInfo{
    
   public static FirebaseInit obj=new FirebaseInit();
   
    private static VBox mainContainer;
    private static VBox subcategoriesBox;
    private static GridPane categoriesGrid;
    private static VBox subcategoryInfoBox;
    private static String lastCategoryName;
    private static String lastCategoryImagePath;
    private static VBox cropVBox;


    public static VBox cropinformation(){

        // Main content area
        mainContainer = new VBox(0);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setStyle("-fx-background-color: #A7EEA6;");
        mainContainer.setPrefHeight(2900);
        mainContainer.setPadding(new Insets(30, 40, 30, 50));

        categoriesGrid = new GridPane();
        categoriesGrid.setAlignment(Pos.CENTER_LEFT);
        categoriesGrid.setPadding(new Insets(20));
        categoriesGrid.setPrefHeight(2900);
        categoriesGrid.setHgap(20);
        categoriesGrid.setVgap(20);

        // Label profileLabel = new Label("Krushi Sahayak");
        // Font fontProfileLabel = Font.font("Roboto", FontWeight.BOLD, 36);
        // profileLabel.setFont(fontProfileLabel);
        // profileLabel.setAlignment(Pos.TOP_CENTER);
        // categoriesGrid.add(profileLabel, 0, 0);

        String[] categoryNames = {"Cereal Crops\nअन्नधान्य", "Pulse Crops\nकडधान्य", "Root and Tuber Crops\nकंद आणि मूळ पिके", "Fruits\nफळे", "Vegetables\nभाज्या", "Oilseed Crops\nतेलबिया", "Fiber Crops\nतंतू पिके", "Sugar Crops\nसाखर पिके", "Spices and Herbs\nमसाले आणि औषधी वनस्पती", "Forage Crops\nचारा पिके", "Beverage Crops\nपेय पिके", "Medicinal and Aromatic Plants\nऔषधी आणि सुगंधी वनस्पती"};
        String[] categoryImagePaths = {"/assets/images/cereal_grains.jpeg", "/assets/images/pulse_crops.jpeg", "/assets/images/root_tuberCrops.jpeg", "/assets/images/fruits.jpeg", "/assets/images/vegetables.jpeg", "/assets/images/oilseeds.jpeg", "/assets/images/fiber_crops.jpeg", "/assets/images/sugarcrops.jpeg", "/assets/images/spices.jpeg", "/assets/images/forage_crops.jpeg", "/assets/images/beverage_crops.jpeg", "/assets/images/medicine.jpeg"};

        // Label categoryName = new Label("Crop Categories");
        // categoryName.setAlignment(Pos.CENTER);
        // categoriesGrid.getChildren().add(categoryName);

        for (int i = 0; i < categoryNames.length; i++) {
            VBox categoryBox = createCategoryBox(categoryNames[i], categoryImagePaths[i]);
            int finalI = i;
            categoryBox.setOnMouseClicked(event -> {
                lastCategoryName = categoryNames[finalI];
                lastCategoryImagePath = categoryImagePaths[finalI];
                showSubcategories(lastCategoryName, lastCategoryImagePath);
            });
            categoriesGrid.add(categoryBox, i % 4, i / 4);
        }

        mainContainer.getChildren().add(categoriesGrid);

        BorderPane root = new BorderPane();
        root.setCenter(mainContainer);
        return mainContainer;
    }

    private static VBox createCategoryBox(String categoryName, String imagePath) {
        ImageView imageView = new ImageView(new Image(CropInfo.class.getResourceAsStream(imagePath)));
        imageView.setFitWidth(310);
        imageView.setFitHeight(150);
        imageView.setEffect(new DropShadow(10, Color.GRAY));

        // Create a rounded rectangle clip
        Rectangle clip = new Rectangle(310, 150);
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        imageView.setClip(clip);

        Label label = new Label(categoryName);
        label.setFont(Font.font("Roboto", FontWeight.BOLD, 14));
        label.setAlignment(Pos.CENTER);
        label.setPadding(new Insets(10, 0, 0, 0));

        VBox categoryBox = new VBox(10, imageView, label);
        categoryBox.setAlignment(Pos.CENTER);
        categoryBox.setPadding(new Insets(10));
        categoryBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #d3d3d3; -fx-border-width: 2; -fx-background-radius: 10; -fx-border-radius: 10;");

        return categoryBox;
    }

    private static void showSubcategories(String categoryName, String categoryImagePath) {
        mainContainer.getChildren().remove(categoriesGrid);

        subcategoriesBox = new VBox();
        subcategoriesBox.setAlignment(Pos.CENTER_LEFT);
        subcategoriesBox.setStyle("-fx-background-color: #A7EEA6;");
        //subcategoriesBox.setStyle("-fx-background-color: #009900;");
        subcategoriesBox.setPrefWidth(1800);
        subcategoriesBox.setPrefHeight(2900);
        subcategoriesBox.setPadding(new Insets(5, 0, 5, 5));

        String[] subcategoryNames;
        String[] subcategoryImagePaths;

        switch (categoryName.split("\n")[0]) {
            case "Cereal Crops":
                subcategoryNames = new String[]{"Rice(तांदूळ)", "Wheat(गहू)", "Maize/Corn(मका)", "Barley(ज्वारी)", "Oats(जोंधळा)", "Rye(राई)"};
                subcategoryImagePaths = new String[]{"/assets/images/ricee.jpeg", "/assets/images/wheat.jpeg", "/assets/images/maize.jpeg", "/assets/images/barley.jpeg", "/assets/images/oats.jpeg", "/assets/images/rye.jpeg"};
                break;
            case "Pulse Crops":
                subcategoryNames = new String[]{"Chickpeas(हरभरा)", "Lentils(मसूर)", "Beans(मटकी)", "Peas(वाटाणा)", "Lupins(गावरान घेवडा)","Faba Beans(पावटा)"};
                subcategoryImagePaths = new String[]{"/assets/images/chickpeas.jpeg", "/assets/images/lentils.jpeg", "/assets/images/beans.jpeg", "/assets/images/peas.jpeg", "/assets/images/lupins.jpeg", "/assets/images/fababeans.jpeg"};
                break;
            case "Root and Tuber Crops":
                subcategoryNames = new String[]{"Potatoes(बटाटे)", "Sweet Potatoes(रतालू)", "Yams(सुरण)", "Cassava(शेवगा)", "Taro(अळू)", "Carrots(गाजर)"};
                subcategoryImagePaths = new String[]{"/assets/images/potatoes.jpeg", "/assets/images/sweetpotatoes.jpeg", "/assets/images/yam.jpeg", "/assets/images/cassava.jpeg", "/assets/images/taro.jpeg", "/assets/images/carrots.jpeg"};
                break;
            case "Fruits":
                subcategoryNames = new String[]{"Apple(सफरचंद)", "Orange(संत्रे)", "Banana(केळे)", "Grapes(द्राक्षे)", "Mango(आंबा)", "Pomegranate(डाळिंब)"};
                subcategoryImagePaths = new String[]{"/assets/images/apple.jpeg", "/assets/images/orange.jpeg", "/assets/images/banana.jpeg", "/assets/images/grapes.jpeg", "/assets/images/mango.jpeg", "/assets/images/pomegranate.jpeg"};
                break;
            case "Vegetables":
                subcategoryNames = new String[]{"Tomato(टमाटर)", "Spinach(पालक)", "Broccoli(ब्रोकोली)", "Onion(कांदा)", "Cabbage(कोबी)", "Bell Peppers(शिमला मिर्ची)"};
                subcategoryImagePaths = new String[]{"/assets/images/tomato.jpeg", "/assets/images/spinach.jpeg", "/assets/images/broccoli.jpeg", "/assets/images/onion.jpeg", "/assets/images/cabbage.jpeg", "/assets/images/bellpeppers.jpeg"};
                break;
            case "Oilseed Crops":
                subcategoryNames = new String[]{"Soybean(सोयाबीन)", "Sunflower(सूर्यफूल)", "Canola(कॅनोला)", "Peanut(शेंगदाणा)", "Sesame(तीळ)", "Palm Kernels(पाम कर्नल्स)"};
                subcategoryImagePaths = new String[]{"/assets/images/soybean.jpeg", "/assets/images/sunflower.jpeg", "/assets/images/canola.jpeg", "/assets/images/peanut.jpeg", "/assets/images/sesame.jpeg", "/assets/images/palmkernels.jpeg"};
                break;
            case "Fiber Crops":
                subcategoryNames = new String[]{"Cotton(कापूस)", "Flax(अळशी)", "Jute(ज्यूट)", "Hemp(भांग)", "Ramie(रामी)", "Kenaf(केनाफ)"};
                subcategoryImagePaths = new String[]{"/assets/images/cotton.jpeg", "/assets/images/flax.jpeg", "/assets/images/jute.jpeg", "/assets/images/hemp.jpeg", "/assets/images/ramie.jpeg", "/assets/images/kenaf.jpeg"};
                break;
            case "Sugar Crops":
                subcategoryNames = new String[]{"Sugarcane(ऊस)", "Sugar Beet(साखर बीट)", "Sweet Sorghum(गोड ज्वारी)"};
                subcategoryImagePaths = new String[]{"/assets/images/sugarcane.jpeg", "/assets/images/sugarbeet.jpeg", "/assets/images/sweetsorghum.jpeg"};
                break;
            case "Spices and Herbs":
                subcategoryNames = new String[]{"Turmeric(हळद)", "Ginger(आले)", "Garlic(लसूण)", "Basil(तुळस)", "Cilantro/Coriander(कोथिंबीर)", "Anise/Star Anise(चक्रफूल)"};
                subcategoryImagePaths = new String[]{"/assets/images/turmeric.jpeg", "/assets/images/ginger.jpeg", "/assets/images/garlic.jpeg", "/assets/images/basil.jpg", "/assets/images/cilantro.jpeg", "/assets/images/anise.jpeg"};
                break;
            case "Forage Crops":
                subcategoryNames = new String[]{"Alfalfa(अल्फाल्फा)", "Clover(तृण)", "Ryegrass(राईगवत)", "Sorghum-Sudan Grass(सोरघम-सुदान गवत)", "Fescue(राई घास)","Timothy Grass(टिमोथी गवत)"};
                subcategoryImagePaths = new String[]{"/assets/images/alfalfa.jpeg", "/assets/images/clover.jpeg", "/assets/images/ryegrass.jpeg", "/assets/images/sorghumsudangrass.jpeg", "/assets/images/fescue.jpeg", "/assets/images/timothygrass.jpeg"};
                break;
            case "Beverage Crops":
                subcategoryNames = new String[]{"Coffee(कॉफी)", "Tea(चहा)", "Cocoa(कोको)", "Kola Nut(कोला नट)", "Yerba Mate(यर्बा मेट)", "Hops(हॉप्स)"};
                subcategoryImagePaths = new String[]{"/assets/images/coffee.jpeg", "/assets/images/tea.jpeg", "/assets/images/cocoa.jpeg", "/assets/images/kolanut.jpeg", "/assets/images/yerbamate.jpeg", "/assets/images/hops.jpeg"};
                break;
            case "Medicinal and Aromatic Plants":
                subcategoryNames = new String[]{"Mint(पुदीना)", "Lavender(लॅव्हेंडर)", "Aloe Vera(कोरफड)", "Eucalyptus(निलगिरी)", "Rosemary(रोझमेरी)", "Neem(निंब)"};
                subcategoryImagePaths = new String[]{"/assets/images/mint.jpg", "/assets/images/lavender.jpg", "/assets/images/aloevera.jpeg", "/assets/images/eucalyptus.jpeg", "/assets/images/rosemary.jpeg", "/assets/images/neem.jpeg"};
                break;
            default:
                subcategoryNames = new String[]{};
                subcategoryImagePaths = new String[]{};
                break;
        }

        GridPane subcategoriesGrid = new GridPane();
        subcategoriesGrid.setAlignment(Pos.CENTER_LEFT);
        subcategoriesGrid.setPadding(new Insets(20));
        subcategoriesGrid.setHgap(70);
        subcategoriesGrid.setVgap(50);

        for (int i = 0; i < subcategoryNames.length; i++) {
            VBox subcategoryBox = createSubcategoryBox(subcategoryNames[i], subcategoryImagePaths[i]);
            int finalI = i;
            subcategoryBox.setOnMouseClicked(event -> {
                try {
                    showSubcategoryInfo(subcategoryNames[finalI], subcategoryImagePaths[finalI]);
                } catch (InterruptedException | ExecutionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
            subcategoriesGrid.add(subcategoryBox, i % 3, i / 3);
        }

        Button backButton = new Button("Back to Category");
        backButton.setAlignment(Pos.BOTTOM_CENTER);
        backButton.setOnAction(event -> mainContainer.getChildren().setAll(categoriesGrid));

        VBox subcategoriesContainer = new VBox(10);
        subcategoriesContainer.setAlignment(Pos.CENTER);
        subcategoriesContainer.getChildren().addAll(subcategoriesGrid, backButton);

        subcategoriesBox.getChildren().add(subcategoriesContainer);
        mainContainer.getChildren().add(subcategoriesBox);
    }

    private static VBox createSubcategoryBox(String subcategoryName, String imagePath) {
        ImageView imageView = new ImageView(new Image(CropInfo.class.getResourceAsStream(imagePath)));
        imageView.setFitWidth(400);
        imageView.setFitHeight(250);
        imageView.setEffect(new DropShadow(10, Color.GRAY));

        // Create a rounded rectangle clip
        Rectangle clip = new Rectangle(400, 250);
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        imageView.setClip(clip);

        Label label = new Label(subcategoryName);
        label.setFont(Font.font("Roboto", FontWeight.BOLD, 14));
        label.setAlignment(Pos.CENTER);
        label.setPadding(new Insets(10, 0, 0, 0));

        VBox subcategoryBox = new VBox(10, imageView, label);
        subcategoryBox.setAlignment(Pos.CENTER);
        subcategoryBox.setPadding(new Insets(10, 15, 20, 15));
        subcategoryBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #d3d3d3; -fx-border-width: 2; -fx-background-radius: 10; -fx-border-radius: 10;");

        return subcategoryBox;
    }


    private static void showSubcategoryInfo(String subcategoryName, String subcategoryImagePath) throws InterruptedException, ExecutionException {
        mainContainer.getChildren().remove(subcategoriesBox);

        subcategoryInfoBox = new VBox(10);
        subcategoryInfoBox.setAlignment(Pos.CENTER);
        // subcategoryInfoBox.setPadding(new Insets(20));
        subcategoryInfoBox.setStyle("-fx-background-color: #A7EEA6;");
        //subcategoriesBox.setStyle("-fx-background-color: #009900;");
        subcategoryInfoBox.setPrefWidth(1800);
        subcategoryInfoBox.setPrefHeight(2900);
        subcategoryInfoBox.setPadding(new Insets(5, 0, 5, 5));
        
        // Define sub-subcategories for each subcategory
        String[] subSubcategoryImagePaths;
    
        switch (subcategoryName) {
            case "Rice(तांदूळ)":
                subSubcategoryImagePaths = new String[]{"/assets/images/rice1.jpeg", "/assets/images/rice2.jpeg"};
                TextArea textArea = new TextArea(FirebaseInit.readRec("cropInfo","cereal crops","rice"));
                textArea.setPromptText("Search...");
                textArea.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea.setPrefWidth(1000);
                textArea.setPrefHeight(300);
                textArea.setMinHeight(300);
                textArea.setMaxHeight(300);
                textArea.setPrefRowCount(10); 
                textArea.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea.setWrapText(true);
                
                cropVBox = new VBox(textArea);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Wheat(गहू)":
                subSubcategoryImagePaths = new String[]{"/assets/images/wheat1.jpeg", "/assets/images/wheat2.jpeg"};
                TextArea textArea1 = new TextArea(FirebaseInit.readRec("cropInfo","cereal crops","wheat"));
                textArea1.setPromptText("Search...");
                textArea1.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea1.setPrefWidth(1000);
                textArea1.setPrefHeight(300);
                textArea1.setMinHeight(300);
                textArea1.setMaxHeight(300);
                textArea1.setPrefRowCount(10); 
                textArea1.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea1.setWrapText(true);
                
                cropVBox = new VBox(textArea1);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Maize/Corn(मका)":
                subSubcategoryImagePaths = new String[]{"/assets/images/maize1.jpeg", "/assets/images/maize2.jpeg"};
                TextArea textArea2 = new TextArea(FirebaseInit.readRec("cropInfo","cereal crops","maize"));
                textArea2.setPromptText("Search...");
                textArea2.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea2.setPrefWidth(1000);
                textArea2.setPrefHeight(300);
                textArea2.setMinHeight(300);
                textArea2.setMaxHeight(300);
                textArea2.setPrefRowCount(10); 
                textArea2.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea2.setWrapText(true);
                
                cropVBox = new VBox(textArea2);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Barley(ज्वारी)":
                subSubcategoryImagePaths = new String[]{"/assets/images/barley1.jpeg", "/assets/images/barley2.jpeg"};
                TextArea textArea3 = new TextArea(FirebaseInit.readRec("cropInfo","cereal crops","barley"));
                textArea3.setPromptText("Search...");
                textArea3.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea3.setPrefWidth(1000);
                textArea3.setPrefHeight(300);
                textArea3.setMinHeight(300);
                textArea3.setMaxHeight(300);
                textArea3.setPrefRowCount(10); 
                textArea3.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea3.setWrapText(true);
                
                cropVBox = new VBox(textArea3);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Oats(जोंधळा)":
                subSubcategoryImagePaths = new String[]{"/assets/images/oats1.jpeg", "/assets/images/oats2.jpeg"};
                TextArea textArea4 = new TextArea(FirebaseInit.readRec("cropInfo","cereal crops","oats"));
                textArea4.setPromptText("Search...");
                textArea4.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea4.setPrefWidth(1000);
                textArea4.setPrefHeight(300);
                textArea4.setMinHeight(300);
                textArea4.setMaxHeight(300);
                textArea4.setPrefRowCount(10); 
                textArea4.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea4.setWrapText(true);
                
                cropVBox = new VBox(textArea4);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Rye(राई)":
                subSubcategoryImagePaths = new String[]{"/assets/images/rye1.jpeg", "/assets/images/rye2.jpeg"};
                TextArea textArea5 = new TextArea(FirebaseInit.readRec("cropInfo","cereal crops","rye"));
                textArea5.setPromptText("Search...");
                textArea5.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea5.setPrefWidth(1000);
                textArea5.setPrefHeight(300);
                textArea5.setMinHeight(300);
                textArea5.setMaxHeight(300);
                textArea5.setPrefRowCount(10); 
                textArea5.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea5.setWrapText(true);
                
                cropVBox = new VBox(textArea5);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Chickpeas(हरभरा)":
                subSubcategoryImagePaths = new String[]{"/assets/images/chickpeas1.jpeg", "/assets/images/chickpeas2.jpeg"};
                TextArea textArea6 = new TextArea(FirebaseInit.readRec("cropInfo","pulse crops","chickpeas"));
                textArea6.setPromptText("Search...");
                textArea6.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea6.setPrefWidth(1000);
                textArea6.setPrefHeight(300);
                textArea6.setMinHeight(300);
                textArea6.setMaxHeight(300);
                textArea6.setPrefRowCount(10); 
                textArea6.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea6.setWrapText(true);
                
                cropVBox = new VBox(textArea6);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Lentils(मसूर)":
                subSubcategoryImagePaths = new String[]{"/assets/images/lentils1.jpeg", "/assets/images/lentils2.jpeg"};
                TextArea textArea7 = new TextArea(FirebaseInit.readRec("cropInfo","pulse crops","lentils"));
                textArea7.setPromptText("Search...");
                textArea7.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea7.setPrefWidth(1000);
                textArea7.setPrefHeight(300);
                textArea7.setMinHeight(300);
                textArea7.setMaxHeight(300);
                textArea7.setPrefRowCount(10); 
                textArea7.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea7.setWrapText(true);
                
                cropVBox = new VBox(textArea7);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Beans(मटकी)":
                subSubcategoryImagePaths = new String[]{"/assets/images/beans1.jpeg", "/assets/images/beans2.jpeg"};
                TextArea textArea9 = new TextArea(FirebaseInit.readRec("cropInfo","pulse crops","beans"));
                textArea9.setPromptText("Search...");
                textArea9.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea9.setPrefWidth(1000);
                textArea9.setPrefHeight(300);
                textArea9.setMinHeight(300);
                textArea9.setMaxHeight(300);
                textArea9.setPrefRowCount(10); 
                textArea9.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea9.setWrapText(true);
                
                cropVBox = new VBox(textArea9);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Peas(वाटाणा)":
                subSubcategoryImagePaths = new String[]{"/assets/images/peas1.jpeg", "/assets/images/peas2.jpeg"};
                TextArea textArea10 = new TextArea(FirebaseInit.readRec("cropInfo","pulse crops","peas"));
                textArea10.setPromptText("Search...");
                textArea10.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea10.setPrefWidth(1000);
                textArea10.setPrefHeight(300);
                textArea10.setMinHeight(300);
                textArea10.setMaxHeight(300);
                textArea10.setPrefRowCount(10); 
                textArea10.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea10.setWrapText(true);
                
                cropVBox = new VBox(textArea10);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Lupins(गावरान घेवडा)":
                subSubcategoryImagePaths = new String[]{"/assets/images/lupins1.jpeg", "/assets/images/lupins2.jpeg"};
                TextArea textArea11 = new TextArea(FirebaseInit.readRec("cropInfo","pulse crops","lupins"));
                textArea11.setPromptText("Search...");
                textArea11.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea11.setPrefWidth(1000);
                textArea11.setPrefHeight(300);
                textArea11.setMinHeight(300);
                textArea11.setMaxHeight(300);
                textArea11.setPrefRowCount(10); 
                textArea11.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea11.setWrapText(true);
                
                cropVBox = new VBox(textArea11);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Faba Beans(पावटा)":
                subSubcategoryImagePaths = new String[]{"/assets/images/fababeans1.jpeg", "/assets/images/fababeans2.jpeg"};
                TextArea textArea12 = new TextArea(FirebaseInit.readRec("cropInfo","pulse crops","fababeans"));
                textArea12.setPromptText("Search...");
                textArea12.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea12.setPrefWidth(1000);
                textArea12.setPrefHeight(300);
                textArea12.setMinHeight(300);
                textArea12.setMaxHeight(300);
                textArea12.setPrefRowCount(10); 
                textArea12.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea12.setWrapText(true);
                
                cropVBox = new VBox(textArea12);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Potatoes(बटाटे)":
                subSubcategoryImagePaths = new String[]{"/assets/images/potatoes1.jpeg", "/assets/images/potatoes2.jpeg"};
                TextArea textArea13 = new TextArea(FirebaseInit.readRec("cropInfo","root and tuber crops","potatoes"));
                textArea13.setPromptText("Search...");
                textArea13.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea13.setPrefWidth(1000);
                textArea13.setPrefHeight(300);
                textArea13.setMinHeight(300);
                textArea13.setMaxHeight(300);
                textArea13.setPrefRowCount(10); 
                textArea13.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea13.setWrapText(true);
                
                cropVBox = new VBox(textArea13);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Sweet Potatoes(रतालू)":
                subSubcategoryImagePaths = new String[]{"/assets/images/sweetpotatoes1.jpeg", "/assets/images/sweetpotatoes2.jpeg"};
                TextArea textArea14 = new TextArea(FirebaseInit.readRec("cropInfo","root and tuber crops","sweet potatoes"));
                textArea14.setPromptText("Search...");
                textArea14.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea14.setPrefWidth(1000);
                textArea14.setPrefHeight(300);
                textArea14.setMinHeight(300);
                textArea14.setMaxHeight(300);
                textArea14.setPrefRowCount(10); 
                textArea14.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea14.setWrapText(true);
                
                cropVBox = new VBox(textArea14);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5));
                break;
            case "Yams(सुरण)":
                subSubcategoryImagePaths = new String[]{"/assets/images/yams1.jpeg", "/assets/images/yams2.jpeg"};
                TextArea textArea15 = new TextArea(FirebaseInit.readRec("cropInfo","root and tuber crops","yams"));
                textArea15.setPromptText("Search...");
                textArea15.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea15.setPrefWidth(1000);
                textArea15.setPrefHeight(300);
                textArea15.setMinHeight(300);
                textArea15.setMaxHeight(300);
                textArea15.setPrefRowCount(10); 
                textArea15.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea15.setWrapText(true);
                
                cropVBox = new VBox(textArea15);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5));
                break;
            case "Cassava(शेवगा)":
                subSubcategoryImagePaths = new String[]{"/assets/images/cassava1.jpeg", "/assets/images/cassava2.jpeg"};
                TextArea textArea16 = new TextArea(FirebaseInit.readRec("cropInfo","root and tuber crops","cassava"));
                textArea16.setPromptText("Search...");
                textArea16.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea16.setPrefWidth(1000);
                textArea16.setPrefHeight(300);
                textArea16.setMinHeight(300);
                textArea16.setMaxHeight(300);
                textArea16.setPrefRowCount(10); 
                textArea16.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea16.setWrapText(true);
                
                cropVBox = new VBox(textArea16);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5));
                break;
            case "Taro(अळू)":
                subSubcategoryImagePaths = new String[]{"/assets/images/taro1.jpeg", "/assets/images/taro2.jpeg"};
                TextArea textArea17 = new TextArea(FirebaseInit.readRec("cropInfo","root and tuber crops","taro"));
                textArea17.setPromptText("Search...");
                textArea17.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea17.setPrefWidth(1000);
                textArea17.setPrefHeight(300);
                textArea17.setMinHeight(300);
                textArea17.setMaxHeight(300);
                textArea17.setPrefRowCount(10); 
                textArea17.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea17.setWrapText(true);
                
                cropVBox = new VBox(textArea17);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5));
                break;
            case "Carrots(गाजर)":
                subSubcategoryImagePaths = new String[]{"/assets/images/carrots1.jpeg", "/assets/images/carrots2.jpeg"};
                TextArea textArea18 = new TextArea(FirebaseInit.readRec("cropInfo","root and tuber crops","carrots"));
                textArea18.setPromptText("Search...");
                textArea18.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea18.setPrefWidth(1000);
                textArea18.setPrefHeight(300);
                textArea18.setMinHeight(300);
                textArea18.setMaxHeight(300);
                textArea18.setPrefRowCount(10); 
                textArea18.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea18.setWrapText(true);
                
                cropVBox = new VBox(textArea18);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5));
                break;
            case "Apple(सफरचंद)":
                subSubcategoryImagePaths = new String[]{"/assets/images/apple1.jpeg", "/assets/images/apple2.jpeg"};
                TextArea textArea19 = new TextArea(FirebaseInit.readRec("cropInfo","fruits","apple"));
                textArea19.setPromptText("Search...");
                textArea19.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea19.setPrefWidth(1000);
                textArea19.setPrefHeight(300);
                textArea19.setMinHeight(300);
                textArea19.setMaxHeight(300);
                textArea19.setPrefRowCount(10); 
                textArea19.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea19.setWrapText(true);
                
                cropVBox = new VBox(textArea19);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Orange(संत्रे)":
                subSubcategoryImagePaths = new String[]{"/assets/images/orange1.jpeg", "/assets/images/orange2.jpeg"};
                TextArea textArea20 = new TextArea(FirebaseInit.readRec("cropInfo","fruits","orange"));
                textArea20.setPromptText("Search...");
                textArea20.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea20.setPrefWidth(1000);
                textArea20.setPrefHeight(300);
                textArea20.setMinHeight(300);
                textArea20.setMaxHeight(300);
                textArea20.setPrefRowCount(10); 
                textArea20.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea20.setWrapText(true);
                
                cropVBox = new VBox(textArea20);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Banana(केळे)":
                subSubcategoryImagePaths = new String[]{"/assets/images/banana1.jpeg", "/assets/images/banana2.jpeg"};
                TextArea textArea21 = new TextArea(FirebaseInit.readRec("cropInfo","fruits","banana"));
                textArea21.setPromptText("Search...");
                textArea21.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea21.setPrefWidth(1000);
                textArea21.setPrefHeight(300);
                textArea21.setMinHeight(300);
                textArea21.setMaxHeight(300);
                textArea21.setPrefRowCount(10); 
                textArea21.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea21.setWrapText(true);
                
                cropVBox = new VBox(textArea21);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Grapes(द्राक्षे)":
                subSubcategoryImagePaths = new String[]{"/assets/images/grapes1.jpeg", "/assets/images/grapes2.jpeg"};
                TextArea textArea22 = new TextArea(FirebaseInit.readRec("cropInfo","fruits","grapes"));
                textArea22.setPromptText("Search...");
                textArea22.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea22.setPrefWidth(1000);
                textArea22.setPrefHeight(300);
                textArea22.setMinHeight(300);
                textArea22.setMaxHeight(300);
                textArea22.setPrefRowCount(10); 
                textArea22.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea22.setWrapText(true);
                
                cropVBox = new VBox(textArea22);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Mango(आंबा)":
                TextArea textArea23 = new TextArea(FirebaseInit.readRec("cropInfo","fruits","mango"));
                textArea23.setPromptText("Search...");
                textArea23.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea23.setPrefWidth(1000);
                textArea23.setPrefHeight(300);
                textArea23.setMinHeight(300);
                textArea23.setMaxHeight(300);
                textArea23.setPrefRowCount(10); 
                textArea23.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea23.setWrapText(true);
                
                cropVBox = new VBox(textArea23);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                subSubcategoryImagePaths = new String[]{"/assets/images/mango1.jpeg", "/assets/images/mango2.jpeg"};
                break;
            case "Pomegranate(डाळिंब)":
                subSubcategoryImagePaths = new String[]{"/assets/images/pomegranate1.jpeg", "/assets/images/pomegranate2.jpeg"};
                TextArea textArea24 = new TextArea(FirebaseInit.readRec("cropInfo","fruits","pomegranate"));
                textArea24.setPromptText("Search...");
                textArea24.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea24.setPrefWidth(1000);
                textArea24.setPrefHeight(300);
                textArea24.setMinHeight(300);
                textArea24.setMaxHeight(300);
                textArea24.setPrefRowCount(10); 
                textArea24.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea24.setWrapText(true);
                
                cropVBox = new VBox(textArea24);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Tomato(टमाटर)":
                subSubcategoryImagePaths = new String[]{"/assets/images/tomato1.jpeg", "/assets/images/tomato2.jpeg"};
                TextArea textArea25 = new TextArea(FirebaseInit.readRec("cropInfo","vegetables","tomato"));
                textArea25.setPromptText("Search...");
                textArea25.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea25.setPrefWidth(1000);
                textArea25.setPrefHeight(300);
                textArea25.setMinHeight(300);
                textArea25.setMaxHeight(300);
                textArea25.setPrefRowCount(10); 
                textArea25.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea25.setWrapText(true);
                
                cropVBox = new VBox(textArea25);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Spinach(पालक)":
                subSubcategoryImagePaths = new String[]{"/assets/images/spinach1.jpeg", "/assets/images/spinach2.jpeg"};
                TextArea textArea26 = new TextArea(FirebaseInit.readRec("cropInfo","vegetables","spinach"));
                textArea26.setPromptText("Search...");
                textArea26.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea26.setPrefWidth(1000);
                textArea26.setPrefHeight(300);
                textArea26.setMinHeight(300);
                textArea26.setMaxHeight(300);
                textArea26.setPrefRowCount(10); 
                textArea26.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea26.setWrapText(true);
                
                cropVBox = new VBox(textArea26);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Broccoli(ब्रोकोली)":
                subSubcategoryImagePaths = new String[]{"/assets/images/broccoli1.jpeg", "/assets/images/broccoli2.jpeg"};
                TextArea textArea27 = new TextArea(FirebaseInit.readRec("cropInfo","vegetables","broccoli"));
                textArea27.setPromptText("Search...");
                textArea27.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea27.setPrefWidth(1000);
                textArea27.setPrefHeight(300);
                textArea27.setMinHeight(300);
                textArea27.setMaxHeight(300);
                textArea27.setPrefRowCount(10); 
                textArea27.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea27.setWrapText(true);
                
                cropVBox = new VBox(textArea27);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Onion(कांदा)":
                subSubcategoryImagePaths = new String[]{"/assets/images/onion1.jpeg", "/assets/images/onion2.jpeg"};
                TextArea textArea28 = new TextArea(FirebaseInit.readRec("cropInfo","vegetables","onion"));
                textArea28.setPromptText("Search...");
                textArea28.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea28.setPrefWidth(1000);
                textArea28.setPrefHeight(300);
                textArea28.setMinHeight(300);
                textArea28.setMaxHeight(300);
                textArea28.setPrefRowCount(10); 
                textArea28.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea28.setWrapText(true);
                
                cropVBox = new VBox(textArea28);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Cabbage(कोबी)":
                subSubcategoryImagePaths = new String[]{"/assets/images/cabbage1.jpeg", "/assets/images/cabbage2.jpeg"};
                TextArea textArea29 = new TextArea(FirebaseInit.readRec("cropInfo","vegetables","cabbage"));
                textArea29.setPromptText("Search...");
                textArea29.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea29.setPrefWidth(1000);
                textArea29.setPrefHeight(300);
                textArea29.setMinHeight(300);
                textArea29.setMaxHeight(300);
                textArea29.setPrefRowCount(10); 
                textArea29.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea29.setWrapText(true);
                
                cropVBox = new VBox(textArea29);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Bell Peppers(शिमला मिर्ची)":
                subSubcategoryImagePaths = new String[]{"/assets/images/bellpeppers1.jpeg", "/assets/images/bellpeppers2.jpeg"};
                TextArea textArea30 = new TextArea(FirebaseInit.readRec("cropInfo","vegetables","bellpeppers"));
                textArea30.setPromptText("Search...");
                textArea30.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea30.setPrefWidth(1000);
                textArea30.setPrefHeight(300);
                textArea30.setMinHeight(300);
                textArea30.setMaxHeight(300);
                textArea30.setPrefRowCount(10); 
                textArea30.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea30.setWrapText(true);
                
                cropVBox = new VBox(textArea30);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Soybean(सोयाबीन)":
                subSubcategoryImagePaths = new String[]{"/assets/images/soybean1.jpeg", "/assets/images/soybean2.jpeg"};
                TextArea textArea31 = new TextArea(FirebaseInit.readRec("cropInfo","oilseedcrops","soybean"));
                textArea31.setPromptText("Search...");
                textArea31.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea31.setPrefWidth(1000);
                textArea31.setPrefHeight(300);
                textArea31.setMinHeight(300);
                textArea31.setMaxHeight(300);
                textArea31.setPrefRowCount(10); 
                textArea31.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea31.setWrapText(true);
                
                cropVBox = new VBox(textArea31);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Sunflower(सूर्यफूल)":
                subSubcategoryImagePaths = new String[]{"/assets/images/sunflower1.jpeg", "/assets/images/sunflower2.jpeg"};
                TextArea textArea32 = new TextArea(FirebaseInit.readRec("cropInfo","oilseedcrops","sunflower"));
                textArea32.setPromptText("Search...");
                textArea32.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea32.setPrefWidth(1000);
                textArea32.setPrefHeight(300);
                textArea32.setMinHeight(300);
                textArea32.setMaxHeight(300);
                textArea32.setPrefRowCount(10); 
                textArea32.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea32.setWrapText(true);
                
                cropVBox = new VBox(textArea32);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Canola(कॅनोला)":
                subSubcategoryImagePaths = new String[]{"/assets/images/canola1.jpeg", "/assets/images/canola2.jpeg"};
                TextArea textArea33 = new TextArea(FirebaseInit.readRec("cropInfo","oilseedcrops","canola"));
                textArea33.setPromptText("Search...");
                textArea33.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea33.setPrefWidth(1000);
                textArea33.setPrefHeight(300);
                textArea33.setMinHeight(300);
                textArea33.setMaxHeight(300);
                textArea33.setPrefRowCount(10); 
                textArea33.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea33.setWrapText(true);
                
                cropVBox = new VBox(textArea33);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Peanut(शेंगदाणा)":
                subSubcategoryImagePaths = new String[]{"/assets/images/peanut1.jpeg", "/assets/images/peanut2.jpeg"};
                TextArea textArea34 = new TextArea(FirebaseInit.readRec("cropInfo","oilseedcrops","peanut"));
                textArea34.setPromptText("Search...");
                textArea34.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea34.setPrefWidth(1000);
                textArea34.setPrefHeight(300);
                textArea34.setMinHeight(300);
                textArea34.setMaxHeight(300);
                textArea34.setPrefRowCount(10); 
                textArea34.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea34.setWrapText(true);
                
                cropVBox = new VBox(textArea34);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Sesame(तीळ)":
                subSubcategoryImagePaths = new String[]{"/assets/images/sesame1.jpeg", "/assets/images/sesame2.jpeg"};
                TextArea textArea35 = new TextArea(FirebaseInit.readRec("cropInfo","oilseedcrops","sesame"));
                textArea35.setPromptText("Search...");
                textArea35.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea35.setPrefWidth(1000);
                textArea35.setPrefHeight(300);
                textArea35.setMinHeight(300);
                textArea35.setMaxHeight(300);
                textArea35.setPrefRowCount(10); 
                textArea35.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea35.setWrapText(true);
                
                cropVBox = new VBox(textArea35);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Palm Kernels(पाम कर्नल्स)":
                subSubcategoryImagePaths = new String[]{"/assets/images/palmkernels1.jpeg", "/assets/images/palmkernels2.jpeg"};
                TextArea textArea36 = new TextArea(FirebaseInit.readRec("cropInfo","oilseedcrops","palmkernels"));
                textArea36.setPromptText("Search...");
                textArea36.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea36.setPrefWidth(1000);
                textArea36.setPrefHeight(300);
                textArea36.setMinHeight(300);
                textArea36.setMaxHeight(300);
                textArea36.setPrefRowCount(10); 
                textArea36.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea36.setWrapText(true);
                
                cropVBox = new VBox(textArea36);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Cotton(कापूस)":
                subSubcategoryImagePaths = new String[]{"/assets/images/cotton1.jpeg", "/assets/images/cotton2.jpeg"};
                TextArea textArea37 = new TextArea(FirebaseInit.readRec("cropInfo","fiber crops","cotton"));
                textArea37.setPromptText("Search...");
                textArea37.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea37.setPrefWidth(1000);
                textArea37.setPrefHeight(300);
                textArea37.setMinHeight(300);
                textArea37.setMaxHeight(300);
                textArea37.setPrefRowCount(10); 
                textArea37.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea37.setWrapText(true);
                
                cropVBox = new VBox(textArea37);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Flax(अळशी)":
                subSubcategoryImagePaths = new String[]{"/assets/images/flax1.jpeg", "/assets/images/flax2.jpeg"};
                TextArea textArea38 = new TextArea(FirebaseInit.readRec("cropInfo","fiber crops","flax"));
                textArea38.setPromptText("Search...");
                textArea38.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea38.setPrefWidth(1000);
                textArea38.setPrefHeight(300);
                textArea38.setMinHeight(300);
                textArea38.setMaxHeight(300);
                textArea38.setPrefRowCount(10); 
                textArea38.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea38.setWrapText(true);
                
                cropVBox = new VBox(textArea38);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Jute(ज्यूट)":
                subSubcategoryImagePaths = new String[]{"/assets/images/jute1.jpeg", "/assets/images/jute2.jpeg"};
                TextArea textArea39 = new TextArea(FirebaseInit.readRec("cropInfo","fiber crops","jute"));
                textArea39.setPromptText("Search...");
                textArea39.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea39.setPrefWidth(1000);
                textArea39.setPrefHeight(300);
                textArea39.setMinHeight(300);
                textArea39.setMaxHeight(300);
                textArea39.setPrefRowCount(10); 
                textArea39.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea39.setWrapText(true);
                
                cropVBox = new VBox(textArea39);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Hemp(भांग)":
                subSubcategoryImagePaths = new String[]{"/assets/images/hemp1.jpeg", "/assets/images/hemp2.jpeg"};
                TextArea textArea40 = new TextArea(FirebaseInit.readRec("cropInfo","fiber crops","hemp"));
                textArea40.setPromptText("Search...");
                textArea40.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea40.setPrefWidth(1000);
                textArea40.setPrefHeight(300);
                textArea40.setMinHeight(300);
                textArea40.setMaxHeight(300);
                textArea40.setPrefRowCount(10); 
                textArea40.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea40.setWrapText(true);
                
                cropVBox = new VBox(textArea40);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 

                break;
            case "Ramie(रामी)":
                subSubcategoryImagePaths = new String[]{"/assets/images/ramie1.jpeg", "/assets/images/ramie2.jpeg"};
                TextArea textArea41 = new TextArea(FirebaseInit.readRec("cropInfo","fiber crops","ramie"));
                textArea41.setPromptText("Search...");
                textArea41.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea41.setPrefWidth(1000);
                textArea41.setPrefHeight(300);
                textArea41.setMinHeight(300);
                textArea41.setMaxHeight(300);
                textArea41.setPrefRowCount(10); 
                textArea41.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea41.setWrapText(true);
                
                cropVBox = new VBox(textArea41);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Kenaf(केनाफ)":
                subSubcategoryImagePaths = new String[]{"/assets/images/kenaf1.jpeg", "/assets/images/kenaf2.jpeg"};
                TextArea textArea42 = new TextArea(FirebaseInit.readRec("cropInfo","fiber crops","kenaf"));
                textArea42.setPromptText("Search...");
                textArea42.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea42.setPrefWidth(1000);
                textArea42.setPrefHeight(300);
                textArea42.setMinHeight(300);
                textArea42.setMaxHeight(300);
                textArea42.setPrefRowCount(10); 
                textArea42.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea42.setWrapText(true);
                
                cropVBox = new VBox(textArea42);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Sugarcane(ऊस)":
                subSubcategoryImagePaths = new String[]{"/assets/images/sugarcane1.jpeg", "/assets/images/sugarcane2.jpeg"};
                TextArea textArea43 = new TextArea(FirebaseInit.readRec("cropInfo","sugar crops","sugarcane"));
                textArea43.setPromptText("Search...");
                textArea43.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea43.setPrefWidth(1000);
                textArea43.setPrefHeight(300);
                textArea43.setMinHeight(300);
                textArea43.setMaxHeight(300);
                textArea43.setPrefRowCount(10); 
                textArea43.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea43.setWrapText(true);
                
                cropVBox = new VBox(textArea43);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Sugar Beet(साखर बीट)":
                subSubcategoryImagePaths = new String[]{"/assets/images/sugarbeet1.jpeg", "/assets/images/sugarbeet2.jpeg"};
                TextArea textArea44 = new TextArea(FirebaseInit.readRec("cropInfo","sugar crops","sugarbeet"));
                textArea44.setPromptText("Search...");
                textArea44.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea44.setPrefWidth(1000);
                textArea44.setPrefHeight(300);
                textArea44.setMinHeight(300);
                textArea44.setMaxHeight(300);
                textArea44.setPrefRowCount(10); 
                textArea44.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea44.setWrapText(true);
                
                cropVBox = new VBox(textArea44);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Sweet Sorghum(गोड ज्वारी)":
                subSubcategoryImagePaths = new String[]{"/assets/images/sweetsorghum1.jpeg", "/assets/images/sweetsorghum2.jpeg"};
                TextArea textArea45 = new TextArea(FirebaseInit.readRec("cropInfo","sugar crops","sweetsorghum"));
                textArea45.setPromptText("Search...");
                textArea45.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea45.setPrefWidth(1000);
                textArea45.setPrefHeight(300);
                textArea45.setMinHeight(300);
                textArea45.setMaxHeight(300);
                textArea45.setPrefRowCount(10); 
                textArea45.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea45.setWrapText(true);
                
                cropVBox = new VBox(textArea45);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Turmeric(हळद)":
                subSubcategoryImagePaths = new String[]{"/assets/images/turmeric1.jpeg", "/assets/images/turmeric2.jpeg"};
                TextArea textArea46 = new TextArea(FirebaseInit.readRec("cropInfo","spices","turmeric"));
                textArea46.setPromptText("Search...");
                textArea46.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea46.setPrefWidth(1000);
                textArea46.setPrefHeight(300);
                textArea46.setMinHeight(300);
                textArea46.setMaxHeight(300);
                textArea46.setPrefRowCount(10); 
                textArea46.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea46.setWrapText(true);
                
                cropVBox = new VBox(textArea46);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Ginger(आले)":
                subSubcategoryImagePaths = new String[]{"/assets/images/ginger1.jpeg", "/assets/images/ginger2.jpeg"};
                TextArea textArea47 = new TextArea(FirebaseInit.readRec("cropInfo","spices","ginger"));
                textArea47.setPromptText("Search...");
                textArea47.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea47.setPrefWidth(1000);
                textArea47.setPrefHeight(300);
                textArea47.setMinHeight(300);
                textArea47.setMaxHeight(300);
                textArea47.setPrefRowCount(10); 
                textArea47.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea47.setWrapText(true);
                
                cropVBox = new VBox(textArea47);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Garlic(लसूण)":
                subSubcategoryImagePaths = new String[]{"/assets/images/garlic1.jpeg", "/assets/images/garlic2.jpeg"};
                TextArea textArea48 = new TextArea(FirebaseInit.readRec("cropInfo","spices","garlic"));
                textArea48.setPromptText("Search...");
                textArea48.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea48.setPrefWidth(1000);
                textArea48.setPrefHeight(300);
                textArea48.setMinHeight(300);
                textArea48.setMaxHeight(300);
                textArea48.setPrefRowCount(10); 
                textArea48.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea48.setWrapText(true);
                
                cropVBox = new VBox(textArea48);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Basil(तुळस)":
                subSubcategoryImagePaths = new String[]{"/assets/images/basil1.jpeg", "/assets/images/basil2.jpeg"};
                TextArea textArea49 = new TextArea(FirebaseInit.readRec("cropInfo","spices","basil"));
                textArea49.setPromptText("Search...");
                textArea49.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea49.setPrefWidth(1000);
                textArea49.setPrefHeight(300);
                textArea49.setMinHeight(300);
                textArea49.setMaxHeight(300);
                textArea49.setPrefRowCount(10); 
                textArea49.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea49.setWrapText(true);
                
                cropVBox = new VBox(textArea49);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Cilantro/Coriander(कोथिंबीर)":
                subSubcategoryImagePaths = new String[]{"/assets/images/cilantro1.jpeg", "/assets/images/cilantro2.jpeg"};
                TextArea textArea50 = new TextArea(FirebaseInit.readRec("cropInfo","spices","cilantro"));
                textArea50.setPromptText("Search...");
                textArea50.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea50.setPrefWidth(1000);
                textArea50.setPrefHeight(300);
                textArea50.setMinHeight(300);
                textArea50.setMaxHeight(300);
                textArea50.setPrefRowCount(10); 
                textArea50.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea50.setWrapText(true);
                
                cropVBox = new VBox(textArea50);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Anise/Star Anise(चक्रफूल)":
                subSubcategoryImagePaths = new String[]{"/assets/images/anise1.jpeg", "/assets/images/anise2.jpeg"};
                TextArea textArea51 = new TextArea(FirebaseInit.readRec("cropInfo","spices","anise"));
                textArea51.setPromptText("Search...");
                textArea51.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea51.setPrefWidth(1000);
                textArea51.setPrefHeight(300);
                textArea51.setMinHeight(300);
                textArea51.setMaxHeight(300);
                textArea51.setPrefRowCount(10); 
                textArea51.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea51.setWrapText(true);
                
                cropVBox = new VBox(textArea51);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Alfalfa(अल्फाल्फा)":
                subSubcategoryImagePaths = new String[]{"/assets/images/alfalfa1.jpeg", "/assets/images/alfalfa2.jpeg"};
                TextArea textArea52 = new TextArea(FirebaseInit.readRec("cropInfo","forage crops","alfalfa"));
                textArea52.setPromptText("Search...");
                textArea52.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea52.setPrefWidth(1000);
                textArea52.setPrefHeight(300);
                textArea52.setMinHeight(300);
                textArea52.setMaxHeight(300);
                textArea52.setPrefRowCount(10); 
                textArea52.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea52.setWrapText(true);
                
                cropVBox = new VBox(textArea52);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Clover(तृण)":
                subSubcategoryImagePaths = new String[]{"/assets/images/clover1.jpeg", "/assets/images/clover2.jpeg"};
                TextArea textArea53 = new TextArea(FirebaseInit.readRec("cropInfo","forage crops","clover"));
                textArea53.setPromptText("Search...");
                textArea53.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea53.setPrefWidth(1000);
                textArea53.setPrefHeight(300);
                textArea53.setMinHeight(300);
                textArea53.setMaxHeight(300);
                textArea53.setPrefRowCount(10); 
                textArea53.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea53.setWrapText(true);
                
                cropVBox = new VBox(textArea53);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Ryegrass(राईगवत)":
                subSubcategoryImagePaths = new String[]{"/assets/images/ryegrass1.jpeg", "/assets/images/ryegrass2.jpeg"};
                TextArea textArea54 = new TextArea(FirebaseInit.readRec("cropInfo","forage crops","ryegrass"));
                textArea54.setPromptText("Search...");
                textArea54.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea54.setPrefWidth(1000);
                textArea54.setPrefHeight(300);
                textArea54.setMinHeight(300);
                textArea54.setMaxHeight(300);
                textArea54.setPrefRowCount(10); 
                textArea54.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea54.setWrapText(true);
                
                cropVBox = new VBox(textArea54);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Sorghum-Sudan Grass(सोरघम-सुदान गवत)":
                subSubcategoryImagePaths = new String[]{"/assets/images/sorghumsudangrass1.jpeg", "/assets/images/sorghumsudangrass2.jpeg"};
                TextArea textArea55 = new TextArea(FirebaseInit.readRec("cropInfo","forage crops","sorghumsudangrass"));
                textArea55.setPromptText("Search...");
                textArea55.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea55.setPrefWidth(1000);
                textArea55.setPrefHeight(300);
                textArea55.setMinHeight(300);
                textArea55.setMaxHeight(300);
                textArea55.setPrefRowCount(10); 
                textArea55.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea55.setWrapText(true);
                
                cropVBox = new VBox(textArea55);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Fescue(राई घास)":
                subSubcategoryImagePaths = new String[]{"/assets/images/fescue1.jpeg", "/assets/images/fescue2.jpeg"};
                TextArea textArea56 = new TextArea(FirebaseInit.readRec("cropInfo","forage crops","fescue"));
                textArea56.setPromptText("Search...");
                textArea56.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea56.setPrefWidth(1000);
                textArea56.setPrefHeight(300);
                textArea56.setMinHeight(300);
                textArea56.setMaxHeight(300);
                textArea56.setPrefRowCount(10); 
                textArea56.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea56.setWrapText(true);
                
                cropVBox = new VBox(textArea56);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Timothy Grass(टिमोथी गवत)":
                subSubcategoryImagePaths = new String[]{"/assets/images/timothygrass1.jpeg", "/assets/images/timothygrass2.jpeg"};
                TextArea textArea57 = new TextArea(FirebaseInit.readRec("cropInfo","forage crops","timothygrass"));
                textArea57.setPromptText("Search...");
                textArea57.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea57.setPrefWidth(1000);
                textArea57.setPrefHeight(300);
                textArea57.setMinHeight(300);
                textArea57.setMaxHeight(300);
                textArea57.setPrefRowCount(10); 
                textArea57.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea57.setWrapText(true);
                
                cropVBox = new VBox(textArea57);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Coffee(कॉफी)":
                subSubcategoryImagePaths = new String[]{"/assets/images/coffee1.jpeg", "/assets/images/coffee2.jpeg"};
                TextArea textArea58 = new TextArea(FirebaseInit.readRec("cropInfo","beverage","coffee"));
                textArea58.setPromptText("Search...");
                textArea58.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea58.setPrefWidth(1000);
                textArea58.setPrefHeight(300);
                textArea58.setMinHeight(300);
                textArea58.setMaxHeight(300);
                textArea58.setPrefRowCount(10); 
                textArea58.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea58.setWrapText(true);
                
                cropVBox = new VBox(textArea58);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Tea(चहा)":
                subSubcategoryImagePaths = new String[]{"/assets/images/tea1.jpeg", "/assets/images/tea2.jpeg"};
                TextArea textArea59 = new TextArea(FirebaseInit.readRec("cropInfo","beverage","tea"));
                textArea59.setPromptText("Search...");
                textArea59.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea59.setPrefWidth(1000);
                textArea59.setPrefHeight(300);
                textArea59.setMinHeight(300);
                textArea59.setMaxHeight(300);
                textArea59.setPrefRowCount(10); 
                textArea59.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea59.setWrapText(true);
                
                cropVBox = new VBox(textArea59);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Cocoa(कोको)":
                subSubcategoryImagePaths = new String[]{"/assets/images/cocoa1.jpeg", "/assets/images/cocoa2.jpeg"};
                TextArea textArea60 = new TextArea(FirebaseInit.readRec("cropInfo","beverage","cocoa"));
                textArea60.setPromptText("Search...");
                textArea60.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea60.setPrefWidth(1000);
                textArea60.setPrefHeight(300);
                textArea60.setMinHeight(300);
                textArea60.setMaxHeight(300);
                textArea60.setPrefRowCount(10); 
                textArea60.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea60.setWrapText(true);
                
                cropVBox = new VBox(textArea60);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Kola Nut(कोला नट)":
                subSubcategoryImagePaths = new String[]{"/assets/images/kolanut1.jpeg", "/assets/images/kolanut2.jpeg"};
                TextArea textArea61 = new TextArea(FirebaseInit.readRec("cropInfo","beverage","kolanut"));
                textArea61.setPromptText("Search...");
                textArea61.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea61.setPrefWidth(1000);
                textArea61.setPrefHeight(300);
                textArea61.setMinHeight(300);
                textArea61.setMaxHeight(300);
                textArea61.setPrefRowCount(10); 
                textArea61.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea61.setWrapText(true);
                
                cropVBox = new VBox(textArea61);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Yerba Mate(यर्बा मेट)":
                subSubcategoryImagePaths = new String[]{"/assets/images/yerbamate1.jpeg", "/assets/images/yerbamate2.jpeg"};
                TextArea textArea62 = new TextArea(FirebaseInit.readRec("cropInfo","beverage","yebarmate"));
                textArea62.setPromptText("Search...");
                textArea62.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea62.setPrefWidth(1000);
                textArea62.setPrefHeight(300);
                textArea62.setMinHeight(300);
                textArea62.setMaxHeight(300);
                textArea62.setPrefRowCount(10); 
                textArea62.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea62.setWrapText(true);
                
                cropVBox = new VBox(textArea62);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Hops(हॉप्स)":
                subSubcategoryImagePaths = new String[]{"/assets/images/hops1.jpeg", "/assets/images/hops2.jpeg"};
                TextArea textArea63 = new TextArea(FirebaseInit.readRec("cropInfo","beverage","hops"));
                textArea63.setPromptText("Search...");
                textArea63.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea63.setPrefWidth(1000);
                textArea63.setPrefHeight(300);
                textArea63.setMinHeight(300);
                textArea63.setMaxHeight(300);
                textArea63.setPrefRowCount(10); 
                textArea63.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea63.setWrapText(true);
                
                cropVBox = new VBox(textArea63);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Mint(पुदीना)":
                subSubcategoryImagePaths = new String[]{"/assets/images/mint1.jpeg", "/assets/images/mint2.jpeg"};
                TextArea textArea64 = new TextArea(FirebaseInit.readRec("cropInfo","medicine","mint"));
                textArea64.setPromptText("Search...");
                textArea64.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea64.setPrefWidth(1000);
                textArea64.setPrefHeight(300);
                textArea64.setMinHeight(300);
                textArea64.setMaxHeight(300);
                textArea64.setPrefRowCount(10); 
                textArea64.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea64.setWrapText(true);
                
                cropVBox = new VBox(textArea64);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Lavender(लॅव्हेंडर)":
                subSubcategoryImagePaths = new String[]{"/assets/images/lavender1.jpeg", "/assets/images/lavender2.jpeg"};
                TextArea textArea65 = new TextArea(FirebaseInit.readRec("cropInfo","medicine","lavender"));
                textArea65.setPromptText("Search...");
                textArea65.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea65.setPrefWidth(1000);
                textArea65.setPrefHeight(300);
                textArea65.setMinHeight(300);
                textArea65.setMaxHeight(300);
                textArea65.setPrefRowCount(10); 
                textArea65.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea65.setWrapText(true);
                
                cropVBox = new VBox(textArea65);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Aloe Vera(कोरफड)":
                subSubcategoryImagePaths = new String[]{"/assets/images/aloevera1.jpeg", "/assets/images/aloevera2.jpeg"};
                TextArea textArea66 = new TextArea(FirebaseInit.readRec("cropInfo","medicine","aloevera"));
                textArea66.setPromptText("Search...");
                textArea66.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea66.setPrefWidth(1000);
                textArea66.setPrefHeight(300);
                textArea66.setMinHeight(300);
                textArea66.setMaxHeight(300);
                textArea66.setPrefRowCount(10); 
                textArea66.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea66.setWrapText(true);
                
                cropVBox = new VBox(textArea66);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Eucalyptus(निलगिरी)":
                subSubcategoryImagePaths = new String[]{"/assets/images/eucalyptus1.jpeg", "/assets/images/eucalyptus2.jpeg"};
                TextArea textArea67 = new TextArea(FirebaseInit.readRec("cropInfo","medicine","eucalyptus"));
                textArea67.setPromptText("Search...");
                textArea67.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea67.setPrefWidth(1000);
                textArea67.setPrefHeight(300);
                textArea67.setMinHeight(300);
                textArea67.setMaxHeight(300);
                textArea67.setPrefRowCount(10); 
                textArea67.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea67.setWrapText(true);
                
                cropVBox = new VBox(textArea67);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Rosemary(रोझमेरी)":
                subSubcategoryImagePaths = new String[]{"/assets/images/rosemary1.jpeg", "/assets/images/rosemary2.jpeg"};
                TextArea textArea68 = new TextArea(FirebaseInit.readRec("cropInfo","medicine","rosemary"));
                textArea68.setPromptText("Search...");
                textArea68.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea68.setPrefWidth(1000);
                textArea68.setPrefHeight(300);
                textArea68.setMinHeight(300);
                textArea68.setMaxHeight(300);
                textArea68.setPrefRowCount(10); 
                textArea68.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea68.setWrapText(true);
                
                cropVBox = new VBox(textArea68);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            case "Neem(निंब)":
                subSubcategoryImagePaths = new String[]{"/assets/images/neem1.jpeg", "/assets/images/neem2.jpeg"};
                TextArea textArea69 = new TextArea(FirebaseInit.readRec("cropInfo","medicine","neem"));
                textArea69.setPromptText("Search...");
                textArea69.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 16));
                textArea69.setPrefWidth(1000);
                textArea69.setPrefHeight(300);
                textArea69.setMinHeight(300);
                textArea69.setMaxHeight(300);
                textArea69.setPrefRowCount(10); 
                textArea69.setStyle("-fx-background-radius: 15; -fx-control-inner-background: #C0F3BF; -fx-background-color: #C0F3BF; -fx-background-radius: 20;");
                textArea69.setWrapText(true);
                
                cropVBox = new VBox(textArea69);
                cropVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                cropVBox.setMaxWidth(Region.USE_PREF_SIZE);
                cropVBox.setAlignment(Pos.CENTER);
                cropVBox.setStyle("-fx-background-color: #C0F3BF; -fx-border-radius: 20;");
                cropVBox.setPadding(new Insets(20, 5, 20, 5)); 
                break;
            
            default:
                subSubcategoryImagePaths = new String[]{};
        }
       

        // Clear previous subcategory info
        if (subcategoryInfoBox != null) {
            subcategoriesBox.getChildren().remove(subcategoryInfoBox);
       }

        ImageView imageView = new ImageView(new Image(CropInfo.class.getResourceAsStream(subcategoryImagePath)));
        imageView.setFitWidth(400);
        imageView.setFitHeight(400);
        imageView.setEffect(new DropShadow(20, Color.GRAY));

        VBox imageVBox = new VBox(imageView);
        imageVBox.setAlignment(Pos.CENTER);
        imageVBox.setPrefWidth(400);
        imageVBox.setPrefHeight(400);
        imageVBox.setStyle("-fx-background-radius: 20;");
        imageVBox.setPadding(new Insets(0, 20, 0, 20));

        Label label = new Label(subcategoryName);
        label.setFont(Font.font("Roboto", FontWeight.BOLD, 18));
        label.setAlignment(Pos.CENTER);
        label.setPadding(new Insets(10, 0, 0, 0));

        ImageView subSubcategoryImageView1 = new ImageView(new Image(CropInfo.class.getResourceAsStream(subSubcategoryImagePaths[0])));
        subSubcategoryImageView1.setFitWidth(300);
        subSubcategoryImageView1.setFitHeight(300);
        subSubcategoryImageView1.setEffect(new DropShadow(20, Color.GRAY));

        VBox imageVBox1 = new VBox(subSubcategoryImageView1);
        imageVBox1.setAlignment(Pos.TOP_LEFT);
        imageVBox1.setPrefWidth(300);
        imageVBox1.setPrefHeight(300);
        imageVBox1.setStyle("-fx-background-radius: 20;");
        imageVBox1.setPadding(new Insets(40, 10, 30, 10));
    
        ImageView subSubcategoryImageView2 = new ImageView(new Image(CropInfo.class.getResourceAsStream(subSubcategoryImagePaths[1])));
        subSubcategoryImageView2.setFitWidth(300);
        subSubcategoryImageView2.setFitHeight(300);
        subSubcategoryImageView2.setEffect(new DropShadow(20, Color.GRAY));
        
        VBox imageVBox2 = new VBox(subSubcategoryImageView2);
        imageVBox2.setAlignment(Pos.TOP_RIGHT);
        imageVBox2.setMaxWidth(300);
        imageVBox2.setMaxHeight(300);
        imageVBox2.setStyle(" -fx-background-radius: 20;");
        imageVBox2.setPadding(new Insets(40, 10, 30, 10));

        HBox imageHBox = new HBox(imageVBox1,imageVBox,imageVBox2);
        imageHBox.setStyle("-fx-background-color:#A7EEA6");
        imageHBox.setPadding(new Insets(0, 10, 0, 10));
        imageHBox.setAlignment(Pos.TOP_CENTER);

        // Create a Timeline for animation
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            // Swap images
            Image temp = subSubcategoryImageView1.getImage();
            subSubcategoryImageView1.setImage(subSubcategoryImageView2.getImage());
            subSubcategoryImageView2.setImage(temp);
        }));

        // Set the timeline to run indefinitely
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Button backButton = new Button("Back to Subcategories");
        backButton.setOnAction(event -> mainContainer.getChildren().setAll(subcategoriesBox));
        backButton.setAlignment(Pos.BOTTOM_CENTER);
        
        VBox backbuttonVBox = new VBox();
        backbuttonVBox.setAlignment(Pos.BASELINE_CENTER);
        backbuttonVBox.getChildren().addAll(backButton);


        subcategoryInfoBox.getChildren().addAll(imageHBox,label);
        subcategoryInfoBox.setAlignment(Pos.TOP_CENTER);
        mainContainer.getChildren().add(subcategoryInfoBox);
        mainContainer.getChildren().add(cropVBox);
        mainContainer.getChildren().add(backbuttonVBox);
        mainContainer.setAlignment(Pos.TOP_CENTER);
    }
    
}

   
