package com.gamereferee;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.Comparator;

public class MainController {
    @FXML
    private Button place;
    @FXML
    private Label Test;
    @FXML
    private BorderPane field;
    @FXML
    private StackPane boardPane = new StackPane();
    @FXML
    private Pane piecePane = new Pane();
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
        piecePane.setMaxSize(750, 750);

        line1.setStartY(745);
        line1.setStrokeWidth(5);

        line.setStartX(745);
        line.setStrokeWidth(5);

        boardPane.getChildren().add(mainField);
        boardPane.getChildren().add(rect1);
        boardPane.getChildren().add(rect2);
        boardPane.getChildren().add(line1);
        boardPane.getChildren().add(line);
        boardPane.getChildren().add(rect3);
        boardPane.getChildren().add(piecePane);

        boardPane.setBackground(new Background(new BackgroundFill(Color.GRAY,null,null)));

        field.setCenter(boardPane);
    }
    @FXML
    public void initialize() {
        DrawField();
    }

    @FXML
    public void DrawPiece(ActionEvent event) {
        Circle c = new Circle(30);
        c.setStrokeWidth(2);
        c.setStroke(Color.BLACK);
        c.setFill(Color.rgb(50,50,50));

        piecePane.getChildren().add(c);
        c.setCenterX(rect1.getWidth() / 2);
        c.setCenterY(rect1.getHeight());
    }
}