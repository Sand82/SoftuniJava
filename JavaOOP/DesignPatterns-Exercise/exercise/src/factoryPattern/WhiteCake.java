package factoryPattern;

public class WhiteCake extends Cake {

    public WhiteCake(double diameter, double price, int pieces) {
        super(diameter, price, pieces);
    }

    @Override
    public void prepare() {
        System.out.println("Preparing products.");
    }

    @Override
    public void bake() {
        System.out.println("Bake white cake.");
    }

    @Override
    public void box() {
        System.out.println("Box a cake.");
    }
}
