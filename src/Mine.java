public class Mine {
    private boolean mine;
    private Position pos;

    public Mine(boolean mine, Position pos) {
        this.mine = mine;
        this.pos = pos;
    }

    public boolean isMine() {
        return mine;
    }

    public Position getPos() {
        return pos;
    }

    public String toString() {
        return "*";
    }
}
