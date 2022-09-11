package com.gamereferee;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.Stack;

public class MainController {
    @FXML
    private BorderPane field;

    @FXML
    private void DrawField() {
        StackPane pane = new StackPane();
        Region rect1 = new Region();
        rect1.setId("rect1");

        Region rect2 = new Region();
        rect2.setId("rect2");

        Region rect3 = new Region();
        rect3.setId("rect3");

        Line line = new Line();
        line.setStartY(rect1.getHeight() / 2);
        line.setStrokeWidth(5);
        line.setId("connect");

        pane.getChildren().add(rect1);
        pane.getChildren().add(rect2);
        pane.getChildren().add(rect3);
        pane.getChildren().add(line);

        field.setCenter(pane);
    }
    @FXML
    public void initialize() {
        DrawField();
    }
}