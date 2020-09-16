package algorithms;

import project.BagObject;
import project.BagPack;

import java.util.ArrayList;
import java.util.List;

public class Dynamic {
    private BagPack bag;
    private List<ArrayList<Double>> matrix;

    public Dynamic(){
        matrix = new ArrayList<ArrayList<Double>>();
        for(int i = 0; i< bag.getSizeOfList(); ++i){
            matrix.add(new ArrayList<>());
            for(double j = 0; j< bag.getMaxWeight(); ++i){
                matrix.get(i).add(0.0);
            }
        }
    }
    
    public void fillMatrix() {
        BagObject temp;
        for(int i = 0; i< matrix.size(); ++i){
            for(int j = 0; j < bag.getMaxWeight(); ++j){
                temp = bag.getList().get(i);
                if(i == 0){
                    if(temp.getWeight() > j)
                        matrix.get(0).add(0.0);
                    else
                        matrix.get(0).add(temp.getWeight());
                }
                else {
                    if(temp.getWeight() > j){
                        matrix.get(i).add(matrix.get(i-1).get(j));
                    }
                    else {
                        matrix.get(i).add(Math.max(matrix.get(i-1).get(j),
                                matrix.get(i-1).get(((int)(j - temp.getWeight())))
                                        + temp.getValue()));
                    }
                }
            }
        }
    }
}
