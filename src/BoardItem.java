public class BoardItem {
    private boolean mine = false; //In case item is not a Mine
    private Position pos;
    private int value = -1; //In case item is not a Count

    public BoardItem(boolean mine, Position pos) {
        this.mine = mine;
        this.pos = pos;
    }

    public BoardItem(int value, Position pos) {
        this.value = value;
        this.pos = pos;
    }

    public boolean isMine() {
        return mine;
    }

    public int getValue() {
        return value;
    }

    public Position getPos() {
        return pos;
    }
}
