package CustomList_07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class CustomList<T extends Comparable<T>> {
    private List<T> elements;

    public CustomList() {
        this.elements = new ArrayList<>();
    }

    public void add(T element) {
        elements.add(element);
    }

    public T remove(int index) {

        if (elements.size() < index ) {
            throw new NoSuchElementException(String.format("Element in index %s not exist", index));
        }
        return elements.remove(index);
    }

    public boolean contains(T element) {

        if (!elements.contains(element)) {
            return false;
        }

        return true;
    }

    public void swap(int firstIndex, int secondIndex){

        if(elements.size() < firstIndex || elements.size() < secondIndex){
            throw new NoSuchElementException("One of the Elements not exist");
        }

        Collections.swap(elements, firstIndex, secondIndex);
    }

    public int countGreaterThan(T element){
        return (int)elements.stream().filter(x -> x.compareTo(element) > 0).count();
    }

    public T getMax(){
        return Collections.max(elements, (x, y) -> x.compareTo(y));
    }

    public T getMin(){
        return Collections.min(elements, (x, y) -> x.compareTo(y));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (var element: elements) {
            sb.append(String.format("%s%n",element));
        }

        return  sb.toString();
    }

    public List<T> getElements() {
        return elements;
    }
}
