package CardsWithPower_03;

public enum FourSuits {

    CLUBS(0),
    DIAMONDS(13),
    HEARTS(26),
    SPADES(39);

    private int suitsPower;

    FourSuits(int suitsPower) {
        this.suitsPower = suitsPower;
    }

    public int getSuitsPower() {
        return suitsPower;
    }
}
