package com.gamereferee;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 800);

        stage.setTitle("Mühle Referee");
        stage.setScene(scene);
        stage.show();

        GraphicsContext gc = new Canvas().getGraphicsContext2D();
        gc.setLineWidth(2.0);

    }

    public static void main(String[] args) {
        launch(args);
    }
}