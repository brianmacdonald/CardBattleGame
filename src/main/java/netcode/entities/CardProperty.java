package netcode.entities;

public class CardProperty {

    private final String name;

    private final String value;

    public CardProperty(final String name, final String value) {
        this.name = name;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
