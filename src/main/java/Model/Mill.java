package Model;

public class Mill {
    private int[] rings = new int[3];
    private int [] positions = new int[3];
    private boolean isMill;
    private boolean multiRing;

    public Mill(int[] rings, int[] positions, boolean isMill, boolean multiRing) {
        this.rings = rings;
        this.positions = positions;
        this.isMill = isMill;
        this.multiRing = multiRing;
    }

    public int[] getRings() {
        return rings;
    }

    public int[] getPositions() {
        return positions;
    }

    public boolean isMill() {
        return isMill;
    }

    public boolean isMultiRing() {
        return multiRing;
    }
}
