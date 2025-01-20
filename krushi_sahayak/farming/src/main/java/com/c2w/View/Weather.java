package com.c2w.View;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;

public class Weather {
    private static TextField locationField;
    private static String locationfromTextField;

    private static Label sunsetLabel;
    private static Label sunriseLabel;
    private static Label humidityLabel;
    private static Label tempLabel;
    private static Label pressureLabel;
    private static Label windSpeedLabel;
    private static Label locationLabel;
    private static Label conditionLabel;
    private static Label longitudeLabel;
    private static Label latitudeLabel;

    static public VBox createMainWeatherBox() {

        // Weather.fetchWeatherData();
        VBox mainWeatherBox = new VBox(20);
        mainWeatherBox.setPrefHeight(1000);
        mainWeatherBox.setPrefWidth(850);
        mainWeatherBox.setAlignment(Pos.TOP_LEFT);
        mainWeatherBox.setStyle("-fx-padding: 20px;");

        mainWeatherBox.setStyle(
                "-fx-background-image: url('https://images.unsplash.com/photo-1623190632241-20a391a7b2e0?q=80&w=1886&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'); "
                        +
                        "-fx-background-size: cover;");

        mainWeatherBox.setOpacity(0.8);
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy");
        String formattedDate = today.format(dateFormatter);

        Label dateLabel = new Label(formattedDate);
        dateLabel.setTextFill(Color.BLACK);
        dateLabel.setFont(new Font("ROBOTO", 16));

        Label timeLabel = new Label();
        timeLabel.setStyle("-fx-font-size:16");
        timeLabel.setTextFill(Color.BLACK);
        timeLabel.setFont(new Font("ROBOTO", 30));
        VBox timeAndDate = new VBox(10, timeLabel, dateLabel);
        HBox timeDateAndLocation = new HBox(700);
        timeDateAndLocation.setPadding(new Insets(10, 20, 0, 20));

        Timeline timeline = new Timeline(
                new KeyFrame(javafx.util.Duration.seconds(1), event -> {
                    LocalTime currentTime = LocalTime.now();
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
                    String formattedTime = currentTime.format(timeFormatter);
                    timeLabel.setText(formattedTime);
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        String locationFiledLabel = new String("Enter Location");
        locationField = new TextField();
        locationField.setPromptText(locationFiledLabel);
        locationField.setPrefWidth(400);
        locationField.setMaxWidth(400);
        locationField.setPrefHeight(35);
        locationField.setStyle(
                "-fx-font-size: 14px; " +
                        "-fx-font-family: 'Arial'; " +
                        "-fx-border-color: #5DADE2; " +
                        "-fx-border-radius: 5; " +
                        "-fx-background-radius: 5; " +
                        "-fx-padding: 10;");

        Label goButton = new Label("Go");
        goButton.setPadding(new Insets(10));
        goButton.setStyle(
                "-fx-background-color: #FF9443; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-font-family: 'Arial'; " +
                        "-fx-background-radius: 5; " +
                        "-fx-padding: 10; " +
                        "-fx-cursor: hand;");
        goButton.setOnMouseClicked(event -> {
            locationfromTextField = locationField.getText();
            try {
                if (locationfromTextField != null) {
                    try {
                        fetchWeatherData();
                    } catch (Exception e) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Invalid Location");
                        alert.setHeaderText(null);
                        alert.setContentText("Please enter a valid location.");
                        System.out.println("HTTP GET request failed: ");
                        e.printStackTrace();
                    }
                } else {
                    throw new FetchDataException("Enter Locatioin Properly");
                }
            } catch (FetchDataException e) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText("Enter Location Properly");
            }
            locationField.clear();
        });

        Label weatherLabel = new Label("𝚆𝙴𝙰𝚃𝙷𝙴𝚁");
        weatherLabel.setStyle(
                "-fx-font-size: 40px; " +
                        "-fx-text-fill: BLACK; " +
                        "-fx-padding: 10px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-effect: dropshadow(one-pass-box, black, 8, 0, 2, 2);");
        weatherLabel.setPadding(new Insets(0, 0, 0, 0));

        Image img = new Image("https://cdn-icons-png.flaticon.com/128/4814/4814489.png");
        ImageView imgView = new ImageView(img);

        HBox weatherLabelAndIcon = new HBox(20);
        weatherLabelAndIcon.getChildren().addAll(imgView, weatherLabel);
        weatherLabelAndIcon.setPrefHeight(150);
        weatherLabelAndIcon.setAlignment(Pos.CENTER);
        weatherLabelAndIcon.setPadding(new Insets(0, 0, 0, 0));

        HBox textFieldAndGoButton = new HBox(15, locationField, goButton);
        textFieldAndGoButton.setPadding(new Insets(60, 0, 0, 530));

        humidityLabel = new Label("Humidity: N/A");
        HBox humidityBox = new HBox(humidityLabel);

        pressureLabel = new Label("Pressure: N/A");
        HBox pressureBox = new HBox(pressureLabel);

        windSpeedLabel = new Label("Wind Speed: N/A");
        HBox windSpeedBox = new HBox(windSpeedLabel);

        sunriseLabel = new Label("Sunrise: N/A");
        HBox sunriseBox = new HBox(sunriseLabel);

        sunsetLabel = new Label("Sunset: N/A");
        HBox sunsetBox = new HBox(sunsetLabel);

        tempLabel = new Label("Temperature: N/A");
        HBox tempBox = new HBox(tempLabel);

        conditionLabel = new Label("Condition: N/A");
        HBox conditionBox = new HBox(conditionLabel);

        latitudeLabel = new Label("Latitude: N/A");
        HBox latitudeBox = new HBox(latitudeLabel);

        longitudeLabel = new Label("Longitude: N/A");
        HBox longitudeBox = new HBox(longitudeLabel);

        locationLabel = new Label("Location: N/A");
        locationLabel.setStyle(
                "-fx-text-fill: black; " +
                        "-fx-font-size: 16px; " +
                        "-fx-font-family: 'Roboto'; ");
        Label[] labels = {
                humidityLabel, pressureLabel, windSpeedLabel, sunriseLabel, sunsetLabel,
                tempLabel, conditionLabel, latitudeLabel, longitudeLabel
        };

        for (Label label : labels) {
            label.setStyle(
                    "-fx-text-fill: BLACk; " +
                            "-fx-font-size: 16px; " +
                            "-fx-font-family: 'Roboto'; " +
                            "-fx-padding: 10px; ");
        }
        HBox[] boxes = {
                humidityBox, pressureBox, windSpeedBox, sunriseBox, sunsetBox,
                tempBox, conditionBox, latitudeBox, longitudeBox
        };

        for (HBox box : boxes) {
            box.setStyle(
                    "-fx-background-color: WHITE; " +
                            "-fx-background-radius: 10px; " +
                            "-fx-padding: 5px; ");
            box.setAlignment(Pos.CENTER);
        }

        VBox vb1 = new VBox(30, tempBox, sunriseBox, sunsetBox, conditionBox);
        vb1.setPadding(new Insets(20, 50, 20, 50));

        VBox vb2 = new VBox(30, windSpeedBox, humidityBox, latitudeBox, longitudeBox);
        vb2.setPadding(new Insets(20, 50, 20, 50));

        HBox hb = new HBox(vb1, vb2);
        hb.setAlignment(Pos.CENTER);

        HBox locationLabelBox = new HBox(locationLabel);
        locationLabelBox.setPrefWidth(250);
        locationLabelBox.setMaxWidth(300);
        locationLabelBox.setMinWidth(200);
        VBox.setVgrow(locationLabelBox, null);
        locationLabel.setWrapText(true);

        timeDateAndLocation.getChildren().addAll(locationLabelBox, timeAndDate);

        VBox weatherDetailsBox = new VBox(hb);
        weatherDetailsBox.setAlignment(Pos.CENTER);

        weatherDetailsBox.setStyle(
                "-fx-background-color: rgba(220,220,220, 0.5); " +
                        "-fx-background-radius: 10px; " +
                        "-fx-padding: 20px;");
        weatherDetailsBox.setStyle(
                "-fx-background-color: transparent; -fx-padding: 10px; -fx-border-radius: 10px; -fx-background-radius: 20px;");
        weatherDetailsBox.setAlignment(Pos.CENTER);
        weatherDetailsBox.setMinWidth(700);
        weatherDetailsBox.setMinHeight(400);
        weatherDetailsBox.setPrefWidth(750);
        weatherDetailsBox.setPrefHeight(650);
        weatherDetailsBox.setMaxWidth(800);
        weatherDetailsBox.setMaxHeight(600);
        weatherDetailsBox.setPadding(new Insets(10, 20, 10, 20));

        VBox foreCastBox = new VBox();
        foreCastBox.setOpacity(0.5);
        foreCastBox.setStyle(
                "-fx-background-color: rgba(220,220,220, 0.5); -fx-background-radius: 20; -fx-border-radius: 20; -fx-padding: 10px;");
        foreCastBox.setMinWidth(750);
        foreCastBox.setMinHeight(400);
        foreCastBox.setPrefWidth(750);
        foreCastBox.setPrefHeight(650);
        foreCastBox.setMaxWidth(800);
        foreCastBox.setMaxHeight(600);
        foreCastBox.setPadding(new Insets(10, 20, 10, 20));

        StackPane stackWeatherPane = new StackPane(foreCastBox, weatherDetailsBox);
        stackWeatherPane.setAlignment(Pos.CENTER);
        stackWeatherPane.setMaxHeight(300);

        HBox mainContentBox = new HBox(20);
        mainContentBox.setAlignment(Pos.CENTER);
        mainContentBox.setPadding(new Insets(40, 20, 40, 20));
        mainContentBox.getChildren().addAll(stackWeatherPane);

        HBox contentWithSpacing = new HBox(0);
        contentWithSpacing.setAlignment(Pos.CENTER);
        contentWithSpacing.getChildren().addAll(stackWeatherPane);

        VBox finalLayout = new VBox(20);
        finalLayout.setAlignment(Pos.TOP_CENTER);
        finalLayout.setPadding(new Insets(20));
        finalLayout.getChildren().addAll(timeDateAndLocation, weatherLabelAndIcon, textFieldAndGoButton,
                contentWithSpacing);

        VBox forecastContainer = new VBox(20);
        forecastContainer.setAlignment(Pos.TOP_CENTER);
        forecastContainer.setPadding(new Insets(50, 0, 0, 0));

        finalLayout.getChildren().add(forecastContainer);

        mainWeatherBox.getChildren().addAll(finalLayout);

        return mainWeatherBox;
    }

    private static JSONObject fetchWeatherData() throws Exception {
        final String API_KEY = "cea5dc7680c94ce4a2991417240607";
        final String API_URL = "http://api.weatherapi.com/v1/forecast.json?key=" + API_KEY + "&q="
                + locationfromTextField + "&days=1&aqi=no&alerts=no";
        JSONObject response = null;
        HttpURLConnection httpClient = null;

        URL url = new URL(API_URL);
        httpClient = (HttpURLConnection) url.openConnection();
        httpClient.setRequestMethod("GET");

        int responseCode = httpClient.getResponseCode();
        StringBuffer resp = new StringBuffer();
        try {
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == 500) {
                BufferedReader data = new BufferedReader(new InputStreamReader(httpClient.getInputStream()));
                String tmp;
                while ((tmp = data.readLine()) != null) {
                    resp.append(tmp);
                }
                response = new JSONObject(resp.toString());

                long humidity = response.getJSONObject("current").getLong("humidity");
                long pressure = response.getJSONObject("current").getLong("pressure_mb");
                long windSpeed = response.getJSONObject("current").getLong("wind_kph");
                long latitude = response.getJSONObject("location").getLong("lat");
                long longitude = response.getJSONObject("location").getLong("lon");
                long temp = response.getJSONObject("current").getLong("temp_c");
                String sunrise = response.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0)
                        .getJSONObject("astro").getString("sunrise");
                String sunset = response.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0)
                        .getJSONObject("astro").getString("sunset");

                String condition = response.getJSONObject("current").getJSONObject("condition").getString("text");
                JSONObject locationObject = response.getJSONObject("location");
                String cityName = locationObject.getString("name") + " ";
                String region = locationObject.getString("region") + " ";
                String country = locationObject.getString("country") + " ";
                String tzId = locationObject.getString("tz_id");
                String location = cityName + region + country + tzId;

                // Update the UI labels with the fetched data
                humidityLabel.setText("Humidity: " + humidity + "%");
                pressureLabel.setText("Pressure: " + pressure + " mb");
                windSpeedLabel.setText("Wind Speed: " + windSpeed + " kph");
                sunriseLabel.setText("Sunrise: " + sunrise);
                sunsetLabel.setText("Sunset: " + sunset);
                tempLabel.setText("Temperaute: " + temp + " °C");
                locationLabel.setText("Location: " + location);
                latitudeLabel.setText("Latitude: " + latitude);
                longitudeLabel.setText("Longitude: " + longitude);
                conditionLabel.setText("Condition: " + condition);
                data.close();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Invalid Location");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid location.");
                alert.showAndWait();
                System.out.println("Before respose code");
                System.out.println("HTTP GET request failed: " + responseCode);
            }
            throw new FetchDataException("Invalid Location UserDefined Exception");
        } catch (FetchDataException ex) {
            System.out.println("Invalid Location UserDefined Exception");
        } catch (Exception e) {
            // System.out.println("Location Invalid Exception");
            e.getMessage();
        } finally {
            if (httpClient != null) {
                httpClient.disconnect();
            }
        }
        System.out.println(":::::::::::::::::location:::::::::Data -------Done  " + response.getJSONObject("location"));

        return response;
    }
}

class FetchDataException extends Exception {
    FetchDataException(String str) {
        super(str);
    }
}