package HotelReservation_04;

public enum Season {
    Autumn(1),
    Spring(2),
    Winter(3),
    Summer(4);

    private int seasonPower;

    Season(int seasonPower) {
        this.seasonPower = seasonPower;
    }

    public int getSeasonPower() {
        return seasonPower;
    }
}
