package netcode.game.battle;

import netcode.entities.Action;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class PlayerActionManager {

    private final Log logger = LogFactory.getLog(PlayerActionManager.class);

    private CompletableFuture<List<Action>> future = new CompletableFuture<>();

    public void addActionList(final List<Action> actionList) {
        logger.debug("Completing Player Action.");
        future.complete(actionList);
    }

    CompletableFuture<List<Action>> getPlayerAction() {
        future = new CompletableFuture<>();
        return future;
    }

}
