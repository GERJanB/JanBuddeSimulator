package Model;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class Board {
    Ring outerRing = new Ring(0);
    Ring secondRing = new Ring(1);
    Ring innerRing = new Ring(2);

    public Board() {

    }

    public void AddPiece(Piece piece, int ring, int position) {
        switch (ring) {
            case 0 -> outerRing.AddPiece(piece, position);
            case 1 -> secondRing.AddPiece(piece, position);
            case 2 -> innerRing.AddPiece(piece, position);
        }
    }
}
