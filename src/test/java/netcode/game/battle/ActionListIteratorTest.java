package netcode.game.battle;

import netcode.game.models.RoundsModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ActionListIteratorTest {
    @Test
    public void hasNext() throws Exception {
        final ActionListIterator actionListIterator = new ActionListIterator(new ArrayList<>());
        Assert.assertFalse(actionListIterator.hasNext());
    }

    @Test
    public void next() throws Exception {
        final List<RoundsModel> roundsModelList = new ArrayList<>();
        final List<String> actionListA = new ArrayList<String>(){{
            add("action1");
        }};
        roundsModelList.add(new RoundsModel(actionListA));
        final List<String> actionListB = new ArrayList<String>(){{
            add("action2");
        }};
        roundsModelList.add(new RoundsModel(actionListB));
        final List<String> actionListC = new ArrayList<String>(){{
            add("action3");
        }};
        roundsModelList.add(new RoundsModel(actionListC));
        final ActionListIterator actionListIterator = new ActionListIterator(roundsModelList);
        Assert.assertEquals(actionListIterator.next().get(0).getActionType(), "action1");
        Assert.assertEquals(actionListIterator.next().get(0).getActionType(), "action2");
        Assert.assertEquals(actionListIterator.next().get(0).getActionType(), "action3");
        Assert.assertEquals(actionListIterator.next().get(0).getActionType(), "attack");
    }

}