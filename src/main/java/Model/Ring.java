package Model;

public class Ring {
    private Field[] fields = new Field[8];

    public Ring() {
        CreateFields();
    }

    private void CreateFields() {
        for (int i = 0; i < fields.length; i++) {
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
