package factoryPattern;

public class ChocolateCake extends Cake {

    public ChocolateCake(double diameter, double price, int pieces) {
        super(diameter, price, pieces);
    }

    @Override
    public void prepare() {
        System.out.println("Preparing products.");
    }

    @Override
    public void bake() {
        System.out.println("Bake chocolate cake.");
    }

    @Override
    public void box() {
        System.out.println("Box a cake.");
    }
}
