package Model;

public class Referee {
    private Board board;
    private Player currentPlayer;

    private Game game;

    public Referee(Player playerA, Player playerB) {
        playerA.setBoard(new Board());
        playerB.setBoard(new Board());
        currentPlayer = playerA;

        board = currentPlayer.getBoard();

        game = new Game(playerA, playerB, board, enumPhase.placing);
    }
    public void SwitchPlayer() {
        if (currentPlayer.isPlayerA()) {
            currentPlayer = game.getPlayerB();
        } else {
            currentPlayer = game.getPlayerA();
        }
    }

    public Boolean AllPiecesInMill() {
        var outerFields = board.getRing(1).getFields();
        var secondFields = board.getRing(2).getFields();
        var innerFields = board.getRing(3).getFields();

        for (int i = 0; i < outerFields.length; i++) {
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
        if (currentPlayer.getGamePhase() != enumPhase.removing) {
            return mill(currentPlayer, move);
        } else {
            if (currentPlayer.isPlayerA()) {
                return mill(game.getPlayerB(), move);
            } else {
                return mill(game.getPlayerA(), move);
            }
        }
    }


    private boolean mill(Player player, Move move) {
        int position = move.getToPosition();

        //check on same Ring
        Field[] fields = player.getBoard().getRing(move.getToRing()).getFields();

        int pOne = Math.floorMod(position + 1, 8);
        int pTwo = Math.floorMod(position + 2, 8);
        int mOne = Math.floorMod(position - 1, 8);
        int mTwo = Math.floorMod(position - 2, 8);

        if (position % 2 == 0) {
            if (((fields[pOne].getPiece() != null && fields[pTwo].getPiece() != null)
                    && (fields[pOne].getPiece().getBelongsPlayerA() == player.isPlayerA()
                    && fields[pTwo].getPiece().getBelongsPlayerA() == player.isPlayerA()))
                    || ((fields[mOne].getPiece() != null && fields[mTwo].getPiece() != null)
                    && (fields[mOne].getPiece().getBelongsPlayerA() == player.isPlayerA()
                    && fields[mTwo].getPiece().getBelongsPlayerA() == player.isPlayerA()))) {
                return true;
            }
        } else {
            if ((fields[pOne].getPiece() != null && fields[mOne].getPiece() != null)
                    && (fields[pOne].getPiece().getBelongsPlayerA() == player.isPlayerA()
                    && fields[mOne].getPiece().getBelongsPlayerA() == player.isPlayerA())) {
                return true;
            }

            //Check for Mills overlapping Rings
            Field[] outerFields = player.getBoard().getRing(1).getFields();
            Field[] secondFields = player.getBoard().getRing(2).getFields();
            Field[] innerFields = player.getBoard().getRing(3).getFields();


            if ((outerFields[position].getPiece() != null && secondFields[position].getPiece() != null && innerFields[position].getPiece() != null)
                    && (outerFields[position].getPiece().getBelongsPlayerA() == player.isPlayerA()
                    && secondFields[position].getPiece().getBelongsPlayerA() == player.isPlayerA()
                    && innerFields[position].getPiece().getBelongsPlayerA() == player.isPlayerA())) {
                return true;
            }
        }
        return false;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getOtherPlayer() {
        if (currentPlayer.isPlayerA()) {
            return game.getPlayerB();
        }
        return game.getPlayerA();
    }
}
