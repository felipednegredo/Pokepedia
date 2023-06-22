package mobile.pokepedia;

public class ListedMoveModel {

    String moveName;

    String moveType;

    int moveRank;

    public ListedMoveModel(String moveName, String moveType, int moveRank) {
        this.moveName = moveName;
        this.moveType = moveType;
        this.moveRank = moveRank;
    }

public String getMoveName() {
        return moveName;
    }

public String getMoveType() {
        return moveType;
    }

public int getMoveRank() {
        return moveRank;
    }


}
