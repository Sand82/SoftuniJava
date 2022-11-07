package zoo.entities.animals;

public class TerrestrialAnimal extends BaseAnimal {

    private static double KILOGRAMS = 2.50;

    public TerrestrialAnimal(String name, String kind, double price) {
        super(name, kind, KILOGRAMS, price);
    }

    @Override
    public void eat() {
        KILOGRAMS += 5.70;
    }
}
