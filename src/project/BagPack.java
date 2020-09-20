package project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import algorithms.Dynamic;
import algorithms.Greedy;

public class BagPack {
	private double maxWeight;
	private List<BagObject> items;

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
		return new LinkedList<>(items) ;
	}
	public ListIterator<BagObject> getIterator(){
		return items.listIterator();
	}
	public double getBagWeight() {
		double w = 0;
		for(BagObject o: this.items){
			if(o != null && o.getWeight() != 0.0)
				w+= o.getWeight();
		}
		return w;
	}

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
			default:
		}

	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		ListIterator<BagObject> iterator = getIterator();

		while(iterator.hasNext()) {
			BagObject o = iterator.next();
			s.append(o.toString()).append(System.lineSeparator());
		}
		s.append("\nBag weight: ").append(getBagWeight());
		return s.toString();
	}
	

}
