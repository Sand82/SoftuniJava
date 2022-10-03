package JarOfT_01;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Jar<T> {
    private ArrayDeque<T> elements = new ArrayDeque<>();

    public void add(T element){
        elements.push(element);
    }

    public T remove(){

        if (elements.size() <= 0) {
            throw new NoSuchElementException("Collection is empty.");
        }

        return elements.pop();
    }
}
