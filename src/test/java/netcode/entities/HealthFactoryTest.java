package netcode.entities;

import org.junit.Assert;
import org.junit.Test;

public class HealthFactoryTest {

    @Test
    public void create() throws Exception {
        final HealthFactory healthFactory = new HealthFactory("HPMax", "HPMin");
        final CardType cardType = new CardType("One");
        cardType.getCardProperties().put("HPMax", new CardProperty("HPMax", "10"));
        cardType.getCardProperties().put("HPMin", new CardProperty("HPMin", "3"));
        final Health health = healthFactory.create(cardType);
        Assert.assertTrue(health.getCurrent() > 0);
        Assert.assertTrue(health.getCurrent() < 10);
    }

    @Test
    public void createForNullCardType() throws Exception {
        final HealthFactory healthFactory = new HealthFactory("HPMax", "HPMin");
        final Health health = healthFactory.create(null);
        Assert.assertEquals(0, health.getCurrent());
    }

}