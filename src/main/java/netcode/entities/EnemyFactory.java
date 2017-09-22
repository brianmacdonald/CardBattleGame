package netcode.entities;

import netcode.datastores.CardDataStore;
import netcode.game.battle.ActionListIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.StreamSupport;

@Component
public class EnemyFactory {

    private final CardDataStore cardDataStore;

    private final GameFlowFactory gameFlowFactory;

    @Autowired
    EnemyFactory(final CardDataStore cardDataStore,
                 final GameFlowFactory gameFlowFactory) {
        this.cardDataStore = cardDataStore;
        this.gameFlowFactory = gameFlowFactory;
    }

    public Enemy create() {
        final GameFlow gameFlow = gameFlowFactory.create();
        final ActionListIterator actionIterator = new ActionListIterator(gameFlow.getEnemyModel().getRoundsModelList());
        final Enemy enemy = new Enemy(actionIterator);
        StreamSupport.stream(gameFlow.getEnemyModel().getCardModelList().spliterator(), false).forEach(
                cardModel -> {
                    final Map.Entry<String, CardType> cardTypeEntry = cardDataStore.create(cardModel.getCardType());
                    enemy.getCardRefs().put(cardTypeEntry.getKey(), cardTypeEntry.getValue());
                });
        return enemy;
    }


}
