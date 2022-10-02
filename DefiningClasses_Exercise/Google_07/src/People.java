package Google_07.src;

import java.util.ArrayList;
import java.util.List;

public class People {

    private String name;

    private Company company;

    private Car car;

    private List<Parent> parents = new ArrayList<>();

    private List<Children> childrens = new ArrayList<>();

    private List<Pokemon> pokemons = new ArrayList<>();

    public People(String name) {
        this.name = name;
    }
}
