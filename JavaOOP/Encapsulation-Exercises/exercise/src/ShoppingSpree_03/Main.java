package ShoppingSpree_03;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] personInfo = scanner.nextLine().split(";");
        String[] productInfo = scanner.nextLine().split(";");

        Map<String, Double> persons = new LinkedHashMap<>();
        Map<String, Double> products = new HashMap<>();

        fillMapCollection(persons, personInfo);
        fillMapCollection(products, productInfo);

        String[] input = scanner.nextLine().split(" ");

        List<Person> personsList = new ArrayList<>();

        while (!input[0].equals("END")) {

            String personName = input[0];
            String productName = input[1];

            try {
                double personMoney = persons.get(personName);

                Person person = personsList.stream().filter(p -> p.getName().equals(personName)).findFirst().orElse(null);

                if (person == null) {

                    person = new Person(personName, personMoney);
                    personsList.add(person);
                }

                double productCost = products.get(productName);
                Product product = new Product(productName, productCost);

                person.buyProduct(product);

            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
                break;
            }

            input = scanner.nextLine().split(" ");
        }



        for (var person : personsList) {

            String currentProducts = " ";

            if (person.getProducts().isEmpty()) {

                currentProducts = "Nothing bought";
            }else {

                currentProducts = person.getProducts().stream().map(Product::getName)
                                        .collect(Collectors.joining(", "));
            }

            System.out.printf("%s - %s%n", person.getName(), currentProducts);
        }
    }

    private static void fillMapCollection(Map<String, Double> map, String[] collection) {

        for (var item : collection) {

            String[] personsInfo = item.split("=");
            String key = personsInfo[0];
            double value = Double.parseDouble(personsInfo[1]);

            map.putIfAbsent(key, value);
        }

    }
}
