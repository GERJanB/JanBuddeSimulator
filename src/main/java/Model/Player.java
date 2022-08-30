package Model;

public class Player {
    private Boolean isHumanPlayer;
    private Boolean isPlayerA;

    private Board board;

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
}
