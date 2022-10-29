package Model;

public class Board {
    private Ring outerRing = new Ring();
    private Ring secondRing = new Ring();
    private Ring innerRing = new Ring();

    public Board() {

    }

    public Boolean AddPiece(Piece piece, int ring, int position) {
        boolean success = false;
        switch (ring) {
            case 1:
                success = outerRing.AddPiece(piece, position);
                break;
            case 2:
                success = secondRing.AddPiece(piece, position);
                break;
            case 3:
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
