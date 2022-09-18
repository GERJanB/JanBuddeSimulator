package com.gamereferee;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class UIPiece extends Circle {
    private int position;
    private int ring;
    public UIPiece(double v) {
        super(v);

    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getRing() {
        return ring;
    }

    public void setRing(int ring) {
        this.ring = ring;
    }
}
