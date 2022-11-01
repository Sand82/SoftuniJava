package observerPattern;

public class CarFan implements Observer{
    @Override
    public void update(String magazineName) {
        System.out.println("Going to buy " + magazineName);
    }
}
