package netcode.entities;

public class Action {

    private final String actionType;

    private CardType cardType;

    private boolean applyToPlayer;

    private String cardRef;

    public Action(final String actionType) {
        this.actionType = actionType;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(final CardType cardType) {
        this.cardType = cardType;
    }

    public String getActionType() {
        return actionType;
    }

    @Override
    public String toString() {
        return this.actionType;
    }

    public boolean isApplyToPlayer() {
        return applyToPlayer;
    }

    public void setApplyToPlayer(boolean applyToPlayer) {
        this.applyToPlayer = applyToPlayer;
    }

    public String getCardRef() {
        return cardRef;
    }

    public void setCardRef(String cardRef) {
        this.cardRef = cardRef;
    }
}
