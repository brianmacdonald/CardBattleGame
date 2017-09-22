package netcode.datastores;

import netcode.entities.Player;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PlayerDataStore {

    private final Map<String, Player> playerMap = new HashMap<>();

    public void add(final Player player) {
        playerMap.put(player.getUuid(), player);
    }

    public Player find(final String uuid) {
        return playerMap.get(uuid);
    }

}
