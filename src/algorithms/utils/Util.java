package algorithms.utils;

import project.BagObject;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public void mergeSorting(List<BagObject> items, int start, int end){
        if(start != end){
            int middle = pivotChoice(start, end);
            mergeSorting(items, start, middle);
            mergeSorting(items,middle+1, end);
            merging(items, start, middle, end);
        }
    }

//    private void merging(List<BagObject> items, int start, int middle, int end){
//        int secondStart = end+1;
//
//        //on recopie les éléments du début du tableau
//        int table1[]=new int[fin1-deb1+1];
//        for(int i=deb1;i<=fin1;i++)
//        {
//            table1[i-deb1]=tableau[i];
//        }
//
//        int indexFirstList = start;
//        int indexOfStartSecList = secondStart;
//
//        int i = start;
//        while(i < end && indexFirstList != secondStart){
//            if (indexFirstList==secondStart) //c'est que tous les éléments du premier tableau ont été utilisés
//            {
//                break; //tous les éléments ont donc été classés
//            }
//            else if (indexOfStartSecList==(end+1)) //c'est que tous les éléments du second tableau ont été utilisés
//            {
//                tableau[i]=table1[compt1-deb1]; //on ajoute les éléments restants du premier tableau
//                compt1++;
//            }
//            else if (table1[compt1-deb1]<tableau[compt2])
//            {
//                tableau[i]=table1[compt1-deb1]; //on ajoute un élément du premier tableau
//                compt1++;
//            }
//            else
//            {
//                tableau[i]=tableau[compt2]; //on ajoute un élément du second tableau
//                compt2++;
//            }
//        }
//    }

    public List<BagObject> quickSort(List<BagObject> items, int first, int last) {
        if(first < last){
            Result result;
            int pivot = pivotChoice(first, last);
            result = repartition(items, first, last, pivot);
            // left side
            quickSort(result.getItems(), first, result.getPivot() -1);
            //right side
            quickSort(result.getItems(), result.getPivot() +1, last);
        }
        return items;
    }

    private int pivotChoice(int first, int last){
        return (first + last) /2;
    }

    private Result repartition(List<BagObject> items, int first, int last, int pivot){
        // get pivot and list of items
        Result result = new Result();
        // swap the last and pivot
        items = swap(items, last, pivot);

        int f = first;
        for(int i = first; i< last; ++i){
            if(items.get(i).compareTo(items.get(last)) > 0){
                swap(items, f, i);
                ++f;
            }
        }
        items = swap(items, f, last);
        // put the temp list on the result
        result.setItems(items);
        result.setPivot(f);
        return result;
    }

    // allow to swap betwwen the last and pivot of a list
    private List<BagObject> swap(List<BagObject> items, int last, int pivot){
        BagObject obj = items.get(pivot);
        items.set(pivot,items.get(last));
        items.set(last, obj);

        return items;
    }

    class Result {
        private List<BagObject> items;
        private int pivot;

        public Result() {
            items = new ArrayList<>();
        }

        public List<BagObject> getItems() {
            return items;
        }

        public void setItems(List<BagObject> items) {
            this.items = items;
        }

        public int getPivot() {
            return pivot;
        }

        public void setPivot(int pivot) {
            this.pivot = pivot;
        }
    }
}
