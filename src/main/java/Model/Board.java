package Model;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class Board {
    private Ring outerRing = new Ring(0);
    private Ring secondRing = new Ring(1);
    private Ring innerRing = new Ring(2);

    public Board() {

    }

    public Boolean AddPiece(Piece piece, int ring, int position) {
        boolean success = false;
        switch (ring) {
            case 0:
                success = outerRing.AddPiece(piece, position);
            case 1:
                success = secondRing.AddPiece(piece, position);
            case 2:
                success = innerRing.AddPiece(piece, position);
        }

        return success;
    }

    public Ring getOuterRing() {
        return outerRing;
    }

    public Ring getSecondRing() {
        return secondRing;
    }

    public Ring getInnerRing() {
        return innerRing;
    }
}
