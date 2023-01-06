package tools;

public enum VectorDirection {
    /**Vector Unitaire 3D Cartesien**/
    Q_UP(0, -1, +1),
    Q_DOWN(0, +1, -1),
    S_UP(+1, -1, 0),
    S_DOWN(-1, +1, 0),
    R_LEFT(-1, 0, +1),
    R_RIGHT(+1, 0, -1);
    private final int q;
    private final int s;
    private final int r;

    VectorDirection(int q1, int s1, int r1) {
        this.q = q1;
        this.s = s1;
        this.r = r1;
    }

    public int getQ() {
        return q;
    }

    public int getS() {
        return s;
    }

    public int getR() {
        return r;
    }
}
