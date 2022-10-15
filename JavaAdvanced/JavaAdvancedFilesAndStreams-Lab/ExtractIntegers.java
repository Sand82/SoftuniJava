import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Scanner;

public class ExtractIntegers {
    public static void main(String[] args) {

        String directory = System.getProperty("user.dir");
        String path = directory +"\\input.txt";

        try{
            FileInputStream fileInputStream = new FileInputStream(path);
            Scanner scanner = new Scanner(fileInputStream);
            PrintWriter printWriter = new PrintWriter(new FileOutputStream("Intigers-out.txt"));

            while (scanner.hasNext()){

                if (scanner.hasNextInt()) {
                    printWriter.println(scanner.next());
                }else {
                    scanner.next();
                }
            }
            printWriter.close();

        }catch (Exception Ignored){

        }
    }
}
