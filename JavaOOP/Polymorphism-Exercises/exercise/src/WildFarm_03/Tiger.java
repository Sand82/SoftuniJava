package WildFarm_03;

public class Tiger extends  Feline{

    public Tiger(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("ROAAR!!!");
    }

    @Override
    public void eat(Food food) {
        if (food instanceof Vegetable) {

            System.out.println("Tigers are not eating that type of food!");
        }else {

            foodEaten += food.quantity;
        }
    }
}
