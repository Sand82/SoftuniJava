package GenericBoxOfInteger_03;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Box<T extends Comparable<T>> {
    private List<T> list = new ArrayList<>();

    public void addItem(T item){
        list.add(item);
    }

    public void swapElements(int firstIndex, int secondIndex){

        Collections.swap(list, firstIndex, secondIndex);
    }

    public Long CompareElements(T element){
        return list.stream().filter(e -> e.compareTo(element) > 0).count();
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (var item: list) {
            sb.append(String.format("%s: %s%n", item.getClass().getName(), item));
        }

        return sb.toString();
    }
}
