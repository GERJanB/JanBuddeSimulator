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

    public void AddPiece(Piece piece, int positon) {
        fields[positon].setPiece(piece);
        fields[positon].setOccupied(true);
    }
}
