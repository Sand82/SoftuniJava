import java.io.File;
import java.util.Arrays;

public class ListFiles {
    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        String path = directory +"\\input.txt";

        File root = new File("C:\\SoftUni\\JavaAdvancedFilesAndStreams-Lab\\Files-and-Streams");

        Arrays.stream(root.listFiles())
                .filter(f -> f.isFile())
                .forEach(f -> System.out.println(f.getName() +": " + "["+ f.length()+"]")
        );
    }
}
