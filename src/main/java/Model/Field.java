package Model;

public class Field {
    private int row;
    private int col;

    private boolean isOccupied;

    public Field(int row, int col, boolean isOccupied) {
        this.row = row;
        this.col = col;
        this.isOccupied = isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
}
