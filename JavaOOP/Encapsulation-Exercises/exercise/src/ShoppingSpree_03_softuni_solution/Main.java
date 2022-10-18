package ShoppingSpree_03_softuni_solution;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Person> peopleInfo = new LinkedHashMap<>();
        Map<String, Product> productsInfo = new HashMap<>();

        String[] people = scanner.nextLine().split(";");

        for (String element : people) {
            String[] personData = element.split("=");
            String name = personData[0];
            double money = Double.parseDouble(personData[1]);

            try {

                Person person = new Person(name, money);
                peopleInfo.putIfAbsent(name, person);

            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
                return;
            }
        }

        String[] products = scanner.nextLine().split(";");

        for (String item : products) {

            String[] itemData = item.split("=");
            String name = itemData[0];
            double cost = Double.parseDouble(itemData[1]);

            try {

                Product product = new Product(name, cost);
                productsInfo.putIfAbsent(name, product);

            } catch (IllegalArgumentException ex) {

                System.out.println(ex.getMessage());
            }
        }

        String command = scanner.nextLine();

        while (!command.equals("END")) {

            String [] commandPards = command.split(" ");
            String personName = commandPards[0];
            String productName = commandPards[1];

            try{
                
                Person person = peopleInfo.get(personName);
                Product product = productsInfo.get(productName);
                person.buyProduct(product);

            }catch (IllegalArgumentException ex){

                System.out.println(ex.getMessage());
            }

            command = scanner.nextLine();
        }

        for (var person : peopleInfo.values()) {

            String productList = "";

            if (person.getProducts().size() == 0) {

                productList = "Nothing bought";
            }else  {

                productList = person.getProducts().stream().map(Product::getName).collect(Collectors.joining(", "));
            }
            System.out.print(person.getName() + " - " + productList);
        }
    }
}
