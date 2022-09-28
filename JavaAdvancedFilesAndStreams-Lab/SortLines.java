import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class SortLines {
    public static void main(String[] args) throws IOException {
        String directory = System.getProperty("user.dir");
        String path = directory +"\\input.txt";

       List<String> lines = Files.readAllLines(Paths.get(path))
                .stream()
                .sorted()
                .collect(Collectors.toList());

       Path filePath = Path.of("sorted-lines-out.txt");

       Files.createFile(filePath);

        Files.write(filePath,lines, StandardOpenOption.WRITE);
    }
}
