package algorithms.utils;

import project.BagObject;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Tree {
    private Tree leftTree, rightTree;
    private List<BagObject> values;
    private int depth; // profondeur de l'arbre
    private double upperBound; // borne supérieure
    private static double lowerBound; // borne inférieure best value
    private static List<BagObject> bestPath = new LinkedList<>();

    public Tree(List<BagObject> items, double maxWeight, BagObject[] tabObj, int index){
        if (index <= items.size()) {

            this.values = Arrays.stream(tabObj).collect(Collectors.toList());

            this.depth = index;
            this.calcUpperBound(items);
            this.calcLowerBound();

            if (depth != items.size()){
                this.leftTree = new Tree(items, maxWeight, tabObj, index+1);

                tabObj[index] = items.get(index);
                if (this.getListWeight(tabObj)<=maxWeight && this.upperBound>Tree.lowerBound){

                    this.rightTree = new Tree(items, maxWeight, tabObj, index+1);
                }
                tabObj[index] = null;
            }

        }
    }

    public static List<BagObject> getBestPath() {
        return bestPath;
    }

    public double getLowerBound() {
        return Tree.lowerBound;
    }

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
            value += it.next().getValue(); // ajout des valeurs des objets restants
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
     * Calculate the the sum of values in the current list
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
