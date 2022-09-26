import java.text.DecimalFormat;
import java.util.*;

public class AcademyGraduation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Map<String, Double> dictionary = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            String studentName = scanner.nextLine();

            double [] grades = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToDouble(Double::parseDouble)
                    .toArray();

            double aveGrades = getAveregeGrade(grades);
            dictionary.putIfAbsent(studentName, aveGrades);


        }
        DecimalFormat myDF = new DecimalFormat("0.#");

        for (var student: dictionary.entrySet()) {
            System.out.printf("%s is graduated with %s %n", student.getKey(), student.getValue().toString());
        }
    }

    private static double getAveregeGrade(double[] grades) {
        double averageValue = 0.0;

        for (int i = 0; i < grades.length; i++) {
            averageValue += grades[i];
        }

        averageValue= averageValue/grades.length;
        return  averageValue;
    }
}
