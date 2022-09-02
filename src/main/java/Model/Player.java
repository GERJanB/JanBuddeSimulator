package Model;

import java.util.Stack;

public class Player {
    private Boolean isHumanPlayer;
    private Boolean isPlayerA;

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

    public void MovePiece(Move move) {
        boolean valid = move.ValidateMove();
        //TODO: Move Pieces
    }

    public Boolean PlacePiece(int ring, int position) {
       if (board.AddPiece(pieces.pop(), ring - 1, position - 1)) {
           return true;
       }
       return false;
    }

    private void CreatePieces() {
        pieces = new Stack<>();
        //TODO: Anzahl Steine wieder auf 9 ändern
        for (int i = 0; i < 1; i++) {
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
}
