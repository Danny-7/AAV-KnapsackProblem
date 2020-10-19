package algorithms;

import algorithms.utils.Tree;
import project.BagObject;
import project.BagPack;

import java.util.List;

public class BnB {
    private BagPack bag;

    public BnB(BagPack bag){
        this.bag = bag;
    }

    /**
     * Resolve the knapsack problem using branc and bound least and cost
     * @return best solution in a list
     */
    public  List<BagObject>  resolve(){
        int length = this.bag.getList().size();
        BagObject[] currentItems = new BagObject[length];
        Tree tree = new Tree(bag.getList(), this.bag.getMaxWeight(), currentItems, 0);

        tree.pathToTheSolution();

        return Tree.getBestPath();
    }
}
