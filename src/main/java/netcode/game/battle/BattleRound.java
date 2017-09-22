package netcode.game.battle;


import netcode.entities.Action;
import netcode.entities.Enemy;
import netcode.entities.Player;

import java.util.List;

public class BattleRound {

    private List<Action> playerActions;

    private List<Action> enemyActions;

    private Player player;

    private Enemy enemy;

    public List<Action> getPlayerActions() {
        return playerActions;
    }

    public void setPlayerActions(List<Action> playerActions) {
        this.playerActions = playerActions;
    }

    public List<Action> getEnemyActions() {
        return enemyActions;
    }

    public void setEnemyActions(List<Action> enemyActions) {
        this.enemyActions = enemyActions;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
