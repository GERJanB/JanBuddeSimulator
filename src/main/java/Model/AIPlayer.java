package Model;

public class AIPlayer extends Player {

    Board board;

    public AIPlayer(Boolean isHumanPlayer, Boolean isPlayerA, enumPhase phase) {
        super(isHumanPlayer, isPlayerA, phase);
        this.board = super.getBoard();
    }

    public void movePiece() {
        for (int i = 0; i < 3; i++) {
            var ring = board.getRing(i).getFields();

            for (int x = 0; x < ring.length; x++) {
                if (ring[x].getPiece().getBelongsPlayerA() == this.isPlayerA()) {
                    var s = this.getPossibleMoves(i + 1, x);
                    for (int moves = 0; moves < s.length; moves++) {
                        if (s[moves] != null) {
                            super.movePiece(s[moves]);
                            return;
                        }
                    }
                }
            }
        }
    }

}
