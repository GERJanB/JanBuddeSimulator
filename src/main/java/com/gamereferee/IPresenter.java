package com.gamereferee;
import Model.Move;

public interface IPresenter {
    void quitGame();
    void startGame();
    boolean getCurrentPlayer();

    Move[] getMoves(int ring, int position);
}
