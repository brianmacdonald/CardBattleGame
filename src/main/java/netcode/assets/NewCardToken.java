package netcode.assets;

public class NewCardToken implements Token {

    public final static String TYPE = "NewCard";
    private String cardName = "EMPTY_CARD_NAME";

    public NewCardToken(String cardName) {
        this.cardName = cardName;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        return TYPE + ": " + this.cardName;
    }

    public String getCardName() {
        return this.cardName;
    }

}