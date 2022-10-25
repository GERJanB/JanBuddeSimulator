package Model;

import java.util.HashMap;

public class Referee {
    private Board board = new Board();
    private Player currentPlayer;

    private Game game;

    public Referee(Player playerA, Player playerB) {
        game = new Game(playerA, playerB, board);
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

        for (int i = 0; i < outerFields.length; i++) {
            if (outerFields[i].getPiece() != null && !outerFields[i].getPiece().isInMill()) return false;
            if (secondFields[i].getPiece() != null && !secondFields[i].getPiece().isInMill()) return false;
            if (innerFields[i].getPiece() != null && !innerFields[i].getPiece().isInMill()) return false;
        }
        return true;
    }

    public Boolean MoveIsMill(Move move) {
        if (currentPlayer.getPiecesCountBoard() < 3) {
            return false;
        }
        Mill mill = GetMill(move);

        if (mill == null) {
            return false;
        }

        if (!mill.isMultiRing()) {
            for (int i = 0; i < 3; i++) {
                currentPlayer.getBoard().getRing(mill.getRings()[0]).getFields()[mill.getPositions()[i]].getPiece().setInMill(mill.isMill());
            }
        } else {
            for (int i = 0; i < 3; i++) {
                currentPlayer.getBoard().getRing(mill.getRings()[i]).getFields()[mill.getPositions()[0]].getPiece().setInMill(mill.isMill());
            }
        }
        return mill.isMill();
    }

    private Mill GetMill(Move move) {
        int position = move.getToPosition();

        int[] rings = {-1,-1,-1};
        int[] positions = {-1,-1,-1};

        if (position % 2 == 0) {
            int pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;

            switch (position) {
                case 0: pos1 = 1; pos2 = 2; pos3 = 7; pos4 = 6; break;
                case 2: pos1 = 3; pos2 = 4; pos3 = 1; pos4 = 0; break;
                case 4: pos1 = 5; pos2 = 6; pos3 = 3; pos4 = 2; break;
                case 6: pos1 = 7; pos2 = 8; pos3 = 5; pos4 = 4; break;
            }

            Field[] fields = currentPlayer.getBoard().getRing(move.getToRing()).getFields();
            if ((fields[pos3].getPiece() != null && fields[pos4].getPiece() != null)
                    &&(fields[pos3].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA()
                    && fields[pos4].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA())) {
                rings[0] = move.getToRing();
                positions[0] = pos3;
                positions[1] = pos4;
                positions[2] = position;
                return new Mill(rings, positions, true, false);
            }

            if ((fields[pos1].getPiece() != null && fields[pos2].getPiece() != null)
                    && (fields[pos1].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA()
                    && fields[pos2].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA())){
                rings[0] = move.getToRing();
                positions[0] = pos1;
                positions[1] = pos2;
                positions[2] = position;
                return new Mill(rings, positions, true, false);
            }

        } else {
            //check on same Ring
            int pos1 = position -1;
            int pos2 = position == 7 ? 0 : position + 1;

            Field[] fields = currentPlayer.getBoard().getRing(move.getToRing()).getFields();
            if ((fields[pos1].getPiece() != null && fields[pos2].getPiece() != null)
                    && (fields[pos1].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA()
                    && fields[pos2].getPiece().getBelongsPlayerA() == currentPlayer.isPlayerA())) {
                rings[0] = move.getToRing();
                positions[0] = pos1;
                positions[1] = pos2;
                positions[2] = position;
                return new Mill(rings, positions, true, false);
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
                    rings[0] = 1;
                    rings[1] = 2;
                    rings[2] = 3;
                    positions[0] = i;
                    return new Mill(rings,positions, true, true);
                }
            }
        }

        return null;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
