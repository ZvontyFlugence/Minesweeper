public class Count extends BoardItem {

    public Count(int value, Position pos) {
        super(value, pos);
    }

    public String toString() {
        return "" + super.getValue();
    }
}
