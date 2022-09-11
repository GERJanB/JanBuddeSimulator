package com.gamereferee;

import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class MainController {
    @FXML
    private BorderPane field;
    @FXML
    private StackPane pane = new StackPane();
    Region mainField = new Region();
    Region rect1 = new Region();
    Region rect2 = new Region();
    Region rect3 = new Region();
    Line line1 = new Line();
    Line line = new Line();

    @FXML
    private void DrawField() {
        mainField.setId("main");
        rect1.setId("rect1");
        rect2.setId("rect2");
        rect3.setId("rect3");

        line1.setStartY(745);
        line1.setStrokeWidth(5);

        line.setStartX(745);
        line.setStrokeWidth(5);

        pane.getChildren().add(mainField);
        pane.getChildren().add(rect1);
        pane.getChildren().add(rect2);
        pane.getChildren().add(line1);
        pane.getChildren().add(line);
        pane.getChildren().add(rect3);

        field.setCenter(pane);
    }
    @FXML
    public void initialize() {
        DrawField();
        DrawPiece();
    }

    @FXML
    private void DrawPiece() {


        Circle c = new Circle();
        c.setRadius(20);
        c.setStrokeWidth(2);
        c.setStroke(Color.BLACK);
        c.fillProperty().set(Color.WHITE);
        pane.getChildren().add(c);
    }
}