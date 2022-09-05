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
                break;
            case 1:
                success = secondRing.AddPiece(piece, position);
                break;
            case 2:
                success = innerRing.AddPiece(piece, position);
                break;
        }

        return success;
    }

    public Ring getRing(int ring) {
        switch (ring) {
            case 1: return outerRing;
            case 2: return secondRing;
            case 3: return innerRing;
            default: throw new IllegalArgumentException();
        }
    }

    public int getPlayerPieces(Player player) {
        var outer = outerRing.getFields();
        var second = secondRing.getFields();
        var inner = innerRing.getFields();

        int counter = 0;

        for (int i = 0; i < outer.length; i++) {
            if (outer[i].getPiece() != null && outer[i].getPiece().getBelongsPlayerA() == player.isPlayerA()) {
                counter++;
            }

            if (second[i].getPiece() != null && second[i].getPiece().getBelongsPlayerA() == player.isPlayerA()) {
                counter++;
            }

            if (inner[i].getPiece() != null && inner[i].getPiece().getBelongsPlayerA() == player.isPlayerA()) {
                counter++;
            }
        }
        return counter;
    }
}
