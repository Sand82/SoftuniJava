package ListyIterator_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListyIterator {

    private List<String> collection = new ArrayList<>();
    private int index = 0;

    public ListyIterator(String... elements) {
        this.collection = Arrays.asList(elements);
    }

    public Boolean MoveNext() {
        if (!HasNext()) {
            return false;
        }
        this.index++;
        return true;
    }

    public Boolean HasNext() {
        return index < collection.size() - 1;
    }

    public void print() {
        if (collection.size() == 0) {
             throw new IllegalStateException("Invalid Operation!");
        }else {
            System.out.println(collection.get(index));
        }
    }
}
