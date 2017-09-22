package netcode.assets;

public class EmptyLineToken implements Token {

    public final static String TYPE = "EMPTY";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        return TYPE;
    }
}
