package Model;

import java.util.HashMap;

public class Referee {
    private Board board = new Board();
    private Player currentPlayer;

    private Game game;

    public Referee(Player playerA, Player playerB) {
        game = new Game(playerA, playerB, board, enumPhase.placing);
        playerA.setBoard(board);
        playerB.setBoard(board);
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

    public Boolean AllPiecesInMill() {
        var outerFields = board.getRing(1).getFields();
        var secondFields = board.getRing(2).getFields();
        var innerFields = board.getRing(3).getFields();

        for (int i = 0; i < 3; i++) {
            if (outerFields[i].getPiece() != null && outerFields[i].getPiece().getBelongsPlayerA() != currentPlayer.isPlayerA()) {
                Move moveOuter = new Move();
                moveOuter.setToRing(1);
                moveOuter.setToPosition(i);

                if (!GetMill(moveOuter)) return false;
            }

            if (secondFields[i].getPiece() != null && secondFields[i].getPiece().getBelongsPlayerA() != currentPlayer.isPlayerA()) {
                Move moveSecond = new Move();
                moveSecond.setToRing(2);
                moveSecond.setToPosition(i);

                if (!GetMill(moveSecond)) return false;
            }

            if (innerFields[i].getPiece() != null && innerFields[i].getPiece().getBelongsPlayerA() != currentPlayer.isPlayerA()) {
                Move moveInner = new Move();
                moveInner.setToRing(3);
                moveInner.setToPosition(i);

                if (!GetMill(moveInner)) return false;
            }
        }
        return true;
    }

    public boolean GetMill(Move move) {
        int position = move.getToPosition();

        //check on same Ring
        Field[] fields = currentPlayer.getBoard().getRing(move.getToRing()).getFields();
        if (position % 2 == 0) {
            int val = Math.floorMod(position + 1,8);
            if (((fields[Math.floorMod(position + 1, 8)].getPiece() != null && fields[Math.floorMod(position + 2, 8)].getPiece() != null)
                    && (fields[Math.floorMod(position + 1, 8)].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA()
                    && fields[Math.floorMod(position + 2, 8)].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA()))
                || ((fields[Math.floorMod(position - 1, 8)].getPiece() != null && fields[Math.floorMod(position -2, 8)].getPiece() != null)
                    && (fields[Math.floorMod(position - 1, 8)].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA()
                    && fields[Math.floorMod(position - 2,8)].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA()))) {
                return true;
            }
        } else {
            if ((fields[Math.floorMod(position + 1, 8)].getPiece() != null && fields[Math.floorMod(position - 1, 8)].getPiece() != null)
                    && (fields[Math.floorMod(position + 1, 8)].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA()
                    && fields[Math.floorMod(position - 1, 8)].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA())) {
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
                if ((outerFields[i].getPiece() != null && secondFields[i].getPiece() != null && innerFields[i].getPiece() != null)
                        && (outerFields[i].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA()
                        && secondFields[i].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA()
                        && innerFields[i].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA())) {
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
