import java.util.*;

public class AverageStudentsGrades {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n =Integer.parseInt(scanner.nextLine());

        Map<String, ArrayList<Double>> notes = new TreeMap<>();

        for (int i = 0; i < n; i++) {

           String [] input = scanner.nextLine().split(" ");

            String studentName = input[0];
            double grade = Double.parseDouble(input[1]);

            if (!notes.containsKey(studentName)) {
                notes.put(studentName, new ArrayList<Double>());
            }
            var list = notes.get(studentName);
            list.add(grade);
            notes.put(studentName, list);
        }
        for (String key: notes.keySet()
             ) {

            String stringValue = "";
            Double averageDegree = 0.0;

            for (Double value : notes.get(key)
            ) {
                averageDegree += value;

                String formatedValue = String.format("%.2f", value);
                stringValue += " " + formatedValue;
            }

            averageDegree = averageDegree / notes.get(key).size();

            System.out.println(String.format("%s ->%s (avg: %.2f)", key, stringValue, averageDegree));
        }
    }
}
