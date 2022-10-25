package WildFarm_03;

public class Cat extends Feline {

    private String breed;

    public Cat(String animalName, String animalType, Double animalWeight, String livingRegion, String breed) {
        super(animalName, animalType, animalWeight, livingRegion);

        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    public void eat(Food food) {
        foodEaten += food.quantity;
    }

    @Override
    public String toString() {
        return String.format("%s[%s, %s, %s, %s, %d]",
                this.getClass().getSimpleName(), this.animalName, this.breed, df.format(this.animalWeight), this.livingRegion, this.foodEaten);
    }
}
