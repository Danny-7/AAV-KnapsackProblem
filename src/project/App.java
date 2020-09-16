package project;

public class App {

	public static void main(String[] args) {
		
		BagPack bag = new BagPack(6);
		
		bag.addObject(new BagObject(2.0,30.0));
		bag.addObject(new BagObject(1.0,20.0));
		bag.addObject(new BagObject(3.0,40.0));
		bag.addObject(new BagObject(0.2,50.0));
		bag.addObject(new BagObject(0.1,3.0));
		bag.addObject(new BagObject(3.0,100.0));
		bag.addObject(new BagObject(0.2,3.0));
		bag.addObject(new BagObject(0.2,2.0));

		bag.resolve();
		System.out.println(bag.toString());

	}

}
