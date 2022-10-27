package View;

import Model.Move;
import Model.enumPhase;
import com.gamereferee.IPresenter;
import com.gamereferee.MainPresenter;
import com.gamereferee.SceneManager;
import com.gamereferee.UIPiece;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.io.Console;
import java.io.IOException;

public class DrawView implements IDrawView {
    @FXML
    private Label statusUpdates;
    @FXML
    private BorderPane field;
    @FXML
    private StackPane boardPane = new StackPane();
    @FXML
    private Pane piecePane = new Pane();
    @FXML
    private ToggleGroup playerA;
    @FXML
    private ToggleGroup playerB;

    Region mainField = new Region();
    Region rect1 = new Region();
    Region rect2 = new Region();
    Region rect3 = new Region();
    Line line1 = new Line();
    Line line = new Line();

    UIPiece[] outerUIFields = new UIPiece[8];
    UIPiece[] secondUIFields = new UIPiece[8];
    UIPiece[] innerUIFields = new UIPiece[8];


    IPresenter presenter = new MainPresenter();

    @FXML
    protected void startGame() {
        setFields();

        var pA = (RadioMenuItem) playerA.getSelectedToggle();
        var pB = (RadioMenuItem) playerB.getSelectedToggle();

        presenter.startGame(pA.getText(),pB.getText());
        spawnPieces();
        statusUpdates.setText(presenter.playerName() + ", du bist am Zug");
    }

    @FXML
    public void initialize() {
        DrawField();
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

    @FXML
    public void quitGameClick() {
        presenter.quitGame();
    }
    private void spawnPieces() {
        EventHandler<MouseEvent> enterDrag = e -> {
            var uip = (UIPiece) e.getSource();
            switch (presenter.getGamePhase()) {
                case placing:
                case moving:
                case threePieces:
                    if ((uip.getBelongsPlayerA() == presenter.getCurrentPlayer()) && (presenter.piecesEmpty() || (uip.getRing() == -1 && uip.getPosition() == -1))) {
                        switch (uip.getRing()) {
                            case 1 -> outerUIFields[uip.getPosition()].setStroke(Color.BLUE);
                            case 2 -> secondUIFields[uip.getPosition()].setStroke(Color.BLUE);
                            case 3 -> innerUIFields[uip.getPosition()].setStroke(Color.BLUE);
                        }
                        var currentMoves = presenter.getMoves(uip.getRing(), uip.getPosition());

                        for (int i = 0; i < currentMoves.length; i++) {
                            if (currentMoves[i] == null) {
                                continue;
                            }

                            switch (currentMoves[i].getToRing()) {
                                case 1 -> outerUIFields[currentMoves[i].getToPosition()].setStroke(Color.GREEN);
                                case 2 -> secondUIFields[currentMoves[i].getToPosition()].setStroke(Color.GREEN);
                                case 3 -> innerUIFields[currentMoves[i].getToPosition()].setStroke(Color.GREEN);
                            }
                        }
                    }
                    break;
                case removing:
                    if ((uip.getBelongsPlayerA() != presenter.getCurrentPlayer())
                            && (uip.getPosition() != -1 && uip.getRing() != -1)
                            && !presenter.pieceInMill(uip.getRing(), uip.getPosition())) {
                        presenter.removePiece(uip.getRing(), uip.getPosition());
                        piecePane.getChildren().remove(uip);
                        uip = null;

                        if (presenter.hasPlayerWon()) {
                            statusUpdates.setText(presenter.playerName() + ", du hast gewonnen!");
                            finish();
                            break;
                        }

                        if (!presenter.hasMovesLeft()) {
                            statusUpdates.setText(presenter.playerName() + " hat gewonnen, da " + presenter.otherPlayerName() + " keine Spielzüge mehr übrig hat");
                            finish();
                            break;
                        }

                        if (!presenter.piecesEmpty()) {
                            presenter.setGamePhase(enumPhase.placing);
                        } else {
                            presenter.setGamePhase(enumPhase.moving);
                        }

                        presenter.switchPlayer();

                        if (presenter.getGamePhase() == enumPhase.threePieces) {
                            statusUpdates.setText(presenter.playerName() + ", du hast nur noch 3 Steine. Du kannst zu jedem freien Feld springen");
                        } else if (presenter.getGamePhase() == enumPhase.removing) {
                            statusUpdates.setText(presenter.playerName() + ", nimm einen gegnerischen Stein vom Feld");
                        } else {
                            statusUpdates.setText(presenter.playerName() + ", du bist am Zug");
                        }
                    }
                    break;
            }
        };

        EventHandler<MouseEvent> dragPiece = e -> {
            UIPiece uip = (UIPiece) e.getSource();
            switch (presenter.getGamePhase()) {
                case placing:
                case moving:
                case threePieces:
                    if ((uip.getBelongsPlayerA() == presenter.getCurrentPlayer()) && (presenter.piecesEmpty() || (uip.getRing() == -1 && uip.getPosition() == -1))) {
                        uip.setCenterX(e.getX());
                        uip.setCenterY(e.getY());
                    }
                    break;
                case removing:
                    break;
            }
        };

        EventHandler<MouseEvent> dropPiece = e -> {
            UIPiece uip = (UIPiece) e.getSource();

            switch (presenter.getGamePhase()) {
                case placing:
                case moving:
                case threePieces:
                    if ((uip.getBelongsPlayerA() == presenter.getCurrentPlayer()) && (presenter.piecesEmpty() || (uip.getRing() == -1 && uip.getPosition() == -1))){
                        switch (uip.getRing()) {
                            case 1 -> outerUIFields[uip.getPosition()].setStroke(Color.TRANSPARENT);
                            case 2 -> secondUIFields[uip.getPosition()].setStroke(Color.TRANSPARENT);
                            case 3 -> innerUIFields[uip.getPosition()].setStroke(Color.TRANSPARENT);
                        }
                        var currentMoves = presenter.getMoves(uip.getRing(), uip.getPosition());

                        for (int i = 0; i < currentMoves.length; i++) {
                            if (currentMoves[i] == null)
                                continue;

                            switch (currentMoves[i].getToRing()) {
                                case 1 -> outerUIFields[currentMoves[i].getToPosition()].setStroke(Color.TRANSPARENT);
                                case 2 -> secondUIFields[currentMoves[i].getToPosition()].setStroke(Color.TRANSPARENT);
                                case 3 -> innerUIFields[currentMoves[i].getToPosition()].setStroke(Color.TRANSPARENT);
                            }
                        }

                        boolean moved = false;
                        for (int i = 0; i < currentMoves.length; i++) {
                            if (currentMoves[i] == null) {
                                continue;
                            }

                            UIPiece field = null;
                            switch (currentMoves[i].getToRing()) {
                                case 1 -> field = outerUIFields[currentMoves[i].getToPosition()];
                                case 2 -> field = secondUIFields[currentMoves[i].getToPosition()];
                                case 3 -> field = innerUIFields[currentMoves[i].getToPosition()];
                            }

                            if (field != null && uip.getBoundsInParent().intersects(field.getBoundsInParent())) {
                                uip.setCenterX(field.getCenterX());
                                uip.setCenterY(field.getCenterY());
                                presenter.movePiece(new Move(uip.getRing(), currentMoves[i].getToRing(), uip.getPosition(), currentMoves[i].getToPosition()));
                                uip.setRing(currentMoves[i].getToRing());
                                uip.setPosition(currentMoves[i].getToPosition());
                                moved = true;

                                if (!presenter.hasMovesLeft()) {
                                    statusUpdates.setText(presenter.playerName() + " hat gewonnen, da " + presenter.otherPlayerName() + " keine Spielzüge mehr übrig hat");
                                    finish();
                                    break;
                                }

                                if (presenter.getGamePhase() == enumPhase.threePieces) {
                                    statusUpdates.setText(presenter.playerName() + ", du hast nur noch 3 Steine. Du kannst zu jedem freien Feld springen");
                                } else if (presenter.getGamePhase() == enumPhase.removing) {
                                    statusUpdates.setText(presenter.playerName() + ", nimm einen gegnerischen Stein vom Feld");
                                } else {
                                    statusUpdates.setText(presenter.playerName() + ", du bist am Zug");
                                }
                                break;
                            }
                        }
                        if (!moved) {
                            if (uip.getBelongsPlayerA()) {
                                uip.setCenterX(-200);
                                uip.setCenterY(rect1.getHeight() / 2);
                            } else {
                                uip.setCenterX(rect1.getWidth() + 200);
                                uip.setCenterY(rect1.getHeight() / 2);
                            }

                            if (uip.getRing() != -1 && uip.getPosition() != -1) {
                                var xy = getCoordinates(uip.getPosition() + 1, uip.getRing());
                                uip.setCenterY(xy[1]);
                                uip.setCenterX(xy[0]);
                            }
                        }
                    }
                    break;
                case removing:
                    break;
            }


        };

        for (int i = 0; i < 9; i++) {
            UIPiece piece = new UIPiece(30, true);
            piece.setStrokeWidth(2);
            piece.setStroke(Color.BLACK);
            piece.setFill(Color.rgb(50,50,50));

            piecePane.getChildren().add(piece);
            piece.setCenterX(-200);
            piece.setCenterY(rect1.getHeight() / 2);

            piece.setRing(-1);
            piece.setPosition(-1);

            piece.setOnMousePressed(enterDrag);
            piece.setOnMouseDragged(dragPiece);
            piece.setOnMouseReleased(dropPiece);
        }

        for (int i = 0; i < 9; i++) {
            UIPiece piece = new UIPiece(30, false);
            piece.setStrokeWidth(2);
            piece.setStroke(Color.BLACK);
            piece.setFill(Color.BEIGE);

            piecePane.getChildren().add(piece);
            piece.setCenterX(rect1.getWidth() + 200);
            piece.setCenterY(rect1.getHeight() / 2);

            piece.setRing(-1);
            piece.setPosition(-1);

            piece.setOnMousePressed(enterDrag);
            piece.setOnMouseDragged(dragPiece);
            piece.setOnMouseReleased(dropPiece);
        }
    }

    @Override
    public void setStatusUpdate(String status) {
        statusUpdates.setText(status);
    }

    private void finish() {
        boardPane.getChildren().remove(piecePane);
        piecePane = new Pane();
        piecePane.setMaxSize(750, 750);
        boardPane.getChildren().add(piecePane);
        presenter.resetValues();
    }

    @FXML
    private void newWindow() throws IOException {
        SceneManager.createNewStage();
    }
}
