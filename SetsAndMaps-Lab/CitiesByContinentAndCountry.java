import java.util.*;

public class CitiesByContinentAndCountry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Map<String, List<String>>> continents = new LinkedHashMap<>();

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String [] input = scanner.nextLine().split(" ");

            String continent = input[0];
            String country = input[1];
            String city = input[2];

            continents.putIfAbsent(continent, new LinkedHashMap<>());
            Map<String, List<String>> countryCity = continents.get(continent);
            countryCity.putIfAbsent(country, new ArrayList<>());
            List<String> cities = countryCity.get(country);
            cities.add(city);       }

        for (var continet: continents.entrySet()) {
            System.out.println(continet.getKey()+":");
            var currentSyties = continet.getValue();

            for (var city:currentSyties.entrySet()) {

                String towns = String.join(", ", city.getValue());
                System.out.printf(String.format("  %s -> %s %n", city.getKey(), towns));
            }
        }
    }
}
