package netcode.entities;

public class Health {

    private final int limit;

    private int current;

    public Health(final int limit) {
        this.limit = limit;
        this.current = limit;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
}
