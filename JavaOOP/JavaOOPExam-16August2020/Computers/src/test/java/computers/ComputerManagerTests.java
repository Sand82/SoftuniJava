package computers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ComputerManagerTests {

    private ComputerManager manager;

    private Computer firstComputer;

    private Computer secondComputer;

    @Before
    public void setUp() {

        manager = new ComputerManager();

        firstComputer = new Computer("test1", "123", 1200.0);
        secondComputer = new Computer("test2", "234", 1300.0);
    }

    @Test
    public void getComputerShouldReturnCorrectValue(){

        manager.addComputer(firstComputer);
        manager.addComputer(secondComputer);

        List<Computer> computers = new ArrayList<>();
        computers.add(firstComputer);
        computers.add(secondComputer);

        Assert.assertEquals(manager.getComputers().size(), 2);
        for (int i = 0; i < computers.size(); i++) {
            Assert.assertEquals(manager.getComputers().get(i).getManufacturer(), computers.get(i).getManufacturer());
        }
    }

    @Test
    public void addComputerShouldInsertValueInCollection(){

        manager.addComputer(firstComputer);
        manager.addComputer(secondComputer);

        List<Computer> computers = new ArrayList<>();
        computers.add(firstComputer);
        computers.add(secondComputer);

        Assert.assertEquals(manager.getCount(), 2);
        for (int i = 0; i < computers.size(); i++) {
            Assert.assertEquals(manager.getComputers().get(i).getManufacturer(), computers.get(i).getManufacturer());
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void validateNullValueShouldThrowExceptionWhenValueIsNull(){

        manager.addComputer(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void validateNullValueShouldThrowExceptionWhenValueExistInArray(){

        manager.addComputer(firstComputer);
        manager.addComputer(secondComputer);

        manager.addComputer(firstComputer);
    }

    @Test
    public void removeComputerShouldReturnCorrectValue() {

        manager.addComputer(firstComputer);
        manager.addComputer(secondComputer);

        Assert.assertEquals(manager.removeComputer("test1", "123").getManufacturer(), "test1");
        Assert.assertEquals(manager.removeComputer("test2", "234").getModel(), "234");
        Assert.assertEquals(manager.getCount(), 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeComputerShouldThrowExceptionWhenTryToRemoveNotExistingObject() {

        manager.addComputer(firstComputer);
        manager.addComputer(secondComputer);

        manager.removeComputer("test3", "345");
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeComputerShouldThrowExceptionWhenManufacturerIsNullValue() {

        manager.addComputer(firstComputer);
        manager.addComputer(secondComputer);

        manager.removeComputer(null, "345");
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeComputerShouldThrowExceptionWhenModelIsNullValue() {

        manager.addComputer(firstComputer);
        manager.addComputer(secondComputer);

        manager.removeComputer("test1", null);
    }

    @Test
    public void getComputersByManufacturerShouldReturnCorrectCollection(){

        Computer thirdComputer = new Computer("test1", "456", 1400.0);

        manager.addComputer(firstComputer);
        manager.addComputer(secondComputer);
        manager.addComputer(thirdComputer);

        List<Computer> computers = new ArrayList<>();
        computers.add(firstComputer);
        computers.add(thirdComputer);

        Assert.assertEquals(manager.getComputersByManufacturer("test1").size(), 2);

        for (int i = 0; i < computers.size(); i++) {

            Assert.assertEquals(manager.getComputersByManufacturer("test1").get(i).getModel(), computers.get(i).getModel());
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void getComputersByManufacturerShouldThrowExceptionWhenModelIsNullValue() {

        manager.addComputer(firstComputer);
        manager.addComputer(secondComputer);

        manager.getComputersByManufacturer(null);
    }
}