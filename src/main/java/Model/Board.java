package Model;

import java.util.LinkedList;
import java.util.List;

public class Board {
    private Field[][] fields = new Field[3][3];

    public Board() {
        CreateFields();
    }

    private void CreateFields() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col< 3; col++) {
                fields[row][col] = new Field(row,col,false);
            }
        }
    }

    public void AddPiece(Piece piece) {
        fields[piece.getRow()][piece.getCol()].setPiece(piece);
    }
}
