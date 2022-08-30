package Model;

import java.util.LinkedList;
import java.util.List;

public class Board {
    private Field[][] fields;

    private List<Piece> pieces;

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

    public String PlacePiece(Piece piece) {
        //TODO: Logik zum plazieren eines Spielsteins implementieren
        pieces.add(piece);
        return "";
    }
}
