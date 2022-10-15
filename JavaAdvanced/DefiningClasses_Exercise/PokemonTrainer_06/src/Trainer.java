import java.util.ArrayList;
import java.util.List;

public class Trainer {
        //name, a number of badges, and a collection of pokemon

    private String name;

    private int number = 0;

    private int badges = 0;

    private List<Pokemon> pokemons = new ArrayList<>();

    public Trainer(String name) {
        this.name = name;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public int getBadges() {
        return badges;
    }

    public void setBadges(int badges) {
        this.badges = badges;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public void addPokemon(Pokemon pokemon){
        pokemons.add(pokemon);
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("%s %d %d", this.getName(), this.getBadges(), this.getPokemons().size());
    }
}
