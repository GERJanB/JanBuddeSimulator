package com.example.gamereferee;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void Test() {
        welcomeText.setText("Hello World");
        Color color1 = Color.rgb(23,23,23);
        welcomeText.setTextFill(color1);
    }
}