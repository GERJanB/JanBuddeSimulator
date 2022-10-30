package Model;

import java.util.Stack;

public abstract class Player {
    private Board board;
    private  Boolean isPlayerA;
    private enumPhase gamePhase;

    private Stack<Piece> pieces;

    public Player(Boolean isPlayerA, enumPhase phase) {
        this.gamePhase = phase;
        this.isPlayerA = isPlayerA;

        CreatePieces();
    }

    private void CreatePieces() {
        pieces = new Stack<>();
        for (int i = 0; i < 9; i++) {
            Piece piece = new Piece(isPlayerA);
            pieces.push(piece);
        }
    }

    @Override
    public String toString() {
        return isPlayerA ? "Schwarz" : "Weiß";
    }

    public abstract Move getNextMove(Move move);

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public Boolean TakePiece(int ring, int position) {
        if (board.getRing(ring).getFields()[position].getPiece().getBelongsPlayerA() == isPlayerA) {
            return false;
        } else {
            board.getRing(ring).getFields()[position].setPiece(null);
            board.getRing(ring).getFields()[position].setOccupied(false);

            return true;
        }
    }

    public int getPiecesCountBoard() {
        return board.getPlayerPieces(this);
    }

    public Boolean isPlayerA() {
        return isPlayerA;
    }

    public Boolean isPiecesEmpty() {
        return pieces.isEmpty();
    }

    public void setGamePhase(enumPhase gamePhase) {
        this.gamePhase = gamePhase;
    }

    public enumPhase getGamePhase() {
        return gamePhase;
    }

    public Move[] getPossibleMoves(int ring, int position) {
        Move[] moves;

        if ((ring == -1 && position == -1) || (this.getPiecesCountBoard() == 3 && this.isPiecesEmpty())) {
            moves = new Move[24] ;
            int counter = 0;
            for (int r = 1; r < 4; r++) {
                for (int p = 0; p < 8; p++) {
                    if (!board.getRing(r).getFields()[p].isOccupied()) {
                        moves[counter] = new Move(r,r,p,p);
                        counter++;
                    }
                }
            }

            return counter == 0 ? null : moves;
        }

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

    public void movePiece(Move move) {
        if ((move.getFromRing() != move.getToRing() && move.getFromPosition() % 2 == 1) || getPiecesCountBoard() == 3) {
            var fields = board.getRing(move.getToRing()).getFields();
            var fieldsFrom = board.getRing(move.getFromRing()).getFields();

            fieldsFrom[move.getFromPosition()].setOccupied(false);
            fields[move.getToPosition()].setOccupied(true);
            fields[move.getToPosition()].setPiece(fieldsFrom[move.getFromPosition()].getPiece());
            fieldsFrom[move.getFromPosition()].setPiece(null);
        } else {
            var fields = board.getRing(move.getToRing()).getFields();

            fields[move.getFromPosition()].setOccupied(false);
            fields[move.getToPosition()].setOccupied(true);
            fields[move.getToPosition()].setPiece(fields[move.getFromPosition()].getPiece());
            fields[move.getFromPosition()].setPiece(null);
        }
    }

    public Boolean PlacePiece(Move move, Piece piece) {
        piece = piece == null ? pieces.pop() : piece;
        boolean pieceAdded = board.AddPiece(piece, move.getToRing(), move.getToPosition());
        if (!pieceAdded) pieces.push(piece);
        return pieceAdded;
    }
}
