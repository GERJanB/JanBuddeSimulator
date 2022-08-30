package Model;

import java.util.LinkedList;

public class Board {
    private Field[][] fields;

    public Board() {

    }

    private void CreateFields() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col< 3; col++) {
                fields[row][col] = new Field(row,col,false);
            }
        }
    }
}
