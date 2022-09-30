public class Cargo {

    private int weight;
    private String type;

    public Cargo(int weight, String type){
        this.weight = weight;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
