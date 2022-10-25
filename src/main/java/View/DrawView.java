package View;

import com.gamereferee.IPresenter;
import com.gamereferee.MainPresenter;
import com.gamereferee.UIPiece;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class DrawView implements IDrawView {
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


    IPresenter presenter = new MainPresenter();

    @FXML
    protected void startGame() {
        setFields();
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
}
