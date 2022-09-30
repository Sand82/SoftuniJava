import java.util.ArrayList;
import java.util.List;

public class Car { //model, engine, cargo,
    private String model;
    private Engine engine;
    private Cargo cargo;

    private List<Tire> tires = new ArrayList<>();

    public Car(String model, Engine engine, Cargo cargo ,List<Tire> tires) {
        this.model = model;
        this.engine = engine;
        this.cargo = cargo;
        this.tires.addAll(tires);
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public List<Tire> getTires() {
        return tires;
    }

    public void setTires(List<Tire> tires) {
        this.tires = tires;
    }
}
