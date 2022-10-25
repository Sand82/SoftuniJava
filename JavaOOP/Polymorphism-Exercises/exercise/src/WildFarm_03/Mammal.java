package WildFarm_03;

import java.text.DecimalFormat;

public abstract class Mammal extends AnimalImpl {

    protected String livingRegion;
    protected DecimalFormat df;

    public Mammal(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight);

        this.livingRegion = livingRegion;

        df = new DecimalFormat("#.##");
    }

    @Override
    public String toString() {

        return String.format("%s[%s, %s, %s, %d]",
                this.getClass().getSimpleName(), this.animalName, df.format(this.animalWeight), this.livingRegion, this.foodEaten);
    }
}
