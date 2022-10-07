package easterBasket;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Basket {

//    •	material: String
//•	capacity: int

    private String material;
    private int capacity;
    private List<Egg> data;


    public Basket(String material, int capacity) {
        this.material = material;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void addEgg(Egg egg) {
        if (data.size() < capacity) {
            data.add(egg);
        }
    }

    public boolean removeEgg(String color) {
        Egg egg = getEgg(color);

        if (egg == null) {
            return  false;
        }
        data.remove(egg);
        return  true;
    }

    public Egg getStrongestEgg() {
        return data.stream()
                .max(Comparator.comparingInt(e -> e.getStrength()))
                .stream()
                .findFirst()
                .orElse(null);
    }

    public Egg getEgg(String color) {
        return  data.stream().filter(e -> e.getColor()
                .equals(color))
                .findFirst()
                .orElse(null);
    }

    public int getCount() {
        return data.size();
    }

    public String report() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s basket contains:%n", material));

        for (var egg: data) {
            sb.append(egg);
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
