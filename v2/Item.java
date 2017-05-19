public class Item {
    private boolean mine;
    private int value;
    private Position pos;

    public Item(boolean mine, Position pos) {
        this.mine = mine;
        this.pos = pos;
    }

    public Item(int value, Position pos) {
        this.value = value;
        this.pos = pos;
    }

    public Position getPos() {
        return pos;
    }
}