package GenericBox_01;

import java.util.ArrayList;
import java.util.List;

public class Box<T> {

    private List<T> list = new ArrayList<>();

    public void addItem(T item) {
        list.add(item);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (var item : list) {
            sb.append(String.format("%s: %s%n", item.getClass().getName(), item));
        }

        return sb.toString();

    }
}
