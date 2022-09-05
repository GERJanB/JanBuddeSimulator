package Model;

public class Piece {
    private Boolean belongsPlayerA;

    private boolean isInMill = false;

    public Piece(Boolean belongsPlayerA) {
        this.belongsPlayerA = belongsPlayerA;
    }

    public Boolean getBelongsPlayerA() {
        return belongsPlayerA;
    }

    public boolean isInMill() {
        return isInMill;
    }

    public void setInMill(boolean inMill) {
        isInMill = inMill;
    }
}
