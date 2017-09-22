package netcode.entities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import netcode.game.models.CardModel;
import netcode.game.models.EnemyModel;
import netcode.game.models.PlayerModel;
import netcode.game.models.RoundsModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class GameFlowFactory {

    private final Log logger = LogFactory.getLog(GameFlowFactory.class);

    private String gameFileUrl = getClass().getResource("/Game1.json").getPath();

    public GameFlow create() {
        final ObjectMapper objectMapper = new ObjectMapper();
        final GameFlow gameFlow = new GameFlow();
        try {
            final File gameFile = new File(this.gameFileUrl);
            final JsonNode node = objectMapper.readValue(gameFile, JsonNode.class);
            final EnemyModel enemyModel = new EnemyModel();
            enemyModel.setRoundsModelList(createRoundsModelList(objectMapper, node));
            enemyModel.setCardModelList(createCardModelsList(node.get("enemy").get("cards")));
            gameFlow.setEnemyModel(enemyModel);
            final PlayerModel playerModel = new PlayerModel();
            playerModel.setCardModelList(createCardModelsList(node.get("player").get("cards")));
            gameFlow.setPlayerModel(playerModel);
        } catch (final Exception e) {
            logger.error("Error parsing GameFlow file: " + e);
        }
        return gameFlow;
    }

    private List<CardModel> createCardModelsList(final JsonNode node) {
        return StreamSupport.stream(node.spliterator(), false)
                .map(jsonNode -> new CardModel(jsonNode.get("cardType").asText()))
                .collect(Collectors.toList());
    }

    private List<RoundsModel> createRoundsModelList(final ObjectMapper objectMapper, final JsonNode node) {
        return StreamSupport.stream(node.get("enemy").get("rounds").spliterator(), false)
                .map(jsonNode -> new RoundsModel(listReader(objectMapper, jsonNode.get("action"))))
                .collect(Collectors.toList());
    }

    private static List<String> listReader(final ObjectMapper objectMapper, final JsonNode jsonNode) {
        final ObjectReader reader = objectMapper.readerFor(new TypeReference<List<String>>() {});
        try {
            return reader.readValue(jsonNode);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void setGameFilePath(final String gameFileUrl) {
        this.gameFileUrl = gameFileUrl;
    }

}
