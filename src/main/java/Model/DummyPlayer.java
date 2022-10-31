package Model;

public class DummyPlayer extends Player {

    Board board;

    public DummyPlayer(Boolean isPlayerA, enumPhase phase) {
        super(isPlayerA, phase);
        this.board = super.getBoard();
    }

    @Override
    public Move getNextMove(Move move) {
        if (move != null) {
            super.movePiece(move);
        }

        Move mv = getMove();

        this.movePiece(mv);

        return mv;
    }

    private Move getMove() {
        for (int ring = 1; ring < 3; ring++) {
            for (int pos = 0; pos < board.getRing(ring).getFields().length; pos++){
                if (board.getRing(ring).getFields()[pos].getPiece().getBelongsPlayerA() == this.isPlayerA()) {
                    var mvs = getPossibleMoves(ring,pos);
                    for (int i = 0; i < mvs.length; i++){
                        if (mvs[i] != null) {
                            return mvs[i];
                        }
                    }
                }
            }
        }

        return null;
    }

}
