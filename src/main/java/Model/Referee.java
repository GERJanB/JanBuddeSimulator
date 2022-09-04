package Model;

import java.util.HashMap;

public class Referee {
    private Board board = new Board();
    private Player currentPlayer;

    private Game game;

    public Referee(Player playerA, Player playerB) {
        game = new Game(playerA, playerB, board);
        currentPlayer = playerA;
    }

    public Board getBoard() {
        return board;
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

    public Boolean MoveIsMill(Move move) {
        int position = move.getToPosition();

        if (position % 2 == 0) {
            int pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;

            switch (position) {
                case 0: pos1 = 1; pos2 = 2; pos3 = 8; pos4 = 7; break;
                case 2: pos1 = 3; pos2 = 4; pos3 = 1; pos4 = 0; break;
                case 4: pos1 = 5; pos2 = 6; pos3 = 3; pos4 = 2; break;
                case 6: pos1 = 7; pos2 = 8; pos3 = 5; pos4 = 4; break;
            }

            Field[] fields = currentPlayer.getBoard().getRing(move.getToRing()).getFields();
            if ((fields[pos1].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA()
                && fields[pos2].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA())
                || (fields[pos3].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA()
                &&  fields[pos4].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA())) {
                return true;
            }
        } else {
            //check on same Ring
            Field[] fields = currentPlayer.getBoard().getRing(move.getToRing()).getFields();
            if (fields[position - 1].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA()
                && fields[position + 1].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA()) {
                return true;
            }

            //Check for Mills overlapping Rings
            
        }

        return true;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
