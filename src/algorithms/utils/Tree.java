package algorithms.utils;

import project.BagObject;

import java.util.*;
import java.util.stream.Collectors;

public class Tree {
    private Tree leftTree, rightTree;
    private List<BagObject> values;
    private int depth; // profondeur de l'arbre
    private double upperBound; // borne supérieure
    private static double lowerBound; // borne inférieure (best value)
    private static List<BagObject> bestPath = new ArrayList<>();

    public Tree(){
        this.values = null;
        this.leftTree = null;
        this.rightTree = null;
        this.depth = 0;
        this.upperBound = 0;
    }

    /**
     * Build a tree on conditions ( UB & LB )
     * @param items items to add
     * @param maxWeight maw weight of the bag
     * @param currentItems the current items on the node
     * @param index how high are we
     */
    public Tree(List<BagObject> items, double maxWeight,
                BagObject[] currentItems, int index){
        this.values = Arrays.stream(currentItems).collect(Collectors.toList());

        this.depth = index;
        this.calcUpperBound(items);
        this.calcLowerBound();

        if (index != items.size()){
            this.leftTree = new Tree(items, maxWeight, currentItems, index+1);

            currentItems[index] = items.get(index);
            if (this.getListWeight(currentItems)<=maxWeight &&
                    this.upperBound>getLowerBound()){

                this.rightTree = new Tree(items, maxWeight,
                        currentItems, index+1);
            }
            currentItems[index] = null;
        }
    }

    public static List<BagObject> getBestPath() {
        return bestPath;
    }

    public double getLowerBound() {
        return Tree.lowerBound;
    }

    /**
     * Calculate the feasible solution on the node
     */
    public void calcLowerBound(){
        double currentNodeValue = this.getSumListValues();
        if(currentNodeValue > Tree.lowerBound)
            Tree.lowerBound = currentNodeValue;
    }

    /**
     * Calculate the max value with the adding list in the current node
     * @param items items added
     */
    public void calcUpperBound(List<BagObject> items) {
        // value of the current node list
        double value = this.getSumListValues();
        Iterator<BagObject> it = items.listIterator(depth);
        while(it.hasNext()){
            // adding of remaining objects
            value += it.next().getValue();
        }
        this.upperBound = value;
    }

    /**
     * Return the weight of the current node list
     * @param items current list
     * @return weight
     */
    public double getListWeight(BagObject[] items){
        double weight = 0.0;
        for(BagObject o: items)
            if(o != null)
                weight+= o.getWeight();
        return weight;
    }

    /**
     * Calculate  the sum of values in the current list
     * @return sum of values
     */
    private double getSumListValues() {
       double value = 0.0;
       for(BagObject item :this.values)
           if(item != null)
               value += item.getValue();
       return value;
    }


    /**
     * Find the objects with the best solution to put in the bag
     * @return list of objects
     */
    public void pathToTheSolution(){
        if(this.getSumListValues() == Tree.lowerBound)
            Tree.bestPath = this.values;
        else {
            if(this.leftTree == null && this.rightTree == null)
                return;
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
    }

}
