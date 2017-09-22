package netcode.game.battle;

import netcode.datastores.CardDataStore;
import netcode.datastores.CardTypeDataStore;
import netcode.entities.CardType;
import netcode.entities.Enemy;
import netcode.entities.EnemyFactory;
import netcode.entities.Player;
import netcode.entities.PlayerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class BattleManager implements Iterator<CompletableFuture<BattleRound>> {

    private final CardTypeDataStore cardTypeDataStore;
    private final CardDataStore cardDataStore;
    private final Player player;
    private final Enemy enemy;
    private PlayerActionManager playerActionManager;
    private ActionResolver actionResolver;

    @Autowired
    public BattleManager(final CardTypeDataStore cardTypeDataStore,
                         final PlayerFactory playerFactory,
                         final EnemyFactory enemyFactory,
                         final CardDataStore cardDataStore,
                         final PlayerActionManager playerActionManager,
                         final ActionResolver actionResolver) {
        this.cardDataStore = cardDataStore;
        this.player = playerFactory.create();
        this.cardTypeDataStore = cardTypeDataStore;
        this.enemy = enemyFactory.create();
        this.playerActionManager = playerActionManager;
        this.actionResolver = actionResolver;
    }

    @Override
    public boolean hasNext() {
        return enemyHasCardsAlive(enemy.getCardRefs());
    }

    @Override
    public CompletableFuture<BattleRound> next() {
        final CompletableFuture<BattleRound> future = new CompletableFuture<>();
        playerActionManager.getPlayerAction().thenAccept(actions -> {
            BattleRound battleRound = new BattleRound();
            battleRound.setPlayerActions(actions);
            battleRound.setEnemyActions(enemy.nextActionList());
            battleRound.setEnemy(enemy);
            battleRound.setPlayer(player);
            battleRound = actionResolver.resolve(battleRound);
            future.complete(battleRound);
        });
        return future;
    }

    private boolean enemyHasCardsAlive(final Map<String, CardType> cardTypeMap) {
        return cardTypeMap.entrySet().stream()
                .filter(stringCardTypeEntry -> cardDataStore.find(stringCardTypeEntry.getKey())
                        .getHealth().getCurrent() > 0)
                .count() > 0;
    }

    public Player getPlayer() {
        return player;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public CardDataStore getCardDataStore() {
        return cardDataStore;
    }

}
