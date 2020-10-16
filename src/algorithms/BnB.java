package algorithms;

import project.BagObject;
import project.BagPack;
import project.Tree;

import java.util.List;

public class BnB {
    private BagPack bag;

    public BnB(BagPack bag){
        this.bag = bag;
    }

    public  List<BagObject>  resolve(){
//        Tree tree = new Tree(this.bag.getList(),this.bag.getMaxWeight(),0);
//        return tree.pathToTheSolution();
        int length = this.bag.getList().size();
        BagObject[] tabObj = new BagObject[length];

        Tree tree = new Tree(bag.getList(), this.bag.getMaxWeight(), tabObj, 0);

        tree.pathToTheSolution();

        return Tree.getBestPath();
    }
}
