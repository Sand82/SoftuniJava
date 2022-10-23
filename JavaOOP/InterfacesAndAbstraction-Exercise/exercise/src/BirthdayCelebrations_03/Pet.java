package BirthdayCelebrations_03;

public class Pet implements Birthable{

    private String name;
    private String getBirthDate;

    public Pet(String name, String getBirthDate) {
        this.name = name;
        this.getBirthDate = getBirthDate;
    }

    @Override
    public String getBirthDate() {
        return this.getBirthDate;
    }

    public String getName() {
        return this.name;
    }
}
