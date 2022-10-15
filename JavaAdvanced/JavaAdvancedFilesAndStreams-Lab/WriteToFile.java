import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class WriteToFile {
    public static void main(String[] args){
        Properties prop = System.getProperties();

        String directory = prop.getProperty("user.dir");
        String path = directory + "//input.txt";

        Set<Character> characterToSkip = Set.of(',', '.', '!', '?');

        try{
            FileInputStream fileInputStream = new FileInputStream(path);
            FileOutputStream fileOutputStream = new FileOutputStream("02.WriteToFileOutput.txt");
            
            int currByte = fileInputStream.read();

            while(currByte != -1){

                char symbol = (char) currByte;

                if (!characterToSkip.contains(symbol)) {
                    fileOutputStream.write(symbol);
                }
                currByte = fileInputStream.read();
            }

            fileInputStream.close();

        }catch (Exception Ignored){}


        System.out.println();
    }
}
