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
        switch (move.getFromRing()) {
            case 1:
                if (move.getToRing() != 2 || move.getToRing() != 1) {
                    return false;
                }
            case 3:
                if (move.getToRing() != 2 || move.getToRing() != 3) {
                    return false;
                }
        }

        switch (move.getFromPosition()) {
            case 7:
                if (move.getToPosition() != 0 || move.getToPosition() != 6) return false;
                break;
            case 0:
                if (move.getToPosition() != 7 || move.getToPosition() != 1) return false;
                break;
            case 1:
                if (move.getToPosition() != 2 || move.getToPosition() != 0) return false;
                break;
            default:
                if (move.getToPosition() - move.getFromPosition() != 1
                    || move.getFromPosition() - move.getToPosition() != 1){
                    return false;
                }
                break;
        }

        if (move.getFromRing() != move.getToRing() && move.getFromPosition() % 2 == 1) {
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
        return board.AddPiece(pieces.pop(), move.getToRing(), move.getToPosition());
    }

    private void CreatePieces() {
        pieces = new Stack<>();
        //TODO: Anzahl Steine wieder auf 9 ändern
        for (int i = 0; i < 9; i++) {
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
        if (board.getRing(ring).getFields()[position].getPiece().isInMill()) {
            return false;
        } else {
            board.getRing(ring).getFields()[position].setPiece(null);
            return true;
        }
    }
}
