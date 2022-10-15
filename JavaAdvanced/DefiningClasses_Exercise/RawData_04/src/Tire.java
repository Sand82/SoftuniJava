public class Tire {

    private int age;
    private double Pressure;

    public Tire(int age, double pressure) {
        this.age = age;
        Pressure = pressure;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getPressure() {
        return Pressure;
    }

    public void setPressure(double pressure) {
        Pressure = pressure;
    }
}
