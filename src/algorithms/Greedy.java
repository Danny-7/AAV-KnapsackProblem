package algorithms;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import algorithms.utils.Util;
import project.BagObject;
import project.BagPack;

public class Greedy {
	
	private BagPack bag;

	public Greedy(BagPack bag) {
		this.bag = bag;
	}

	public  List<BagObject> sortedByDescending(){
		List<BagObject> listItems = bag.getList();
//		listItems.sort((BagObject o1, BagObject o2) ->
//						Double.compare(o2.getValue() /o2.getWeight(), o1.getValue()/o1.getWeight()));
		Util util = new Util();
		util.quickSort(listItems, 0, listItems.size()-1);

		return listItems;
	}
	
	public List<BagObject> solution(ListIterator<BagObject> items){
		// créer une nouvelle liste avec les objets avec le meilleur ratio
		List<BagObject> finalList = new LinkedList<>();
		double i = 0;
		while(i < bag.getMaxWeight() && items.hasNext()) {
			BagObject o = items.next();
			i+= o.getWeight();
			if(i <= bag.getMaxWeight())
				finalList.add(o);
		}
		return finalList;
	}
}
