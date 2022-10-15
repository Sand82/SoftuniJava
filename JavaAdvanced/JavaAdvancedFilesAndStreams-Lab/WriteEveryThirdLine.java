import java.io.*;
import java.util.concurrent.ExecutionException;

public class WriteEveryThirdLine {
    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        String path = directory +"\\input.txt";

        try{
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(path)));

            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("every-third-line.txt"));

            String line = reader.readLine();

            int counter = 1;

            while (line != null){

                if (counter % 3 == 0) {
                    writer.write(line);
                    writer.newLine();
                }
                counter++;
                line = reader.readLine();
            }

            reader.close();
            writer.close();

        }catch (Exception Ignored){}
    }
}
