public class Car {
    //model, engine, weight, and color

    private String model;
    private Engine engine;
    private int weight;
    private String color;

    public Car(String model, Engine engine) {
        this.model = model;
        this.engine = engine;
    }

    public Car(String model, Engine engine, int weight) {
        this(model, engine);
        this.weight = weight;
    }

    public Car(String model, Engine engine, String color) {
        this(model, engine);
        this.color = color;
    }

    public Car(String model, Engine engine, int weight, String color) {
        this(model, engine);
        this.weight = weight;
        this.color = color;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(String.format(("%s:%n"), this.model));
        sb.append(String.format(("%s:%n"), this.engine.getModel()));
        sb.append(String.format(("Power: %d%n"), this.engine.getPower()));
        sb.append(String.format(("Displacement: %s%n"), this.engine.getDisplacement() == 0 ? "n/a" : String.valueOf(this.engine.getDisplacement())));
        sb.append(String.format(("Efficiency: %s%n"), this.engine.getEfficiency()== null ? "n/a" :this.engine.getEfficiency()));
        sb.append(String.format(("Weight: %s%n"), this.weight == 0 ? "n/a" : String.valueOf(this.weight)));
        sb.append(String.format(("Color: %s"), this.color == null ? "n/a" : this.color));

        return sb.toString();
    }
}
