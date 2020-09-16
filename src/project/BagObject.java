package project;

import java.util.Comparator;

public class BagObject extends BagItem implements Comparator<BagObject>{
	private double ratio;
	
	public BagObject(double weight, double value) {
		super(weight, value);
		this.ratio = (super.getValue()/super.getWeight());
	}

	public double getRatio() { return this.ratio; }

	// methods which will be useFull for dynamic programming method
	public int compare(BagObject o, BagObject o1) {
		return Double.compare(o1.getRatio(), o.getRatio());
	}
}
