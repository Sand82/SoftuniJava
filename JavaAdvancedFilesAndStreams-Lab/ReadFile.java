import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class ReadFile {
    public static void main(String[] args) {  //throws IOException

//        Scanner scanner = new Scanner(inputStreem);
//        String line = scanner.nextLine();
        String programDir = System.getProperty("user.dir");

        String path = "C:\\SoftUni\\JavaAdvancedFilesAndStreams-Lab\\input.txt";

        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            FileOutputStream fileOutputStream = new FileOutputStream("output.txt");

            int currByte = fileInputStream.read();

            while (currByte != -1) {
                String output = Integer.toBinaryString(currByte) + " ";

                for (var c : output.toCharArray()) {
                    fileOutputStream.write(c);
                }
//                printStreem.write(Integer.toBinaryString(currByte)+ " ");
                currByte = fileInputStream.read();

            }
        } catch (IOException Ignored) {

        }

//            }catch(FileNotFoundException f){
//                System.out.println("Can nt create a file " + f.getMessage());
//            }catch(Exception e ){
//                System.out.println("Files not found" + " " + e.getMessage());
//            }

//        try{
//            FileInputStream fileStreem = new FileInputStream(path);
//            int oneByte = fileStreem.read();
//            while(oneByte >= 0){
//                System.out.printf("%s ", Integer.toBinaryString(oneByte));
//                oneByte = fileStreem.read();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
