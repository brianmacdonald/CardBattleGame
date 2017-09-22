package netcode.datastores;

import netcode.assets.CardFileParser;
import netcode.entities.CardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

@Component
public class CardTypeDataStore {

    private final Map<String, CardType> cardTypeMap = new HashMap<>();

    private final CardFileParser cardFileParser;

    @Autowired
    public CardTypeDataStore(CardFileParser cardFileParser) {
        this.cardFileParser = cardFileParser;
    }

    @PostConstruct
    public void initialize() {
        StreamSupport.stream(this.cardFileParser.parseCardFile().spliterator(), false).forEach(this::add);
    }

    public void add(final CardType cardType) {
        cardTypeMap.put(cardType.getCardName(), cardType);
    }

    public CardType find(final String id) {
        return  cardTypeMap.get(id);
    }

}
