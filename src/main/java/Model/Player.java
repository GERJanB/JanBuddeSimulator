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
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void MovePiece(Piece piece) {
        Move move = new Move(piece);
        if (move.ValidateMove()) {

        }
    }

    public void PlacePiece(int row, int col) {
        if (!pieces.isEmpty()) {
            Piece piece = pieces.pop();
            piece.setRow(row);
            piece.setCol(col);
            board.AddPiece(piece);
        }
    }
}
