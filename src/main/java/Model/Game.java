package Model;

public class Game {
    private Player playerA;
    private Player playerB;

    private Referee referee = new Referee();

    public Game(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;

        playerA.setBoard(new Board());
        playerB.setBoard(new Board());
    }
}
