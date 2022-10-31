package p02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {
    private Person[] persons = {new Person(1, "Mish"), new Person(2, "Lib")};
    private Database dataBase;

    @Before
    public void arrange() throws OperationNotSupportedException {

        dataBase = new Database(persons);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void returnExceptionWhenCreateDataBaseWhitEmptyArray() throws OperationNotSupportedException {

        Person[] persons = {};
        Database newDatabase = new Database(persons);

    }

    @Test(expected = OperationNotSupportedException.class)
    public void returnExceptionWhenCreateDataBaseWhit16Persons() throws OperationNotSupportedException {

        Person[] persons = new Person[17];

        for (int i = 0; i < 17; i++) {
            Person person = new Person(i, "Person" + i);
            persons[i] = person;
        }

        Database database = new Database(persons);
    }

    @Test
    public void successfullyAddPersonInDataBase() throws OperationNotSupportedException {
        Person person = new Person(3, "Sand");
        Person person1 = new Person(4, "Mim");

        Database database = new Database(persons);
        database.add(person);
        database.add(person1);

        Assert.assertEquals(database.getElements().length, 4);
    }

    @Test
    public void successfullyRemoveFromDataBase() throws OperationNotSupportedException {

        dataBase.remove();

        Person[] currPerson = dataBase.getElements();

        Assert.assertEquals(currPerson.length, persons.length - 1);
        Assert.assertEquals(currPerson[currPerson.length - 1].getUsername(), "Mish");

    }


    @Test(expected = OperationNotSupportedException.class)
    public void throwExceptionWhenRemoveFromEmptyDataBase() throws OperationNotSupportedException {

        Database database = new Database();
        database.remove();
    }

    @Test
    public void findByUsernameReturnCorrectValue() throws OperationNotSupportedException {

        Person person = dataBase.findByUsername("Mish");

        Assert.assertEquals(person.getUsername(), "Mish");
    }

    @Test(expected = OperationNotSupportedException.class)
    public void findByUsernameThrowExceptionWhenIsMoreThanOnePersonWhitThatUserName() throws OperationNotSupportedException {

        String theUserName = "Mish";

        Person person = new Person(3, theUserName);

        Database database = new Database(persons);

        database.add(person);
        database.findByUsername(theUserName);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void findByUsernameThrowExceptionWhenUserNameIsNull() throws OperationNotSupportedException {

        String theUserName = null;

        Database database = new Database(persons);
        database.findByUsername(theUserName);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void findByIdThrowExceptionWhenIsMoreThanOnePersonWhitTheSameId() throws OperationNotSupportedException {

        Person person = new Person(1, "Sand");

        Database database = new Database(persons);

        database.add(person);
        database.findById(1);
    }

    @Test
    public void findByIdReturnCorrectValue() throws OperationNotSupportedException {

        Person person = dataBase.findById(1);
        Assert.assertEquals(person.getUsername(), "Mish");
    }

}