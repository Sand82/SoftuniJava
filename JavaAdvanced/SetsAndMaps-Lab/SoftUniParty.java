import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class SoftUniParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        Set<String> party = new TreeSet<>();

        while (!input.equals("PARTY")){
            party.add(input);

            input = scanner.nextLine();
        }

        while (!input.equals("END")){

            party.remove(input);

            input = scanner.nextLine();
        }

        System.out.println(party.size());
        for (String guest: party
             ) {
            System.out.println(guest);
        }
    }
}
