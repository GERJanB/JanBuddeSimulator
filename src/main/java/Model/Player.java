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

    public int getPiecesCount() {
        return pieces.size();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void MovePiece(Piece piece) {
        //TODO: Move Pieces
    }

    public String PlacePiece(int ring, int position) {
        if (!pieces.isEmpty()) {
           if (board.AddPiece(pieces.pop(), ring, position)) {
               return "Stein plaziert";
           }
           return "Das Feld ist belegt";
        }
        return "Keine Steine mehr übrig";
    }

    private void CreatePieces() {
        pieces = new Stack<>();
        for (int i = 0; i < 9; i++) {
            Piece piece = new Piece(isPlayerA);
            pieces.push(piece);
        }
    }
}
