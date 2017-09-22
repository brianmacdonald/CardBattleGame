package netcode.entities;

import java.util.UUID;

public class Card {

    private final String uuid = String.valueOf(UUID.randomUUID());

    private final Health health;

    private final CardType cardType;

    public Card(final Health health, final CardType cardType) {
        this.health = health;
        this.cardType = cardType;
    }

    public CardType getCardType() {
        return cardType;
    }

    public String getUuid() {
        return uuid;
    }

    public Health getHealth() {
        return health;
    }
}
