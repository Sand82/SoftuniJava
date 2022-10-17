package HotelReservation_04;

public class PriceCalculator {

    private double pricePerDay;
    private int numberOfDays;
    private Season season;
    private Discount discount;

    public PriceCalculator(double pricePerDay, int numberOfDays, Season season, Discount discount) {
        this.pricePerDay = pricePerDay;
        this.numberOfDays = numberOfDays;
        this.season = season;
        this.discount = discount;
    }

    public double calculateTotalPrice() {

        double priseWithoutDiscount = pricePerDay * numberOfDays * season.getSeasonPower();

        double currDiscount = priseWithoutDiscount * discount.getDiscountPercent() / 100;

        return priseWithoutDiscount - currDiscount;
    }


}
