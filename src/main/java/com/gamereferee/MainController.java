package com.gamereferee;

import Model.*;
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

    private Board board;

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
        createBoard();
    }

    public void PlacePieces() {
        Ring outer = board.getRing(1);
        Ring second = board.getRing(2);
        Ring inner = board.getRing(3);

        for (int i = 0; i < 8; i++) {
            if (outer.getFields()[i].getPiece() != null && outer.getFields()[i].getPiece().getBelongsPlayerA()) {
                var s = getCoordinates(i + 1, 1);
                DrawPiece(true, s[0], s[1]);
            } else if(outer.getFields()[i].getPiece() != null) {
                var s = getCoordinates(i + 1, 1);
                DrawPiece(false, s[0], s[1]);
            }

            if (second.getFields()[i].getPiece() != null && second.getFields()[i].getPiece().getBelongsPlayerA()) {
                var s = getCoordinates(i + 1, 2);
                DrawPiece(true, s[0], s[1]);
            } else if(second.getFields()[i].getPiece() != null) {
                var s = getCoordinates(i + 1, 2);
                DrawPiece(false, s[0], s[1]);
            }

            if (inner.getFields()[i].getPiece() != null && inner.getFields()[i].getPiece().getBelongsPlayerA()) {
                var s = getCoordinates(i + 1, 3);
                DrawPiece(true, s[0], s[1]);
            } else if(inner.getFields()[i].getPiece() != null) {
                var s = getCoordinates(i + 1, 3);
                DrawPiece(false, s[0], s[1]);
            }
        }
    }

    private void createBoard() {
        Player a = new Player(true,true);
        Player b = new Player(true,false);

        Referee referee = new Referee(a,b);

        Move move = new Move();
        move.setToPosition(0);
        move.setToRing(1);

        Move secondMove = new Move();
        secondMove.setToPosition(0);
        secondMove.setToRing(3);

        a.PlacePiece(move);
        //b.PlacePiece(secondMove);

        board = referee.getBoard();
    }

    @FXML
    public void DrawPiece(boolean isPlayerA, double x, double y) {
        Circle c = new Circle(30);
        c.setStrokeWidth(2);
        c.setStroke(Color.BLACK);
        if (isPlayerA) {
            c.setFill(Color.rgb(50,50,50));
        } else {
            c.setFill(Color.BEIGE);
        }

        piecePane.getChildren().add(c);
        c.setCenterX(x);
        c.setCenterY(y);
    }

    private double[] getCoordinates(int position, int ring) {
        //TODO: Bessere Lösung dafür finden

        var val = rect1.getParent();
        switch (position){
            case 1:
                switch (ring) {
                    case 1: return new double[] {0, 0};
                    case 2: return new double[] {rect2.getLayoutX(), rect2.getParent().getLayoutY()};
                    case 3: return new double[] {rect3.getParent().getLayoutX(), rect3.getParent().getLayoutY()};
                }
                break;
            case 2:
                switch (ring) {
                    case 1: return new double[] {rect1.getWidth() / 2, rect1.getLayoutY()};
                    case 2: return new double[] {rect1.getWidth() / 2, rect2.getLayoutY()};
                    case 3: return new double[] {rect1.getWidth() / 2, rect3.getLayoutY()};
                }
                break;
            case 3:
                switch (ring) {
                    case 1: return new double[] {rect1.getWidth(), rect1.getLayoutY()};
                    case 2: return new double[] {rect2.getLayoutX() + rect2.getWidth(), rect2.getLayoutY()};
                    case 3: return new double[] {rect3.getLayoutX() + rect3.getWidth(), rect3.getLayoutY()};
                }
                break;
            case 4:
                switch (ring) {
                    case 1: return new double[] {rect1.getWidth(), rect1.getHeight() / 2};
                    case 2: return new double[] {rect2.getLayoutX() + rect2.getWidth(), rect1.getHeight() / 2};
                    case 3: return new double[] {rect3.getLayoutX() + rect3.getWidth(), rect1.getHeight() / 2};
                }
                break;
            case 5:
                switch (ring) {
                    case 1: return new double[] {rect1.getWidth(), rect1.getHeight()};
                    case 2: return new double[] {rect2.getLayoutX() + rect2.getWidth(), rect2.getLayoutY() + rect2.getHeight()};
                    case 3: return new double[] {rect3.getLayoutX() + rect3.getWidth(), rect3.getLayoutY() + rect2.getHeight()};
                }
                break;
            case 6:
                switch (ring) {
                    case 1: return new double[] {rect1.getWidth() / 2, rect1.getHeight()};
                    case 2: return new double[] {rect2.getLayoutX() + rect2.getWidth() / 2, rect2.getLayoutY() + rect2.getHeight()};
                    case 3: return new double[] {rect3.getLayoutX() + rect3.getWidth() / 2, rect3.getLayoutY() + rect2.getHeight()};
                }
                break;
            case 7:
                switch (ring) {
                    case 1: return new double[] {rect1.getLayoutX(), rect1.getHeight()};
                    case 2: return new double[] {rect2.getLayoutX(), rect2.getLayoutY() + rect2.getHeight()};
                    case 3: return new double[] {rect3.getLayoutX(), rect3.getLayoutY() + rect2.getHeight()};
                }
                break;
            case 8:
                switch (ring) {
                    case 1: return new double[] {rect1.getLayoutX(), rect1.getHeight() / 2};
                    case 2: return new double[] {rect2.getLayoutX(), rect2.getLayoutY() + rect2.getHeight() / 2};
                    case 3: return new double[] {rect3.getLayoutX(), rect3.getLayoutY() + rect2.getHeight() / 2};
                }
                break;
        }

        return null;
    }
}