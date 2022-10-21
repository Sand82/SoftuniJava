package CarShopExtended_02;

public class Audi extends CarImpl implements Rentable {

    private Integer minRendDay;

    private Double pricePerDay;

    public Audi(String model, String color, Integer horsePower, String countryProduced, Integer minRendDay, Double pricePerDay) {
        super(model, color, horsePower, countryProduced);
        this.minRendDay = minRendDay;
        this.pricePerDay = pricePerDay;
    }


    @Override
    public Integer getMinRentDay() {
        return this.getMinRentDay();
    }

    @Override
    public Double getPricePerDay() {
        return this.pricePerDay;
    }

    @Override
    public String toString() {
        return super.toString() +
                System.lineSeparator() +
                String.format("Minimum rental period of %d days. Price per day %f", this.minRendDay, this.pricePerDay);
    }
}
