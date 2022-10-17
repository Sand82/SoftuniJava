package HotelReservation_04;

public enum Discount {
    VIP(20),
    SecondVisit(10),
    None(0);

    private int discountPercent;

    Discount(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }
}
