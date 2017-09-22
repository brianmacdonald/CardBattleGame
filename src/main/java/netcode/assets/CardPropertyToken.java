package netcode.assets;

public class CardPropertyToken implements Token {

    public final static String TYPE = "CARD_PROPERTY";

    private String key;

    private String value;

    public CardPropertyToken(final String key, final String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        return TYPE + ": {" + key + ": " + value + "}";
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

}
