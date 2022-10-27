package Model;

public class HumanPlayer extends Player {

    public HumanPlayer(Boolean isHumanPlayer, Boolean isPlayerA, enumPhase phase) {
        super(isHumanPlayer, isPlayerA, phase);
    }

    @Override
    public Move getNextMove(Move move) {
        return null;
    }
}
