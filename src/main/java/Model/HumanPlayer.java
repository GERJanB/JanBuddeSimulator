package Model;

public class HumanPlayer extends Player {

    public HumanPlayer(Boolean isPlayerA, enumPhase phase) {
        super(isPlayerA, phase);
    }

    @Override
    public Move getNextMove(Move move) {
        //Execute Move of other Player
        movePiece(move);
        return null;
    }
}
