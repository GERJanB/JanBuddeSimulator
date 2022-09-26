package com.gamereferee;

import Model.*;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class MainController {
    @FXML
    private Label statusUpdates;
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

    UIPiece[] outerUIFields = new UIPiece[8];
    UIPiece[] secondUIFields = new UIPiece[8];
    UIPiece[] innerUIFields = new UIPiece[8];

    private Board board;

    Referee referee;
    Player currentPlayer;

    Move[] currentMoves;

    @FXML
    protected void startGame() {
        setFields();

        Player playerA = new Player(true,true);
        Player playerB = new Player(true,false);

        referee = new Referee(playerA, playerB);
        currentPlayer = referee.getCurrentPlayer();
        playerA.setBoard(board);
        playerB.setBoard(board);
    }

    @FXML
    protected void quitGame() {
        Platform.exit();
    }

    private void DrawField() {
        boardPane.getChildren().removeAll();
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
                var coords = getCoordinates(i + 1, 1);
                DrawPiece(true, coords, 1, i);
            } else if(outer.getFields()[i].getPiece() != null) {
                var coords = getCoordinates(i + 1, 1);
                DrawPiece(false, coords, 1, i);
            }

            if (second.getFields()[i].getPiece() != null && second.getFields()[i].getPiece().getBelongsPlayerA()) {
                var coords = getCoordinates(i + 1, 2);
                DrawPiece(true, coords,2, i);
            } else if(second.getFields()[i].getPiece() != null) {
                var coords = getCoordinates(i + 1, 2);
                DrawPiece(false, coords, 2, i);
            }

            if (inner.getFields()[i].getPiece() != null && inner.getFields()[i].getPiece().getBelongsPlayerA()) {
                var coords = getCoordinates(i + 1, 3);
                DrawPiece(true, coords, 3, i);
            } else if(inner.getFields()[i].getPiece() != null) {
                var coords = getCoordinates(i + 1, 3);
                DrawPiece(false, coords, 3, i);
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

    public void DrawPiece(boolean isPlayerA, double[] coords, int ring, int position) {
        UIPiece uiPiece = new UIPiece(30);
        uiPiece.setStrokeWidth(2);
        uiPiece.setStroke(Color.BLACK);
        if (isPlayerA) {
            uiPiece.setFill(Color.rgb(50,50,50));
        } else {
            uiPiece.setFill(Color.BEIGE);
        }

        piecePane.getChildren().add(uiPiece);
        uiPiece.setCenterX(coords[0]);
        uiPiece.setCenterY(coords[1]);
        uiPiece.setPosition(position);
        uiPiece.setRing(ring);

        EventHandler<MouseEvent> dragPiece = e -> {
            UIPiece piece = (UIPiece) e.getSource();
            piece.setCenterX(e.getX());
            piece.setCenterY(e.getY());
        };

        EventHandler<MouseEvent> dropPiece = e -> {
            for (int i = 0; i < currentMoves.length; i++) {
                if (currentMoves[i] == null)
                    continue;

                switch (currentMoves[i].getToRing()) {
                    case 1 -> outerUIFields[currentMoves[i].getToPosition()].setStroke(Color.TRANSPARENT);
                    case 2 -> secondUIFields[currentMoves[i].getToPosition()].setStroke(Color.TRANSPARENT);
                    case 3 -> innerUIFields[currentMoves[i].getToPosition()].setStroke(Color.TRANSPARENT);
                }
            }

            for (int i = 0; i < currentMoves.length; i++) {
                if (currentMoves[i] == null)
                    continue;
                UIPiece piece = (UIPiece) e.getSource();
                UIPiece field = null;
                switch (currentMoves[i].getToRing()) {
                    case 1 -> field = outerUIFields[currentMoves[i].getToPosition()];
                    case 2 -> field = secondUIFields[currentMoves[i].getToPosition()];
                    case 3 -> field = innerUIFields[currentMoves[i].getToPosition()];
                }

                if (field != null && piece.getBoundsInParent().intersects(field.getBoundsInParent())) {
                    piece.setCenterY(field.getCenterY());
                    piece.setCenterX(field.getCenterX());
                } else {
                    var xy = getCoordinates(piece.getPosition(), piece.getRing());
                    piece.setCenterY(xy[1]);
                    piece.setCenterX(xy[0]);
                }
            }

            currentMoves = null;
        };

        EventHandler<MouseEvent> enterDrag = e -> {
            var piece = (UIPiece) e.getSource();
            currentMoves = currentPlayer.getPossibleMoves(piece.getRing(), piece.getPosition());

            for (int i = 0; i < currentMoves.length; i++) {
                if (currentMoves[i] == null)
                    continue;

                switch (currentMoves[i].getToRing()) {
                    case 1 -> outerUIFields[currentMoves[i].getToPosition()].setStroke(Color.GREEN);
                    case 2 -> secondUIFields[currentMoves[i].getToPosition()].setStroke(Color.GREEN);
                    case 3 -> innerUIFields[currentMoves[i].getToPosition()].setStroke(Color.GREEN);
                }
            }
        };

        uiPiece.setOnMousePressed(enterDrag);
        uiPiece.setOnMouseReleased(dropPiece);
        uiPiece.setOnMouseDragged(dragPiece);
    }

    private double[] getCoordinates(int position, int ring) {
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

    private void setFields() {
        for (int i = 0; i < 8; i++) {
            UIPiece piece1 = new UIPiece(30);
            piece1.setStroke(Color.TRANSPARENT);
            piece1.setStrokeWidth(2);
            piece1.setFill(Color.TRANSPARENT);

            UIPiece piece2 = new UIPiece(30);
            piece2.setStroke(Color.TRANSPARENT);
            piece2.setStrokeWidth(2);
            piece2.setFill(Color.TRANSPARENT);

            UIPiece piece3 = new UIPiece(30);
            piece3.setStroke(Color.TRANSPARENT);
            piece3.setStrokeWidth(2);
            piece3.setFill(Color.TRANSPARENT);


            outerUIFields[i] = piece1;
            secondUIFields[i] = piece2;
            innerUIFields[i] = piece3;

            var outerCoords = getCoordinates(i + 1, 1);
            var secondCoords = getCoordinates(i + 1, 2);
            var innerCoords = getCoordinates(i + 1, 3);

            outerUIFields[i].setCenterX(outerCoords[0]);
            outerUIFields[i].setCenterY(outerCoords[1]);

            secondUIFields[i].setCenterX(secondCoords[0]);
            secondUIFields[i].setCenterY(secondCoords[1]);

            innerUIFields[i].setCenterX(innerCoords[0]);
            innerUIFields[i].setCenterY(innerCoords[1]);

            piecePane.getChildren().add(outerUIFields[i]);
            piecePane.getChildren().add(secondUIFields[i]);
            piecePane.getChildren().add(innerUIFields[i]);
        }
    }
}