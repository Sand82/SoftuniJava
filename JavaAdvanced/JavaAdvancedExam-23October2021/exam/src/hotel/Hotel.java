package hotel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Hotel {

    private String name;
    private int capacity;

    private  int count;
    private List<Person> roster;

    public Hotel(String name, int capacity) {

        this.name = name;
        this.capacity = capacity;
        this.roster = new ArrayList<>();
    }

    public void add(Person person) {

        if (roster.size() < capacity) {
            roster.add(person);
        }
    }

    public boolean  remove(String name) {

        Person person = getPerson(name);

        if (person == null) {
            return false;
        }

        roster.remove(person);
        return true;
    }

    public Person getPerson(String name) {
        return roster.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst().orElse(null);
    }

    public Person getPerson(String name, String hometown) {
        return roster.stream()
                .filter(p -> p.getName().equals(name) && p.getHometown().equals(hometown))
                .findFirst().orElse(null);
    }

    public int getCount() {
        return roster.size();
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("The people in the hotel %s are:%n", name));

        for (var person: roster) {
            sb.append(person);
            sb.append(System.lineSeparator());
        }

        return  sb.toString().trim();
    }
}
