import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test {
    public static void main(String[] args) {

        Map<Integer, String> map = new HashMap<>();

        //Iterate he keys
        Set<Integer> integers = map.keySet();

        //Iterate keys and values
        Set<Map.Entry<Integer, String>> entries = map.entrySet();

        //Iterate values -> very slow operation
        Collection<String> values = map.values();

        for (Map.Entry<Integer, String> pair: map.entrySet()) {
            System.out.printf("%s: %d time/s", pair.getKey(), pair.getValue());
        }
    }
}
