package algorithms;

import java.util.*;

import algorithms.utils.Util;
import project.BagObject;
import project.BagPack;

public class Greedy {
	
	private BagPack bag;

	public Greedy(BagPack bag) {
		this.bag = bag;
	}

	/**
	 * Sort a list uisng quickSort implementation
	 * @return list sorted by descending
	 */
	public  List<BagObject> sortedByDescending(){
		List<BagObject> listItems = bag.getList();
//		listItems.sort((BagObject o1, BagObject o2) ->
//						Double.compare(o2.getValue() /o2.getWeight(), o1.getValue()/o1.getWeight()));
		Util util = new Util();
		util.quickSort(listItems, 0, listItems.size()-1);

		return listItems;
	}

	/**
	 * Find the solution for knapsack using greedy approach
	 * @param items items to evaluate
	 * @return new list with the right items
	 */
	public List<BagObject> solution(ListIterator<BagObject> items){
		// create a new list with the best ratio
		List<BagObject> finalList = new ArrayList<>();
		double currentWeight = 0;
		while(currentWeight < bag.getMaxWeight() && items.hasNext()) {
			BagObject o = items.next();
			currentWeight+= o.getWeight();
			if(currentWeight <= bag.getMaxWeight())
				finalList.add(o);
		}
		return finalList;
	}
}
