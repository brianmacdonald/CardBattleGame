package netcode.game.battle;

import netcode.entities.Action;
import netcode.game.models.RoundsModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ActionListIterator implements Iterator<List<Action>> {

    private final List<RoundsModel> roundsModelList;

    private int roundsIndex = 0;

    public ActionListIterator(final List<RoundsModel> roundsModelList) {
        this.roundsModelList = roundsModelList;
    }

    private List<Action> create() {
        if (this.roundsIndex < roundsModelList.size()) {
            final RoundsModel roundsModel = roundsModelList.get(this.roundsIndex);
            this.roundsIndex++;
            return createActionList(roundsModel);
        }
        return createDefaultList();
    }

    private List<Action> createDefaultList() {
        final ArrayList<Action> actionList = new ArrayList<>();
        actionList.add(new Action("attack"));
        return actionList;
    }

    private List<Action> createActionList(final RoundsModel roundsModel) {
        if (roundsModel.getActions().size() <= 0) {
            return createDefaultList();
        }
        final List<Action> actions = new ArrayList<>();
        for (final String actionType : roundsModel.getActions()) {
            if (actionType.contains(":")) {
                final String[] actionTypePair = actionType.split(":");
                actions.add(new Action(actionTypePair[0]));
            } else {
                final Action action = new Action(actionType);
                action.setApplyToPlayer(true);
                actions.add(action);
            }
        }
        return actions;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public List<Action> next() {
        return create();
    }
}
