package PizzaCalories_04;

import java.util.ArrayList;
import java.util.List;

public class Dough {

    private String flourType;
    private String bakingTechnique;
    private double weight;
    private List<String> flours = new ArrayList<>(List.of("White", "Wholegrain"));

    private List<String> backingTechniques = new ArrayList<>(List.of("Crispy", "Chewy", "Homemade"));


    public Dough(String flourType, String bakingTechnique, double weight) {

        this.setFlourType(flourType);
        this.setBakingTechnique(bakingTechnique);
        this.setWeight(weight);
    }

    private void setFlourType(String flourType) {

        if (!flours.contains(flourType)) { //be white or wholegrain

            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.flourType = flourType;
    }

    private void setBakingTechnique(String bakingTechnique) {

        if (!backingTechniques.contains(bakingTechnique)) { // crispy, chewy, or homemade

            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.bakingTechnique = bakingTechnique;
    }

    private void setWeight(double weight) {

        if (weight <= 0 || weight > 200) {

            throw new IllegalArgumentException("Dough weight should be in the range");
        }
        this.weight = weight;
    }

    public double calculateCalories() {

        DoughModifiers doughFlourTypeEnum = DoughModifiers.valueOf(this.flourType.toUpperCase());

        DoughModifiers doughBakingTechniqueEnum = DoughModifiers.valueOf(this.bakingTechnique.toUpperCase());

        double result = 2 * this.weight * doughFlourTypeEnum.getCalories() * doughBakingTechniqueEnum.getCalories();

        return result;
    }
}
