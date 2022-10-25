package com.gamereferee;

import Model.Move;
import Model.Player;
import Model.Referee;
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
        Player playerA = new Player(true, true);
        Player playerB = new Player(true, false);

        referee = new Referee(playerA, playerB);
    }

    public boolean getCurrentPlayer() {
        return referee.getCurrentPlayer().isPlayerA();
    }

    public Move[] getMoves(int ring, int position) {
        return referee.getCurrentPlayer().getPossibleMoves(ring, position);
    }

    public boolean movePieceAndIsMill(Move move) {
        if (move.getFromPosition() == -1 && move.getFromRing() == -1) {
            referee.getCurrentPlayer().PlacePiece(move);
        } else {
            referee.getCurrentPlayer().movePiece(move);
        }

        if (referee.MoveIsMill(move) && !referee.AllPiecesInMill()) {
            return true;
        }

        referee.SwitchPlayer();
        return false;
    }

    public boolean piecesLeft() {
        return referee.getCurrentPlayer().isPiecesEmpty();
    }
}