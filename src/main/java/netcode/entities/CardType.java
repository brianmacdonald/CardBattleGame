package netcode.entities;

import java.util.HashMap;
import java.util.Map;

public class CardType {

    private String cardName;

    private Map<String, CardProperty> cardProperties;

    public CardType(final String cardName) {
        this.cardName = cardName;
        this.cardProperties = new HashMap<>();
    }

    public Map<String, CardProperty> getCardProperties() {
        return cardProperties;
    }

    public String getCardName() {
        return cardName;
    }
}
