package com.gamereferee;

import Model.Move;
import Model.Player;
import Model.Referee;
import Model.enumPhase;
import View.DrawView;
import View.IDrawView;
import javafx.application.Platform;

public class MainPresenter implements IPresenter {
    Referee referee;

    public MainPresenter() {

    }

    public void quitGame() {
        Platform.exit();
    }

    public void startGame() {
        Player playerA = new Player(true, true, enumPhase.placing);
        Player playerB = new Player(true, false, enumPhase.placing);

        referee = new Referee(playerA, playerB);
    }

    public boolean getCurrentPlayer() {
        return referee.getCurrentPlayer().isPlayerA();
    }

    public Move[] getMoves(int ring, int position) {
        return referee.getCurrentPlayer().getPossibleMoves(ring, position);
    }

    public void movePiece(Move move) {
        if (move.getFromPosition() == -1 && move.getFromRing() == -1) {
            referee.getCurrentPlayer().PlacePiece(move);
        } else {
            referee.getCurrentPlayer().movePiece(move);
        }
        if (pieceInMill(move.getToRing(), move.getToPosition()) && !referee.AllPiecesInMill()) {
            referee.getCurrentPlayer().setGamePhase(enumPhase.removing);
            return;
        }

        switchPlayer();
    }

    public void switchPlayer() {
        referee.SwitchPlayer();
    }

    public boolean piecesLeft() {
        return referee.getCurrentPlayer().isPiecesEmpty();
    }

    public enumPhase getGamePhase() {
        return referee.getCurrentPlayer().getGamePhase();
    }

    public boolean pieceInMill(int ring, int position) {
        Move move = new Move();
        move.setToPosition(position);
        move.setToRing(ring);

        var mill = referee.GetMill(move);

        if (mill != null) {
            return true;
        }
        return false;
    }

    public void removePiece(int ring, int position) {
        referee.getCurrentPlayer().TakePiece(ring, position);
    }
}