package project;

public class BagObject {
    private String name;
    private double weight;
    private double value;

    public BagObject(String name, double weight, double value){
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    public double getWeight() {
        return weight;
    }
    public double getValue() { return value; }

    public String toString() {
        return "Value:" + this.value +"\n" + "Weight: "+ this.weight;
    }

}
