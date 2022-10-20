package StackOfStrings_05;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class StackOfStrings extends ArrayDeque<String> {
    private List<String> stack = new ArrayList<>();

    public void push(String item) {

        stack.add(item);
    }

    public String pop() {

        return stack.remove(stack.size() - 1);
    }

    public String peek() {

        return stack.get(stack.size() - 1);
    }
}
