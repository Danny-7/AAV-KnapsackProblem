package project;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    private Tree leftTree, rightTree;
    private List<BagObject> values;
    private int depth; // profondeur de l'arbre
    private double upperBound; // borne supérieure
    private double lowerBound; // borne inférieure
    private double profit;
    private List<BagObject> bestPath; // solution finale
    
    public Tree() {}
    
    public Tree(List<BagObject> items) {
    	this();
    	this.values = new ArrayList<>();
    }

    public Tree(List<BagObject> items, double maxWeight, int index){
        if( index < items.size())
            throw new IllegalArgumentException("La profondeur de l'arbre est trop grande");

        if(items.get(index).getValue() < this.upperBound) {
        	List<BagObject> newValues = this.getSetOfValues();
        	// add the item to the list
        	newValues.add(items.get(index));
        	// the leftTree takes the list of his parent with the object added
        	this.leftTree = new Tree(newValues);
        	this.leftTree.setUpperBound(this.upperBound);
        	this.leftTree.setLowerBound(this.lowerBound);
        	// the right tree takes the same list of his parent
        	this.rightTree = new Tree(this.getSetOfValues());
        }
        if(items.get(index).getValue() > this.upperBound) {
        	this.rightTree = new Tree(this.getSetOfValues());
        	this.rightTree.setLowerBound(this.lowerBound);
        }
        
        this.depth = ++index;
    }
    
    
    // allows to get the values of a tree node
    public List<BagObject> getSetOfValues(){
    	return new ArrayList<BagObject>(values);
   }
    
    
   private double getBoundProfit() {
	   return this.profit += this.rightTree.getBoundProfit();
   }
	   
	   
   // allows to get the sum of all values in the node
   private double getSumListValues() {
	   if(this.values.size() == 0)
		   return 0.0;
	   double value = 0.0;
	   for(BagObject item :this.values) {
		   value += item.getValue();
	   }
	   
	   return value;
   }
    
    public double getUpperBound(List<BagObject> items) {
    	double value = this.getSumListValues();
    	for(int i = depth; i< items.size(); ++i) {
    		value += items.get(i).getValue();
    	}
    	return value; 
    }
    
    public void setLowerBound(double lowerBound) {
    	this.lowerBound = lowerBound;
    }
    
    public void setUpperBound(double upperBound) {
    	this.upperBound = upperBound;
    }
    
}
