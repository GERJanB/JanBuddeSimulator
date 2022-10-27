package Model;

public class AIPlayer extends Player {

    Board board;

    public AIPlayer(Boolean isHumanPlayer, Boolean isPlayerA, enumPhase phase) {
        super(isHumanPlayer, isPlayerA, phase);
        this.board = super.getBoard();
    }
}
