package netcode.entities;

import netcode.datastores.CardTypeDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CardFactory {

    private final CardTypeDataStore cardTypeDataStore;
    private final HealthFactory healthFactory;

    @Autowired
    public CardFactory(final CardTypeDataStore cardTypeDataStore,
                       final HealthFactory healthFactory) {
        this.cardTypeDataStore = cardTypeDataStore;
        this.healthFactory = healthFactory;
    }

    public Card createCard(final String typeName) {
        final CardType cardType = cardTypeDataStore.find(typeName);
        return new Card(healthFactory.create(cardType), cardType);
    }

}
