package project;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    private Tree leftTree, rightTree;
    private List<BagObject> values;
    private int depth;
    private double upperBound;
    private double lowerBound;
    private List<BagObject> bestPath;

    public Tree(List<BagObject> items, double maxWeight, int index){
        if( index < items.size())
            throw new IllegalArgumentException("La profondeur de l'arbre est trop grande");

        this.values = new ArrayList<>();

        BagObject item;
        for(int i = 0; i< items.size(); ++i){
            item = items.get(i);
            if(item != null)
                this.values.add(item);
        }
        this.depth = index;
    }
}
