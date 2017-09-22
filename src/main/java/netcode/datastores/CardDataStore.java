package netcode.datastores;

import netcode.entities.Card;
import netcode.entities.CardFactory;
import netcode.entities.CardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

@Component
public class CardDataStore {

    private final Map<String, Card> cardMap = new HashMap<>();

    private final CardFactory cardFactory;

    private final CardTypeDataStore cardTypeDataStore;

    @Autowired
    public CardDataStore(final CardFactory cardFactory,
                         final CardTypeDataStore cardTypeDataStore) {
        this.cardFactory = cardFactory;
        this.cardTypeDataStore = cardTypeDataStore;
    }

    public Map.Entry<String, CardType> create(final String cardType) {
        final Card card = cardFactory.createCard(cardType);
        this.add(card);
        return new AbstractMap.SimpleEntry<>(card.getUuid(), this.cardTypeDataStore.find(cardType));
    }

    public void add(final Card card) {
        cardMap.put(card.getUuid(), card);
    }

    public Card find(final String uuid) {
        return cardMap.get(uuid);
    }

    public Map<String, Card> getCardMap() {
        return cardMap;
    }

}
