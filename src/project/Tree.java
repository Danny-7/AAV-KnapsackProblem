package project;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    private Tree leftTree, rightTree;
    private double value;
    private int depth; // profondeur de l'arbre
    private double upperBound; // borne supérieure
    private double lowerBound; // borne inférieure
    private List<BagObject> bestPath; // solution finale
    
    public Tree() {}
    
    public Tree(double value) {
    	this();
    	this.value = value;
    }

    public Tree(List<BagObject> items, double maxWeight, int index){
        if( index < items.size())
            throw new IllegalArgumentException("La profondeur de l'arbre est trop grande");

        if(items.get(index).getValue() < this.value) {
        	this.leftTree = new Tree(items.get(index).getValue());
        	this.lowerBound = this.leftTree.getValue();
        }
        if(items.get(index).getValue() > this.value) {
        	this.rightTree = new Tree(items.get(index).getValue());
        	this.lowerBound = this.rightTree.getValue();
        }
        
        this.depth = ++index;
    }
    
    public double getValue() {
    	return this.value;
    }
    
    public double getUpperBound(List<BagObject> items) {
    	double value = this.value;
    	for(BagObject item: items) {
    		value += item.getValue();
    	}
    	return value; 
    }
}
