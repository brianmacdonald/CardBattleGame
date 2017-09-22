package netcode.entities;

import netcode.datastores.CardDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PlayerFactory {

    private final CardDataStore cardDataStore;

    private final GameFlowFactory gameFlowFactory;

    @Autowired
    public PlayerFactory(final CardFactory cardFactory,
                         final CardDataStore cardDataStore,
                         final GameFlowFactory gameFlowFactory) {
        this.cardDataStore = cardDataStore;
        this.gameFlowFactory = gameFlowFactory;
    }

    public Player create() {
        final Player player = new Player();
        final GameFlow gameFlow = gameFlowFactory.create();
        gameFlow.getPlayerModel().getCardModelList().forEach(
                cardModel -> {
                    final Map.Entry<String, CardType> cardTypeEntry = cardDataStore.create(cardModel.getCardType());
                    player.getCardRefs().put(cardTypeEntry.getKey(), cardTypeEntry.getValue());
                });
        return player;
    }


}
