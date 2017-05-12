public class Mine extends BoardItem {

    public Mine(boolean mine, Position pos) {
        super(mine, pos);
    }

    public String toString() {
        return "*";
    }

}
