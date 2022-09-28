import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateParty_10 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<String> guests = Arrays.stream(scanner.nextLine().split(" ")).collect(Collectors.toList());

        String [] input = scanner.nextLine().split(" ");


        while (!input[0].equals("Party!")){

            String mainCommand  = input[0];
            String secondaryCommand = input[1];
            String condition = input[2];

            Predicate<String> predicate = null;

            switch (mainCommand){
                case "Double":
                    predicate = findPredicate(secondaryCommand, condition);
                    List<String> newGuest = guests.stream().filter(predicate).collect(Collectors.toList());
                    guests.addAll(newGuest);
                    break;
                case "Remove":
                     findPredicate(secondaryCommand, condition);
                    guests.stream().filter(predicate).map(x-> guests.remove(x)).collect(Collectors.toList());
                    break;
                default:
                    throw  new NoSuchElementException("Command " + mainCommand+" don't exist.");

            }

            input = scanner.nextLine().split(" ");
        }

        if (guests.size() <= 0) {
            System.out.println("Nobody is going to the party!");
        }else {
            System.out.println(String.join(", ", guests) + " are going to the party!");
        }

    }

    private static Predicate<String> findPredicate(String secondaryCommand, String condition) {

        switch (secondaryCommand){
            case"StartsWith":
                return x-> x.startsWith(condition);
            case"EndsWith":
                return x -> x.endsWith(condition);
            case"Length":
                return x -> x.length() == Integer.parseInt(condition);
            default:
                throw  new NoSuchElementException("Invalid command.");
        }
    }
}
