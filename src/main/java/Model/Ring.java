package Model;

public class Ring {
    private Field[] fields = new Field[7];
    private int ring;

    public Ring(int ring) {
        CreateFields();
        this.ring = ring;
    }

    private void CreateFields() {
        for (int i = 0; i < 7; i++) {
            fields[i] = new Field(i, false);
        }
    }

    public Boolean AddPiece(Piece piece, int position) {
        if (!fields[position].isOccupied()) {
            fields[position].setPiece(piece);
            fields[position].setOccupied(true);
            return true;
        }
        return false;
    }

    public Field[] getFields() {
        return fields;
    }
}
