package netcode.game.models;

import java.util.List;

public class RoundsModel {

    private final List<String> actions;

    private String defaultAction;

    public RoundsModel(final List<String> actions) {
        this.actions = actions;
    }

    public List<String> getActions() {
        return actions;
    }

    public String getDefaultAction() {
        return defaultAction;
    }

    public void setDefaultAction(String defaultAction) {
        this.defaultAction = defaultAction;
    }
}
