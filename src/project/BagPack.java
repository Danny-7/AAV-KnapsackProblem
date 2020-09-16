package project;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import algorithms.Greedy;

public class BagPack {
	private double maxWeight;
	private List<BagObject> items;

	public BagPack(double maxWeight) {
		this.maxWeight = maxWeight;
		this.items = new LinkedList<>();
	}
	
	public void addObject(BagObject item) {
		this.items.add(item);
	}
	
	public double getMaxWeight() {
		return this.maxWeight;
	}
	public int getSizeOfList() { return this.items.size();}
	public List<BagObject> getList(){
		return items;
	}
	public ListIterator<BagObject> getIterator(){
		return items.listIterator();
	}
	
	public void resolve() {
		Greedy greedy = new Greedy(this);
		items = greedy.sortedByDescending();
		items = greedy.solution(getIterator());
	}
	
	public String toString() {
		double weight = 0;
		StringBuilder s = new StringBuilder("");
		ListIterator<BagObject> iterator = getIterator();

		while(iterator.hasNext()) {
			BagObject o = iterator.next();
			weight += o.getWeight();
			s.append(o.toString() + System.lineSeparator());
		}
		s.append("Weight of bag: "+weight);
		return s.toString();
	}
	

}
