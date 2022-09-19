package Model;

import javafx.scene.layout.Region;

public class Move {
    private int fromRing;
    private int toRing;
    private int fromPosition;
    private int toPosition;

    public Move() {

    }

    public Move(int fromRing, int toRing, int fromPosition, int toPosition) {
        this.fromRing = fromRing;
        this.toRing = toRing;
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }

    public int getFromRing() {
        return fromRing;
    }

    public void setFromRing(int fromRing) {
        this.fromRing = fromRing;
    }

    public int getToRing() {
        return toRing;
    }

    public void setToRing(int toRing) {
        this.toRing = toRing;
    }

    public int getFromPosition() {
        return fromPosition;
    }

    public void setFromPosition(int fromPosition) {
        this.fromPosition = fromPosition;
    }

    public int getToPosition() {
        return toPosition;
    }

    public void setToPosition(int toPosition) {
        this.toPosition = toPosition;
    }
}
