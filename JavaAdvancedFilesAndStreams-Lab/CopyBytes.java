import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Properties;

public class CopyBytes {
    public static void main(String[] args) {

        Properties directories = System.getProperties();

        String directiry = directories.getProperty("user.dir");
        String path = directiry + "\\input.txt";

        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            FileOutputStream fileOutputStream = new FileOutputStream("03.CopyBytesoutput.txt");
            PrintWriter printWriter = new PrintWriter(fileOutputStream);

            int currByte = fileInputStream.read();

            while (currByte != -1) {

                if (currByte == 10) {

                    printWriter.println();

                } else if (currByte == 32){

                    printWriter.print(" ");
                }else{
                    printWriter.print(currByte);
                }

                currByte = fileInputStream.read();
            }

            printWriter.close();

        } catch (Exception Ignored) {
        }
    }
}