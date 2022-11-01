package proxyPattern;

public class LionProxy implements Animal {

    private Lion lion;

    public LionProxy() {
        this.lion = new Lion();
    }

    @Override
    public void speak() {
        System.out.println("Before speak method in parent.");
        lion.speak();
        System.out.println("After speak method in parent.");
    }
}
