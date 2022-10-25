package com.gamereferee;

import Model.Player;
import Model.Referee;
import View.DrawView;
import View.IDrawView;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import java.util.concurrent.Callable;

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


}