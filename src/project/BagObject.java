package project;

public class BagObject implements Comparable<BagObject>{
    private String name;
    private double weight;
    private double value;

    public BagObject(){
        this.name = "";
        this.weight = 0;
        this.value = 0;
    }

    public BagObject(String name, double weight, double value){
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    public double getWeight() {
        return this.weight;
    }
    public double getValue() { return this.value; }

    public String toString() {
        return this.name + "-> " +"Value: " + this.value +" - " + "Weight: "+ this.weight;
    }

    @Override
    public int compareTo(BagObject o) {
        return Double.compare(this.getValue() /this.getWeight(), o.getValue()/o.getWeight());
    }
}
