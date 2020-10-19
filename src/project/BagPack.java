package project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.*;

import algorithms.BnB;
import algorithms.Dynamic;
import algorithms.Greedy;
import algorithms.utils.ALGORITHM;

public class BagPack {
	private double maxWeight;
	private List<BagObject> items;

	public BagPack(){
		this.maxWeight = 0;
	}
	public BagPack(String path, double maxWeight) {
		this.items = new LinkedList<>();
		readObjects(path);
		this.maxWeight = maxWeight;
	}

	public double getMaxWeight() {
		return this.maxWeight;
	}
	public int getSizeOfList() { return this.items.size();}
	public List<BagObject> getList(){
		return new ArrayList<>(items) ;
	}
	public ListIterator<BagObject> getIterator(){
		return items.listIterator();
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
	private void readObjects(String path){
		try {
			Scanner file = new Scanner(new FileInputStream(path));
			while(file.hasNextLine()){
				String[] line = file.nextLine().split(";");
				items.add(new BagObject(line[0],Double.parseDouble(line[1]), Double.parseDouble(line[2])));
			}
		}catch(FileNotFoundException fs){
			System.out.println(fs.getMessage());
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
				items = greedy.solution(getIterator());
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
		s.append("\nBag weight: ").append(new DecimalFormat("#.00").format(getBagWeight()));
		s.append("\nBag value: ").append(new DecimalFormat("#.00").format(getBagValue()));
		return s.toString();
	}
	

}
