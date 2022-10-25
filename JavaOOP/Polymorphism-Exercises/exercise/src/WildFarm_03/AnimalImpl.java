package WildFarm_03;

public abstract class AnimalImpl implements Animal {

    protected String animalName;

    protected String animalType;

    protected Double animalWeight;

    protected Integer foodEaten = 0;

    public AnimalImpl(String animalName, String animalType, Double animalWeight) {
        this.animalName = animalName;
        this.animalType = animalType;
        this.animalWeight = animalWeight;
    }

    public abstract void makeSound();

    public abstract void eat(Food food);
}
