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
	 * Sort a list using quickSort implementation
	 * @return list sorted by descending
	 */
	public  List<BagObject> sortedByDescending(){
		List<BagObject> listItems = bag.getList();

		Util util = new Util();
		util.quickSort(listItems, 0, listItems.size()-1);

		return listItems;
	}

	/**
	 * Find the solution for knapsack using greedy approach
	 * @param items items to evaluate
	 * @return new list with the right items
	 */
	public List<BagObject> solution(List<BagObject> items){
		// create a new list with the best ratio
		List<BagObject> finalList = new ArrayList<>();
		for(BagObject o: items){
			if(getCurrentWeight(finalList) + o.getWeight() <= bag.getMaxWeight())
				finalList.add(o);
		}
		return finalList;
	}

	/**
	 * Return the current weight of the solution
	 * @param items list of objects
	 * @return current weight
	 */
	public double getCurrentWeight(List<BagObject> items){
		double w = 0;
		for(BagObject o: items){
			if(o != null && o.getWeight() != 0.0)
				w+= o.getWeight();
		}
		return w;
	}
}
