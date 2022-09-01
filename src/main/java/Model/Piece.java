package Model;

public class Piece {
    private Boolean belongsPlayerA;

    public Piece(Boolean belongsPlayerA) {
        this.belongsPlayerA = belongsPlayerA;
    }

    public Boolean getBelongsPlayerA() {
        return belongsPlayerA;
    }
}
