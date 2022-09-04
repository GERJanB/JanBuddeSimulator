package Model;

public class Field {
    private int position;

    private boolean isOccupied;
    private Piece piece;

    public Field(int position, boolean isOccupied) {
        this.position = position;
        this.isOccupied = isOccupied;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
}
