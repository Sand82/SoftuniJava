package StackOfStrings_05;

import java.util.ArrayList;
import java.util.List;

public class StackOfStrings {
    private List<String> stack = new ArrayList<>();

    public void push(String item) {
        stack.add(item);
    }

    public String pop() {
        return stack.remove(stack.size() - 1);
    }

    public String peek() {

        String result = stack.remove(stack.size() - 1);
        stack.add(result);

        return result;
    }
}
