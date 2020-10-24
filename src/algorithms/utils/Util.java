package algorithms.utils;

import project.BagObject;

import java.util.ArrayList;
import java.util.List;

public class Util {

    /**
     * Sort item on decreasing order
     * @param items items to sort
     * @param first the first index
     * @param last size of items list
     * @return sorted list
     */
    public List<BagObject> quickSort(List<BagObject> items, int first,
                                     int last) {
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

    private Result repartition(List<BagObject> items, int first,
                               int last, int pivot){
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

    // allow to swap between the last and pivot of a list
    private List<BagObject> swap(List<BagObject> items, int last,
                                 int pivot){
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
