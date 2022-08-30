package Model;

import javafx.scene.layout.Region;

public class Move {
    private Piece piece;
    private int toRow;
    private int toCol;

    public Move(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public int getToRow() {
        return toRow;
    }

    public int getToCol() {
        return toCol;
    }

    public Boolean ValidateMove() {
        //TODO: Validate Move
        return true;
    }
}
