package netcode.entities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class HealthFactory {

    private final String hpMaxProp;
    private final String hpMinProp;

    public HealthFactory(final @Value("${app.health.max.prop:HPMax}") String hpMaxProp,
                         final @Value("${app.health.min.prop:HPMin}") String hpMinProp) {
        this.hpMaxProp = hpMaxProp;
        this.hpMinProp = hpMinProp;
    }

    Health create(final CardType cardType) {
        Health health = new Health(0);
        if (cardType != null) {
            final String max = cardType.getCardProperties().get(hpMaxProp) != null ? cardType.getCardProperties().get(hpMaxProp).getValue() : "15";
            final String min = cardType.getCardProperties().get(hpMinProp) != null ? cardType.getCardProperties().get(hpMinProp).getValue() : "0";
            health = new Health(randHealth(Integer.parseInt(min), Integer.parseInt(max)));
        }
        return health;
    }

    private int randHealth(final int min, final int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

}
