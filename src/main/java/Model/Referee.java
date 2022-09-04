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
        if (currentPlayer.getPiecesCountBoard() < 3) {
            return false;
        }

        if (currentPlayer.isPiecesEmpty()) {
            //check if Piece was in Mill
            if (currentPlayer.getBoard().getRing(move.getToRing()).getFields()[move.getToPosition()].getPiece().isInMill()) {

            }
        }

        int position = move.getToPosition();

        if (position % 2 == 0) {
            int pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;

            switch (position) {
                case 0: pos1 = 1; pos2 = 2; pos3 = 7; pos4 = 6; break;
                case 2: pos1 = 3; pos2 = 4; pos3 = 1; pos4 = 0; break;
                case 4: pos1 = 5; pos2 = 6; pos3 = 3; pos4 = 2; break;
                case 6: pos1 = 7; pos2 = 8; pos3 = 5; pos4 = 4; break;
            }

            Field[] fields = currentPlayer.getBoard().getRing(move.getToRing()).getFields();
            if ((fields[pos3].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA()
                &&  fields[pos4].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA())) {
                fields[pos3].getPiece().setInMill(true);
                fields[pos4].getPiece().setInMill(true);
                fields[position].getPiece().setInMill(true);
                return true;
            }

            if ((fields[pos1].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA()
                    && fields[pos2].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA())){
                fields[pos1].getPiece().setInMill(true);
                fields[pos2].getPiece().setInMill(true);
                fields[position].getPiece().setInMill(true);
                return true;
            }

        } else {
            //check on same Ring
            int pos1 = position -1;
            int pos2 = position == 7 ? 0 : position + 1;

            Field[] fields = currentPlayer.getBoard().getRing(move.getToRing()).getFields();
            if (fields[pos1].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA()
                && fields[pos2].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA()) {
                fields[pos1].getPiece().setInMill(true);
                fields[pos2].getPiece().setInMill(true);
                fields[position].getPiece().setInMill(true);
                return true;
            }

            //Check for Mills overlapping Rings
            Field[] outerFields = currentPlayer.getBoard().getRing(1).getFields();
            Field[] secondFields = currentPlayer.getBoard().getRing(2).getFields();
            Field[] innerFields = currentPlayer.getBoard().getRing(3).getFields();

            for (int i = 0; i < 8; i++) {
                if (i % 2 == 0) {
                    continue;
                }
                if (outerFields[i].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA()
                    && secondFields[i].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA()
                    && innerFields[i].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA()) {
                    outerFields[i].getPiece().setInMill(true);
                    secondFields[i].getPiece().setInMill(true);
                    innerFields[i].getPiece().setInMill(true);
                    return true;
                }
            }
        }
        return false;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
