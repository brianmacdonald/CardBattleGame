package netcode.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Player {

    private final String uuid = String.valueOf(UUID.randomUUID());

    private final Map<String, CardType> cardRefs = new HashMap<>();

    public String getUuid() {
        return uuid;
    }

    public Map<String, CardType> getCardRefs() {
        return cardRefs;
    }
}
