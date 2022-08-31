package Model;

public class Referee {
    private Board board;
    private Player currentPlayer;

    private Game game;

    public Referee(Player playerA, Player playerB) {
        game = new Game(playerA, playerB);
    }

    public void SwitchPlayer() {
        this.board = currentPlayer.getBoard();
        if (currentPlayer.isPlayerA()) {
            currentPlayer = game.getPlayerB();
            currentPlayer.setBoard(board);
            game.setPlayerB(currentPlayer);
        } else {
            currentPlayer = game.getPlayerA();
            currentPlayer.setBoard(board);
            game.setPlayerA(currentPlayer);
        }
    }
}
