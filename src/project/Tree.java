package project;

import java.util.LinkedList;
import java.util.List;

public class Tree {
    private Tree leftTree, rightTree;
    private List<BagObject> values;
    private int depth; // profondeur de l'arbre
    private double upperBound; // borne supérieure
    private double lowerBound; // borne inférieure best value
    public static List<BagObject> bestPath = new LinkedList<>();

    public Tree(List<BagObject> items, double maxWeight, int index){
        if(index == 0) {
            values = new LinkedList<>();
            this.lowerBound = 0.0;
        }
        if( index < items.size()){
            this.depth = index;
            // we had the current object to the list
            if(values != null)
                this.values.add(items.get(depth));
            // max profit with the list given
            calcUpperBound(items);
            // at the start the lowerBound is at 0
            calcLowerBound();

            if(index!= items.size()) {
                this.leftTree = new Tree(items, maxWeight, this.values, ++depth);
                // if the weight is respected and the value is upper than the last node
                if (this.getListWeight(values)<=maxWeight && this.upperBound>this.lowerBound) {
                    this.rightTree = new Tree(items, maxWeight, this.values, ++depth);
                }
            }
        }
    }

    public Tree(List<BagObject> items, double maxWeight, List<BagObject> values, int index){
        if( index < items.size()){
            this.depth = index;
            this.values = values;
            // we had the current object to the list
            values.add(items.get(depth));
            // max profit with the list given
            calcUpperBound(items);
            // at the start the lowerBound is at 0
            calcLowerBound();

            if(index!= items.size()) {
                this.leftTree = new Tree(items, maxWeight, this.values, ++depth);
                // if the weight is respected and the value is upper than the last node
                if (this.getListWeight(values)<=maxWeight && this.upperBound>this.lowerBound) {
                    this.rightTree = new Tree(items, maxWeight, this.values, ++depth);
                }
            }
        }
    }

    public double getUpperBound() {
        return this.upperBound;
    }

    public double getLowerBound() {
        return this.lowerBound;
    }

    public void setLowerBound(double lowerBound) {
        this.lowerBound = lowerBound;
    }

    public void setUpperBound(double upperBound) {
        this.upperBound = upperBound;
    }

    /**
     * Return the weight of the current node list
     * @param items current list
     * @return weight
     */
    public double getListWeight(List<BagObject> items){
        double weight = 0.0;
        for(BagObject o: items)
            weight+= o.getWeight();
        return weight;
    }

    /**
     * Calculate the the sum of values in the current list
     * @return sum of values
     */
    private double getSumListValues() {
       double value = 0.0;
       for(BagObject item :this.values)
           value += item.getValue();
       return value;
    }

    /**
     * If we have a combination with a better value update the lower bound value
     * It's the best value also
     */
    public void calcLowerBound(){
        double currentNodeValue = this.getSumListValues();
        if(currentNodeValue > this.lowerBound)
            this.lowerBound = currentNodeValue;
    }

    /**
     * Calculate the max value with the adding list in the current node
     * @param items items added
     */
    public void calcUpperBound(List<BagObject> items) {
        // value of the current node list
        double value = this.getSumListValues();
        for(int i = depth; i< items.size(); ++i) {
            value += items.get(i).getValue();
        }
        this.upperBound = value;
    }

    /**
     * Find the objects with the best solution to put in the bag
     * @return list of objects
     */
    public List<BagObject> pathToTheSolution(){
        if(this.getSumListValues() == this.lowerBound)
            bestPath = this.values;
        else {
            if(this.leftTree == null && this.rightTree == null)
                return bestPath;
            if (this.leftTree==null){
                this.rightTree.pathToTheSolution();
            }
            if (this.rightTree==null){
                this.leftTree.pathToTheSolution();
            }
            if (this.rightTree!=null && this.leftTree!=null){
                this.rightTree.pathToTheSolution();
                this.leftTree.pathToTheSolution();
            }
        }
        return bestPath;
    }

}
