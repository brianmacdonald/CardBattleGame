package netcode.entities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Enemy {

    private final Iterator<List<Action>> actionIterator;

    private final Map<String, CardType> cardRefs = new HashMap<>();

    public Enemy(final Iterator<List<Action>> actionIterator) {
        this.actionIterator = actionIterator;
    }

    public Map<String, CardType> getCardRefs() {
        return cardRefs;
    }

    public List<Action> nextActionList() {
        return this.actionIterator.next();
    }

}
