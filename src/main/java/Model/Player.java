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

    //Nur noch da damit das Testprogramm funktioniert.
    //Für die GUI wird ab jetzt die Methode getPossibleMoves verwendet
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

    public Move[] getPossibleMoves(int ring, int position) {
        Move[] moves;

        if (position % 2 == 1) {
            if (ring != 2) {
                moves = new Move[3];
            } else {
                moves = new Move[4];
            }

            if (position == 7) {
                if (!board.getRing(ring).getFields()[0].isOccupied())
                    moves[0] = new Move(ring, ring, position, 0);
                if (!board.getRing(ring).getFields()[6].isOccupied())
                    moves[1] = new Move(ring, ring, position, position - 1);

                switch (ring) {
                    case 1:
                    case 3:
                        if (!board.getRing(2).getFields()[position].isOccupied())
                            moves[2] = new Move(ring, 2, position, position);
                        break;
                    default:
                        if (!board.getRing(1).getFields()[position].isOccupied())
                            moves[2] = new Move(ring, 1, position, position);
                        if (!board.getRing(3).getFields()[position].isOccupied())
                            moves[3] = new Move(ring, 3, position, position);
                }
            } else {
                if (!board.getRing(ring).getFields()[position + 1].isOccupied())
                    moves[0] = new Move(ring, ring, position, position + 1);
                if (!board.getRing(ring).getFields()[position - 1].isOccupied())
                    moves[1] = new Move(ring, ring, position, position - 1);

                if (ring != 2) {
                    if (!board.getRing(2).getFields()[position].isOccupied())
                        moves[2] = new Move(ring, 2, position, position);
                } else {
                    if (!board.getRing(1).getFields()[position].isOccupied())
                        moves[2] = new Move(ring, 1, position, position);
                    if (!board.getRing(3).getFields()[position].isOccupied())
                        moves[3] = new Move(ring, 3, position, position);
                }
            }
        } else {
            moves = new Move[2];
            if (position == 0) {
                if (!board.getRing(ring).getFields()[7].isOccupied())
                    moves[0] = new Move(ring, ring, position, 7);
                if (!board.getRing(ring).getFields()[1].isOccupied())
                    moves[1] = new Move(ring, ring, position, 1);
            } else {
                if (!board.getRing(ring).getFields()[position - 1].isOccupied())
                    moves[0] = new Move(ring, ring, position, position - 1);
                if (!board.getRing(ring).getFields()[position + 1].isOccupied())
                    moves[1] = new Move(ring, ring, position, position + 1);
            }
        }

        return moves;
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
