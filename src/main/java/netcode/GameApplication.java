package netcode;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import netcode.entities.Action;
import netcode.game.battle.BattleManager;
import netcode.game.battle.PlayerActionManager;
import netcode.game.models.PlayerActionModel;
import netcode.game.models.PlayerConnectModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@SpringBootApplication
@Component
public class GameApplication {

    private int port;

    @Autowired
    private PlayerActionManager playerActionManager;

    @Autowired
    private BattleManager battleManager;

    public GameApplication(final ApplicationArguments args, PlayerActionManager playerActionManager, BattleManager battleManager) {
        this.playerActionManager = playerActionManager;
        this.battleManager = battleManager;
        if (args.containsOption("port")) {
            this.port = Integer.parseInt(args.getOptionValues("port").get(0));
        } else {
            this.port = 9092;
        }
        this.listen();
    }

    private void listen() {
        final Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(this.port);

        final SocketIOServer server = new SocketIOServer(config);
        server.addEventListener("connection", PlayerConnectModel.class, (client, data, ackRequest) -> {
            server.getBroadcastOperations().sendEvent("battleManager", battleManager);
        });
        server.addEventListener("playerAction", PlayerActionModel.class,
                (client, data, ackRequest) -> {
                    if (battleManager.hasNext()) {
                        battleManager.next().whenComplete((battleRound, throwable) -> {

                            server.getBroadcastOperations().sendEvent("battleRound", battleRound);
                            server.getBroadcastOperations().sendEvent("battleManager", battleManager);
                        });
                        playerActionManager.addActionList(data.getActions().stream().map(Action::new).collect(Collectors.toList()));
                    } else {
                        server.getBroadcastOperations().sendEvent("gameOver", true);
                    }
                });

        server.start();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(GameApplication.class, args);
    }

}