package Collection_02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListyIterator implements Iterable<String>{

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

    @Override
    public Iterator<String> iterator() {

        return new Iterator<String>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return this.index < collection.size();
            }

            @Override
            public String next() {
                if (!hasNext()) {
                    throw new IllegalStateException("Invalid Operation!");
                }

                String element = collection.get(index);
                index ++;

                return element;
            }
        };
    }
}
