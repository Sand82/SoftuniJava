package factoryPattern;

public class SpanishCake extends Cake {

    public SpanishCake(double diameter, double price, int pieces) {
        super(diameter, price, pieces);
    }

    @Override
    public void prepare() {
        System.out.println("Preparing products.");
    }

    @Override
    public void bake() {
        System.out.println("Baking spanish cake.");
    }

    @Override
    public void box() {
        System.out.println("Box a cake.");
    }
}
