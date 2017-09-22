package netcode.game.models;

import java.util.List;

public class EnemyModel {

    private List<CardModel> cardModelList;

    private List<RoundsModel> roundsModelList;

    public List<RoundsModel> getRoundsModelList() {
        return roundsModelList;
    }

    public void setRoundsModelList(final List<RoundsModel> roundsModelList) {
        this.roundsModelList = roundsModelList;
    }

    public List<CardModel> getCardModelList() {
        return cardModelList;
    }

    public void setCardModelList(List<CardModel> cardModelList) {
        this.cardModelList = cardModelList;
    }
}
