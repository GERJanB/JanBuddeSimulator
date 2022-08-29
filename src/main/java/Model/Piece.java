package Model;

public class Piece {
    private int row;
    private int col;
    private boolean belongsPlayerA;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isBelongsPlayerA() {
        return belongsPlayerA;
    }

    public void setBelongsPlayerA(boolean belongsPlayerA) {
        this.belongsPlayerA = belongsPlayerA;
    }
}
