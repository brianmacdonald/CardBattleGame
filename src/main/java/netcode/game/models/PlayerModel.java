package netcode.game.models;

import java.util.List;

public class PlayerModel {

    private String uuid;

    private List<CardModel> cardModelList;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<CardModel> getCardModelList() {
        return cardModelList;
    }

    public void setCardModelList(List<CardModel> cardModelList) {
        this.cardModelList = cardModelList;
    }
}
