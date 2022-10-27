package Model;

public class DummyPlayer extends Player {

    Board board;

    public DummyPlayer(Boolean isHumanPlayer, Boolean isPlayerA, enumPhase phase) {
        super(isHumanPlayer, isPlayerA, phase);
        this.board = super.getBoard();
    }

    @Override
    public Move getNextMove(Move move) {
        if (move != null) {
            this.movePiece(move);
        }

        Move mv = getMove();

        this.movePiece(mv);

        return mv;
    }

    private Move getMove() {
        return null;
    }

}
