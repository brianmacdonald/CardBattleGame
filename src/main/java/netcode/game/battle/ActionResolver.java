package netcode.game.battle;

import netcode.datastores.CardDataStore;
import netcode.entities.Action;
import netcode.entities.Card;
import netcode.entities.CardType;
import netcode.entities.Enemy;
import netcode.entities.Player;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiConsumer;

@Component
public class ActionResolver {

    private static final String ATTACK = "attack";
    private final CardDataStore cardDataStore;

    public ActionResolver(final CardDataStore cardDataStore) {
        this.cardDataStore = cardDataStore;
    }

    BattleRound resolve(final BattleRound battleRound) {
        applyEnemyActionToPlayer(battleRound.getEnemyActions(), battleRound.getPlayer());
        applyPlayerActionToEnemy(battleRound.getPlayerActions(), battleRound.getEnemy());
        return battleRound;
    }

    private void applyPlayerActionToEnemy(final List<Action> playerActions,
                                          final Enemy enemy) {
        playerActions.forEach(action -> {
            if (!action.isApplyToPlayer() && ATTACK.equals(action.getActionType())) {
                enemy.getCardRefs().forEach(cardHealthReducer);
            }
        });
    }

    private void applyEnemyActionToPlayer(final List<Action> enemyActions,
                                          final Player player) {
        enemyActions.forEach(action -> {
            if (action.isApplyToPlayer() && ATTACK.equals(action.getActionType())) {
                player.getCardRefs().forEach(cardHealthReducer);
            }
        });
    }

    private BiConsumer<String, CardType> cardHealthReducer = new BiConsumer<String, CardType>() {
        @Override
        public void accept(final String s, final CardType cardType) {
            final Card card = cardDataStore.find(s);
            final int cardHealth = card.getHealth().getCurrent();
            if (cardHealth > 0) {
                card.getHealth().setCurrent(cardHealth - 1);
            }
        }
    };

}
