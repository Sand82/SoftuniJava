package StrategyPattern;

import java.util.Comparator;

public class FirstNameSorter implements Comparator<Person> {
    @Override
    public int compare(Person leftPerson, Person rightPerson) {
        return leftPerson.getFirstName().compareTo(rightPerson.getFirstName());
    }
}
