package com.gamereferee;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    public static void createNewStage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1900, 1000);

        Stage stage = new Stage();
        stage.setTitle("Mühle Referee");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
