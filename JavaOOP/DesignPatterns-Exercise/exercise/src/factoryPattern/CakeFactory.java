package factoryPattern;

public class CakeFactory {

    public static Cake createCake(String cakeType, double diameter, double price, int pieces) {

        Cake cake = null;

        switch (cakeType) {
            case "White":
                cake = new WhiteCake(diameter, price, pieces);
                break;
            case "Spanish":
                cake = new SpanishCake(diameter, price, pieces);
                break;
            case "Chocolate":
                cake = new ChocolateCake(diameter, price, pieces);
                break;
            case "Biscuit":
                cake = new BiscuitCake(diameter, price, pieces);
                break;
        }

        cake.prepare();
        cake.bake();
        cake.box();

        return cake;
    }

}
