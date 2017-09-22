package netcode.entities;

import org.junit.Assert;
import org.junit.Test;

import java.net.URL;

public class GameFlowFactoryTest {

    private final URL testGameFileUrl = getClass().getResource("/netcode/GameFlowFactory/testGameFile.json");

    @Test
    public void create() throws Exception {
        final GameFlowFactory gameFlowFactory = new GameFlowFactory();
        gameFlowFactory.setGameFilePath(testGameFileUrl.getPath());
        final GameFlow out = gameFlowFactory.create();
        Assert.assertEquals(4, out.getEnemyModel().getRoundsModelList().size());
        Assert.assertEquals("attack", out.getEnemyModel().getRoundsModelList().get(0).getActions().get(0));
        Assert.assertEquals("use:FireCard", out.getEnemyModel().getRoundsModelList().get(1).getActions().get(0));
    }

}