package project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

import algorithms.BnB;
import algorithms.Dynamic;
import algorithms.Greedy;
import algorithms.utils.ALGORITHM;

public class BagPack {
	private double maxWeight;
	private List<BagObject> items;

	public BagPack(){
		this.maxWeight = 0;
		items = new LinkedList<>();
	}

	public BagPack(String path, double maxWeight) {
		List<BagObject> temp = readObjects(path);
		this.items = temp != null ? temp : new LinkedList<>();
		this.maxWeight = maxWeight;
	}

	public double getMaxWeight() {
		return this.maxWeight;
	}
	public int getSizeOfList() {
		return this.items.size();
	}
	public List<BagObject> getList(){
		return new ArrayList<>(items) ;
	}

	/**
	 * Calculate the bag weight
	 * @return bag weight
	 */
	public double getBagWeight() {
		double w = 0;
		for(BagObject o: this.items){
			if(o != null && o.getWeight() != 0.0)
				w+= o.getWeight();
		}
		return w;
	}

	/**
	 * Calculate the bag value
	 * @return bag value
	 */
	public double getBagValue(){
		double value = 0.0;
		for(BagObject o: this.items){
			if(o != null && o.getValue() != 0.0)
				value+= o.getValue();
		}
		return value;
	}

	/**
	 * Read a file of objects
	 * @param path file path
	 */
	private List<BagObject> readObjects(String path) {
	    try {
		   return Files.lines(Paths.get(path))
				.map(line -> line.split(";"))
				.filter(line -> (line.length == 3))
				.map(line -> new BagObject(line[0],
						Double.parseDouble(line[1]),
						Double.parseDouble(line[2]))
				)
				   .collect(Collectors.toList());

        }catch(IOException e){
            System.err.println(e.getMessage());
			return null;
        }
    }

	/**
	 * Resolve the knapsack problem using one of the methods under
	 * @param algo algorithm selected
	 */
	public void resolve(ALGORITHM algo) {
		switch (algo){
			case GREEDY:
				Greedy greedy = new Greedy(this);
				items = greedy.sortedByDescending();
				items = greedy.solution(items);
				break;
			case DYNAMIC:
				Dynamic dyn = new Dynamic(this);
				items = dyn.resolve();
				break;
			case PSE:
				BnB bnb = new BnB(this);
				items = bnb.resolve();
			default:
		}

	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();

		for(BagObject item: items){
			if(item != null){
				s.append(item.toString()).append(System.lineSeparator());
			}
		}
		s.append("\nBag weight: ")
				.append(new DecimalFormat("#.00").format(getBagWeight()));
		s.append("\nBag value: ")
				.append(new DecimalFormat("#.00").format(getBagValue()));
		return s.toString();
	}
	

}
