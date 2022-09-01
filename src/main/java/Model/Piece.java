package Model;

public class Piece {
    private Boolean belongsPlayerA;
    private int row;
    private int col;

    public Piece(Boolean belongsPlayerA) {
        this.belongsPlayerA = belongsPlayerA;
    }

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

    public Boolean getBelongsPlayerA() {
        return belongsPlayerA;
    }
}
