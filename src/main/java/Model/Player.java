package Model;

import java.util.Stack;

public class Player {
    private final Boolean isHumanPlayer;
    private final Boolean isPlayerA;

    private Board board;
    private Stack<Piece> pieces;

    public Player(Boolean isHumanPlayer, Boolean isPlayerA) {
        this.isHumanPlayer = isHumanPlayer;
        this.isPlayerA = isPlayerA;

        CreatePieces();
    }

    public Boolean isPlayerA() {
        return isPlayerA;
    }

    public Boolean isPiecesEmpty() {
        return pieces.isEmpty();
    }

    public int getPiecesCountBoard() {
        return board.getPlayerPieces(this);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Boolean MovePiece(Move move) {
        if (getPiecesCountBoard() != 3) {
            switch (move.getFromRing()) {
                case 1:
                    if (move.getToRing() == 3) {
                        return false;
                    }
                    break;
                case 3:
                    if (move.getToRing() == 1) {
                        return false;
                    }
                    break;
            }

            switch (move.getFromPosition()) {
                case 7:
                    if (move.getToPosition() == 0 || move.getToPosition() == 6 || move.getToPosition() == 7) {
                        break;
                    } else {
                        return false;
                    }
                case 0:
                    if (move.getToPosition() == 7 || move.getToPosition() == 1 || move.getToPosition() == 0) {
                        break;
                    }
                    return false;
                case 1:
                    if (move.getToPosition() == 2 || move.getToPosition() == 0 || move.getToPosition() == 1) {
                        break;
                    }
                    return false;
                default:
                    if (move.getToPosition() - move.getFromPosition() == 1
                            || move.getToPosition()- move.getFromPosition() == 0
                            || move.getToPosition() - move.getFromPosition() == -1) {
                        break;
                    }
                    return false;
            }
        }

        if ((move.getFromRing() != move.getToRing() && move.getFromPosition() % 2 == 1) || getPiecesCountBoard() == 3) {
            var fieldsFrom = board.getRing(move.getFromRing()).getFields();
            var fieldsTo = board.getRing(move.getToRing()).getFields();
            if (!fieldsTo[move.getToPosition()].isOccupied()) {
                fieldsTo[move.getToPosition()].setPiece(fieldsFrom[move.getFromPosition()].getPiece());
                fieldsTo[move.getToPosition()].setOccupied(true);
                fieldsFrom[move.getFromPosition()].setPiece(null);
                fieldsFrom[move.getFromPosition()].setOccupied(false);
                return true;
            }
        } else if (move.getToRing() == move.getFromRing()) {
            var fields = board.getRing(move.getToRing()).getFields();
            if (!fields[move.getToPosition()].isOccupied()) {
                fields[move.getToPosition()].setPiece(fields[move.getFromPosition()].getPiece());
                fields[move.getToPosition()].setOccupied(true);
                fields[move.getFromPosition()].setOccupied(false);
                fields[move.getFromPosition()].setPiece(null);
                return true;
            }
        }
        return false;
    }

    public Boolean PlacePiece(Move move) {
        Piece piece = pieces.pop();
        boolean pieceAdded = board.AddPiece(piece, move.getToRing(), move.getToPosition());
        if (!pieceAdded) pieces.push(piece);
        return pieceAdded;
    }

    private void CreatePieces() {
        pieces = new Stack<>();
        //TODO: Anzahl auf 9 ändern
        for (int i = 0; i < 3; i++) {
            Piece piece = new Piece(isPlayerA);
            pieces.push(piece);
        }
    }

    @Override
    public String toString() {
        if (isPlayerA) {
            return "Spieler A";
        } else {
            return "Spieler B";
        }
    }

    public Boolean TakePiece(int ring, int position) {
        if (board.getRing(ring).getFields()[position].getPiece().isInMill()
            || board.getRing(ring).getFields()[position].getPiece().getBelongsPlayerA() == isPlayerA) {
            return false;
        } else {
            board.getRing(ring).getFields()[position].setPiece(null);
            board.getRing(ring).getFields()[position].setOccupied(false);
            return true;
        }
    }
}
