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

    @FXML
    protected void PlacePieces() {
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

        Move m1 = new Move();
        m1.setToRing(1);
        m1.setToPosition(0);

        Move m2 = new Move();
        m2.setToRing(2);
        m2.setToPosition(4);

        Move m3 = new Move();
        m3.setToRing(3);
        m3.setToPosition(7);

        Move m4 = new Move();
        m4.setToRing(1);
        m4.setToPosition(7);

        Move m5 = new Move();
        m5.setToRing(2);
        m5.setToPosition(3);

        Move m6 = new Move();
        m6.setToRing(3);
        m6.setToPosition(0);

        a.PlacePiece(m1);
        a.PlacePiece(m2);
        a.PlacePiece(m3);
        b.PlacePiece(m4);
        b.PlacePiece(m5);
        b.PlacePiece(m6);

        board = referee.getBoard();
    }

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

        int outerOffset = 0;
        int secondOffset = 125;
        int innerOffset = 250;

        var val = rect1.getParent();
        switch (position){
            case 1:
                switch (ring) {
                    case 1: return new double[] {outerOffset, outerOffset};
                    case 2: return new double[] {secondOffset, secondOffset};
                    case 3: return new double[] {innerOffset, innerOffset};
                }
                break;
            case 2:
                switch (ring) {
                    case 1: return new double[] {rect1.getWidth() / 2, outerOffset};
                    case 2: return new double[] {rect1.getWidth() / 2, secondOffset};
                    case 3: return new double[] {rect1.getWidth() / 2, innerOffset};
                }
                break;
            case 3:
                switch (ring) {
                    case 1: return new double[] {rect1.getWidth(), outerOffset};
                    case 2: return new double[] {rect2.getWidth() + secondOffset, secondOffset};
                    case 3: return new double[] {rect3.getWidth() + innerOffset, innerOffset};
                }
                break;
            case 4:
                switch (ring) {
                    case 1: return new double[] {rect1.getWidth(), rect1.getHeight() / 2};
                    case 2: return new double[] {rect2.getWidth() + secondOffset, rect1.getHeight() / 2};
                    case 3: return new double[] {rect3.getWidth() + innerOffset, rect1.getHeight() / 2};
                }
                break;
            case 5:
                switch (ring) {
                    case 1: return new double[] {rect1.getWidth(), rect1.getHeight()};
                    case 2: return new double[] {rect2.getWidth() + secondOffset, rect2.getHeight() + secondOffset};
                    case 3: return new double[] {rect3.getWidth() + innerOffset, rect3.getHeight() + innerOffset};
                }
                break;
            case 6:
                switch (ring) {
                    case 1: return new double[] {rect1.getWidth() / 2, rect1.getHeight()};
                    case 2: return new double[] {rect1.getWidth() / 2, rect2.getHeight() + secondOffset};
                    case 3: return new double[] {rect1.getWidth() / 2, rect3.getHeight() + innerOffset};
                }
                break;
            case 7:
                switch (ring) {
                    case 1: return new double[] {outerOffset, rect1.getHeight()};
                    case 2: return new double[] {secondOffset, rect2.getHeight() + secondOffset};
                    case 3: return new double[] {innerOffset, rect3.getHeight() + innerOffset};
                }
                break;
            case 8:
                switch (ring) {
                    case 1: return new double[] {0, rect1.getHeight() / 2};
                    case 2: return new double[] {secondOffset, rect2.getHeight() / 2 + secondOffset};
                    case 3: return new double[] {innerOffset, rect3.getHeight() / 2 + innerOffset};
                }
                break;
        }

        return null;
    }
}