package algorithms;

import project.BagObject;
import project.BagPack;

import java.util.ArrayList;
import java.util.List;

public class Dynamic {
    private BagPack bag;
    private List<BagObject> items = new ArrayList<>();

    public Dynamic(BagPack bag){
        this.bag = bag;
    }

    public List<BagObject> resolve() {
        List<BagObject> items = new ArrayList<>();
        final int NB_OBJECTS = bag.getSizeOfList();
        final int MAX_WEIGHT = (int) bag.getMaxWeight();

        double [][] matrix = new double[NB_OBJECTS+1][MAX_WEIGHT+1];
        BagObject item;

        for(int i = 0; i <= MAX_WEIGHT; ++i)
            matrix[0][i] = 0.0;

        for(int i = 1; i <= NB_OBJECTS; ++i){
            for(int j = 0; j <= MAX_WEIGHT; ++j){
                //get the current object
                item = bag.getList().get(i-1);
                // weight of current object upper than the current column index we take the previous value
                if (item.getWeight() > j)
                    matrix[i][j] = matrix[i-1][j];
                else
                    matrix[i][j] = Math.max(matrix[i-1][j],
                            item.getValue() + matrix[i-1][j - (int) item.getWeight()]);
            }
        }

        int i = NB_OBJECTS;
        int j = MAX_WEIGHT;

        // find the minimal weight
        while(matrix[i][j] == matrix[i][j-1])
            --j;

        while(j > 0) {
            // find the item to add in the list
            // if we do a different value take this item
            while(i > 0 && matrix[i][j] == matrix[i - 1][j])
                --i;
            item = bag.getList().get(i-1);
            j-= (int) item.getWeight(); // removing the weight of the current object
            if (j > 0) {
                items.add(item);
            }
            --i;
        }
        return items;
    }
}
