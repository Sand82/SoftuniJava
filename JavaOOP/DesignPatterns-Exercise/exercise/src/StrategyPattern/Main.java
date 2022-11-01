package StrategyPattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Person> persons = new ArrayList<>();

        Person perso1 = new Person("Sand","Stef");
        Person perso2 = new Person("Mich","Stef");
        Person perso3 = new Person("Lub","Stef");
        Person perso4 = new Person("Mim","Vel");

        persons.add(perso1);
        persons.add(perso2);
        persons.add(perso3);
        persons.add(perso4);

        Collections.sort(persons, new FirstNameSorter());
        Collections.sort(persons, new LastNameSorter().reversed());
    }
}
