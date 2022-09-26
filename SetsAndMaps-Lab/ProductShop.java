import java.util.*;

public class ProductShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(", ");

        Map<String, LinkedHashMap<String, Double>> shops = new TreeMap<>();

        while (!input[0].equals("Revision")) {

            String shop = input[0];
            String product = input[1];
            Double price = Double.parseDouble(input[2]);

            if (!shops.containsKey(shop)) {
                LinkedHashMap<String, Double> productPrice = new LinkedHashMap<>();
                productPrice.put(product, price);
                shops.put(shop, productPrice);
            }
            shops.get(shop).put(product, price);

            input = scanner.nextLine().split(", ");
        }

        for (var shop : shops.entrySet()) {
            System.out.printf(String.format("%s->%n", shop.getKey()));
            HashMap<String, Double> products = shop.getValue();

            for (var product : products.entrySet()) {
                System.out.printf(String.format("Product: %s, Price: %.1f %n", product.getKey(), product.getValue()));
            }
        }
    }
}
