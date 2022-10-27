package com.gamereferee;

import Model.Move;
import Model.Player;
import Model.Referee;
import Model.enumPhase;
import javafx.application.Platform;

public class MainPresenter implements IPresenter {
    Referee referee;

    public MainPresenter() {

    }

    public void quitGame() {
        Platform.exit();
    }

    public void startGame() {
        referee = null;

        Player playerA = new Player(true, true, enumPhase.placing);
        Player playerB = new Player(true, false, enumPhase.placing);

        referee = new Referee(playerA, playerB);
    }

    public boolean getCurrentPlayer() {
        return referee.getCurrentPlayer().isPlayerA();
    }

    public Move[] getMoves(int ring, int position) {
        var moves =  referee.getCurrentPlayer().getPossibleMoves(ring, position);

        return moves == null ? new Move[1] : moves;
    }

    public void movePiece(Move move) {
        if (move.getFromPosition() == -1 && move.getFromRing() == -1) {
            referee.getCurrentPlayer().PlacePiece(move);
        } else {
            referee.getCurrentPlayer().movePiece(move);
        }
        if (pieceInMill(move.getToRing(), move.getToPosition())) {
            referee.getCurrentPlayer().setGamePhase(enumPhase.removing);
            if (!referee.AllPiecesInMill()){
                return;
            } else {
                if (referee.getCurrentPlayer().isPiecesEmpty()) {
                    referee.getCurrentPlayer().setGamePhase(enumPhase.moving);
                } else {
                    referee.getCurrentPlayer().setGamePhase(enumPhase.placing);
                }
            }

        }

        switchPlayer();
    }

    public void switchPlayer() {
        referee.SwitchPlayer();
    }

    public boolean piecesEmpty() {
        return referee.getCurrentPlayer().isPiecesEmpty();
    }

    public enumPhase getGamePhase() {
        return referee.getCurrentPlayer().getGamePhase();
    }

    public void setGamePhase(enumPhase phase) {
        referee.getCurrentPlayer().setGamePhase(phase);
    }

    public boolean pieceInMill(int ring, int position) {
        Move move = new Move();
        move.setToPosition(position);
        move.setToRing(ring);

        return referee.GetMill(move);
    }

    public void removePiece(int ring, int position) {
        if (referee.getCurrentPlayer().TakePiece(ring, position)) {
            var otherPlayer = referee.getOtherPlayer();
            if (otherPlayer.isPiecesEmpty() && otherPlayer.getPiecesCountBoard() == 3)
                otherPlayer.setGamePhase(enumPhase.threePieces);
        }
    }

    public String playerName() {
        return referee.getCurrentPlayer().toString();
    }

    public String otherPlayerName() {
        return referee.getOtherPlayer().toString();
    }

    public boolean hasPlayerWon() {
        if (referee.getOtherPlayer().getPiecesCountBoard() == 2 && referee.getOtherPlayer().isPiecesEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public void resetValues() {
        startGame();
    }

    public boolean hasMovesLeft() {
        for (int ring = 0; ring < 3; ring++) {
            var rng = referee.getOtherPlayer().getBoard().getRing(ring).getFields();

            for (int pos = 0; pos < rng.length; pos++) {
                if (rng[pos].getPiece() != null && rng[pos].getPiece().getBelongsPlayerA() == referee.getOtherPlayer().isPlayerA()) {
                    if (referee.getOtherPlayer().getPossibleMoves(ring + 1, pos) != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}