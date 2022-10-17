package CardsWithPower_03;

public class Card {

    private CardRanks cardRanks;
    private FourSuits fourSuits;

    public Card(CardRanks cardRanks, FourSuits fourSuits) {
        this.cardRanks = cardRanks;
        this.fourSuits = fourSuits;
    }

    public int powerOfCard() {
        return this.fourSuits.getSuitsPower() + cardRanks.getRangPower();
    }
}
