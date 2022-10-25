package Model;

public class Game {
    private Player playerA;
    private Player playerB;

    public Game(Player playerA, Player playerB, Board board, enumPhase phase) {
        this.playerA = playerA;
        this.playerB = playerB;
        playerA.setBoard(board);
        playerB.setBoard(board);
    }

    public Player getPlayerA() {
        return playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }

    public void setPlayerA(Player playerA) {
        this.playerA = playerA;
    }

    public void setPlayerB(Player playerB) {
        this.playerB = playerB;
    }
}
