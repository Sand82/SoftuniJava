package strategyPattern;

import java.util.Collections;
import java.util.Comparator;

public class LastNameSorter implements Comparator<Person> {

    @Override
    public int compare(Person leftPerson, Person rightPerson) {
        return leftPerson.getLastName().compareTo(rightPerson.getLastName());
    }

    public  Comparator<Person> reversed() {
        return Collections.reverseOrder(this);
    }
}
