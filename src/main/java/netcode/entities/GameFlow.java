package netcode.entities;

import netcode.game.models.EnemyModel;
import netcode.game.models.PlayerModel;

public class GameFlow {

    private EnemyModel enemyModel;

    private PlayerModel playerModel;

    public EnemyModel getEnemyModel() {
        return enemyModel;
    }

    public void setEnemyModel(EnemyModel enemyModel) {
        this.enemyModel = enemyModel;
    }

    public PlayerModel getPlayerModel() {
        return playerModel;
    }

    public void setPlayerModel(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }
}
