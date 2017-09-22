package netcode.game.battle;

import netcode.datastores.CardDataStore;
import netcode.entities.Action;
import netcode.entities.Card;
import netcode.entities.CardType;
import netcode.entities.Enemy;
import netcode.entities.Health;
import netcode.entities.Player;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ActionResolverTest {

    @Test
    public void resolve() {
        final Health mockHealth = mock(Health.class);
        when(mockHealth.getCurrent()).thenReturn(10);
        final CardDataStore cardDataStore = mock(CardDataStore.class);
        when(cardDataStore.find("a")).thenReturn(new Card(mockHealth, new CardType("a")));
        final ActionResolver actionResolver = new ActionResolver(cardDataStore);
        final BattleRound battleRoundInput = new BattleRound();

        final Enemy enemy = mock(Enemy.class);
        final Map<String, CardType> enemyCards = new HashMap<>();
        enemyCards.put("a", new CardType("a"));
        when(enemy.getCardRefs()).thenReturn(enemyCards);
        battleRoundInput.setEnemy(enemy);

        final List<Action> actionListE = new ArrayList<>();
        actionListE.add(new Action("attack"));
        actionListE.add(new Action("use:other"));
        battleRoundInput.setEnemyActions(actionListE);

        final Player player = mock(Player.class);
        battleRoundInput.setPlayer(player);

        final List<Action> actionListP = new ArrayList<>();
        actionListP.add(new Action("attack"));
        actionListP.add(new Action("use:other"));
        battleRoundInput.setPlayerActions(actionListP);

        final Map<String, CardType> playerCards = new HashMap<>();
        playerCards.put("a", new CardType("a"));
        when(player.getCardRefs()).thenReturn(playerCards);

        actionResolver.resolve(battleRoundInput);
        verify(mockHealth).setCurrent(9);
    }

}