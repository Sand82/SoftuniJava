import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class ParkingLot {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(", ");

        Set<String> parking = new LinkedHashSet<>();

        while (!input[0].equals("END")){


            String direction = input[0];
            String carNumber = input[1];

            switch (direction){
                case "IN":
                    parking.add(carNumber);
                    break;
                case "OUT":
                    parking.remove(carNumber);
                    break;
            }

            input = scanner.nextLine().split(", ");
        }

        if (parking.isEmpty()) {
            System.out.println("Parking Lot is Empty");
        }else{
            for (String carNum: parking
                 ) {
                System.out.println(carNum);
            }
        }
    }
}
