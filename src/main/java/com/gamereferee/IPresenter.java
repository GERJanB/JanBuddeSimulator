package com.gamereferee;
import Model.Move;
import Model.enumPhase;

public interface IPresenter {
    void quitGame();
    void startGame();
    boolean getCurrentPlayer();

    Move[] getMoves(int ring, int position);
    void movePiece(Move move);
    boolean piecesEmpty();

    enumPhase getGamePhase();
    boolean pieceInMill(int ring, int position);
    void removePiece(int ring, int position);
    String playerName();
    void setGamePhase(enumPhase phase);
    void switchPlayer();
}
