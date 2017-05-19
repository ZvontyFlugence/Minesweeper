public class Mine extends Item {

    private static final IS_MINE = true;

    public Mine(boolean mine, Position pos) {
        super(mine, pos);
    }

    public boolean isMine() {
        return IS_MINE;
    }

    public String toString() {
        return "*";
    }
}