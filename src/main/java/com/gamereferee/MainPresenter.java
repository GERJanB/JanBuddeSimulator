package com.gamereferee;

import Model.Referee;
import View.DrawView;
import View.IDrawView;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import java.util.concurrent.Callable;

public class MainPresenter {
    public MainPresenter() {

    }

    private void quitGame() {
        Platform.exit();
    }
}