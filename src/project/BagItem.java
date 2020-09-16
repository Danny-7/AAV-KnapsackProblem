package project;

public class BagItem {
    private double weight;
    private double value;
    private boolean taked;

    public BagItem(double weight, double value){
        this.weight = weight;
        this.value = value;
        this.taked = false;
    }

    public double getWeight() {
        return weight;
    }
    public double getValue() { return value; }

    public String toString() {
        return "Value:" + this.value +"\n" + "Weight: "+ this.weight;
    }

}
