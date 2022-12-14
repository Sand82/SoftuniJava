package app;

import app.model.enums.Size;
import app.model.ingredients.BasicIngredient;
import app.model.ingredients.Ingredient;
import app.model.labels.BasicLabel;
import app.model.labels.Label;
import app.model.shampoos.Shampoo;
import app.repositories.BasicIngredientRepository;
import app.repositories.BasicLabelRepository;
import app.repositories.BasicShampooRepository;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

import static javax.swing.text.StyleConstants.Size;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private BasicShampooRepository shampooRepository;
    private BasicLabelRepository labelRepository;

    private BasicIngredientRepository ingredientRepository;

    @Autowired
    public ConsoleRunner(BasicShampooRepository shampooRepository, BasicLabelRepository labelRepository, BasicIngredientRepository ingredientRepository) {
        this.shampooRepository = shampooRepository;
        this.labelRepository = labelRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    //@Transactional
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        //findByBrand();
        //this._01_findBySize(scanner);
        //this._02_SelectShampooBySizeAndLabel(scanner);
        //this._03_SelectAllShampooHigherThanGivenPrice(scanner);
        //this._04_SelectAllIngredientByName(scanner);
        //this._05_SelectIngredientByNames();
        //this._06_CountByShampooPrice(scanner);
        //this._07_SelectShampooByIngredients();
        //this._08_SelectShampoosByIngredientsCount(scanner);
        //this._11_UpdateIngredientsByPrice(scanner); // for 09 @Transactional
    }

    private void _11_UpdateIngredientsByPrice(Scanner scanner) {

        Double inputPrice =Double.parseDouble(scanner.nextLine());

        BigDecimal actualPercent = BigDecimal.valueOf(inputPrice);

        this.ingredientRepository.increasePriceByPercent(actualPercent);

    }

    private void _08_SelectShampoosByIngredientsCount(Scanner scanner) {

        int count = Integer.parseInt(scanner.nextLine());

        List<Shampoo> shampoos = shampooRepository.findByIngredientCountLessThan(count);

        printCollection(shampoos);
    }

    private void _07_SelectShampooByIngredients() {

        Set<String> ingredients = Set.of("Berry", "Mineral-Colagen");

        List<Shampoo> shampoos = shampooRepository.findByIngredientsNames(ingredients);

        printCollection(shampoos);
    }

    private void _06_CountByShampooPrice(Scanner scanner) {

        Long priceInput = Long.parseLong(scanner.nextLine());

        BigDecimal price = BigDecimal.valueOf(priceInput);

        int count = shampooRepository.countByPriceLessThan(price);

        System.out.println(count);

    }

    private void _05_SelectIngredientByNames() {

        List<String> names = List.of("Lavender", "Herbs", "Apple");

        List<Ingredient> ingredients = ingredientRepository.findByNameInOrderByPriceAsc(names);

        printCollectionIngredients(ingredients);
    }

    private void _04_SelectAllIngredientByName(Scanner scanner) {

        String charInput = scanner.nextLine();

        List<Ingredient> ingredients = ingredientRepository.findByNameStartingWith(charInput);

        if (ingredients.size() != 0) {

            printCollectionIngredients(ingredients);
        } else {

            System.out.println("Not exist.");
        }
    }

    private void _03_SelectAllShampooHigherThanGivenPrice(Scanner scanner) {

        Long priceInput = Long.parseLong(scanner.nextLine());

        BigDecimal price = BigDecimal.valueOf(priceInput);

        List<Shampoo> shampoos = shampooRepository.findByPriceGreaterThanOrderByPriceDesc(price);

        printCollection(shampoos);
    }

    private void _02_SelectShampooBySizeAndLabel(Scanner scanner) {

        String inputSize = scanner.nextLine();

        String inputLabel = scanner.nextLine();

        Size size = app.model.enums.Size.valueOf(inputSize);

        Long labelId = Long.parseLong(inputLabel);

        BasicLabel label = labelRepository.findById(labelId).get();

        List<Shampoo> shampoos = shampooRepository.findBySizeOrLabelOrderByPriceAsc(size, label);

        printCollection(shampoos);
    }

    private void _01_findBySize(Scanner scanner) {

        String input = scanner.nextLine();

        Size size = app.model.enums.Size.valueOf(input);

        List<Shampoo> shampoos = shampooRepository.findBySizeOrderByIdAsc(size);

        printCollection(shampoos);
    }

    private void findByBrand() {

        List<Shampoo> shampoos = this.shampooRepository.findByBrand("Cotton Fresh");

        printCollection(shampoos);
    }

    private static void printCollectionIngredients(List<Ingredient> ingredients) {
        ingredients.forEach(i -> System.out.println(i.getName() + " " + i.getPrice()));
    }

    private static void printCollection(List<Shampoo> shampoos) {
        shampoos.forEach(ch -> System.out.println(ch.getBrand() + " " + ch.getSize() + " " + ch.getPrice() + "lv."));
    }
}
