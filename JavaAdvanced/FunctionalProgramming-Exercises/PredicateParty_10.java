import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateParty_10 {

    private static List<String> guests = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        guests = Arrays.stream(scanner.nextLine().split(" ")).collect(Collectors.toList());

        String[] input = scanner.nextLine().split(" ");


        while (!input[0].equals("Party!")) {

            String mainCommand = input[0];
            String secondaryCommand = input[1];
            String condition = input[2];

            Predicate<String> predicate = null;

            switch (mainCommand) {
                case "Double":
                    predicate = findPredicate(secondaryCommand, condition);
                    List<String> newGuest = guests.stream().filter(predicate).collect(Collectors.toList());
                    guests.addAll(0,newGuest);
                    break;
                case "Remove":
                    predicate = findPredicate(secondaryCommand, condition);
                    List<String> collectionToRemove = guests.stream().filter(predicate).collect(Collectors.toList());
                    removeFromGuestList(collectionToRemove);
                    break;
                default:
                    throw new NoSuchElementException("Command " + mainCommand + " don't exist.");

            }

            input = scanner.nextLine().split(" ");
        }

        if (guests.size() == 0) {
            System.out.println("Nobody is going to the party!");
        } else {
            System.out.println(String.join(", ", guests) + " are going to the party!");
        }

    }

    private static void removeFromGuestList(List<String> collectionToRemove) {

        for (var guestToRemove : collectionToRemove) {

            if (guestToRemove.contains(guestToRemove)) {
                guests = guests.stream().filter(x -> x != guestToRemove).collect(Collectors.toList());
            }
        }
    }

    private static Predicate<String> findPredicate(String secondaryCommand, String condition) {

        switch (secondaryCommand) {
            case "StartsWith":
                return x -> x.startsWith(condition);
            case "EndsWith":
                return x -> x.endsWith(condition);
            case "Length":
                return x -> x.length() == Integer.parseInt(condition);
            default:
                throw new NoSuchElementException("Invalid command.");
        }
    }
}
