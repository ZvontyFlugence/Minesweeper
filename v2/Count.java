public class Count extends Item {

    public Count(int value, Position pos) {
        super(value, pos);
    }

    public int getValue() {
        return super.value;
    }

    public String toString() {
        return this.getValue();
    }
}