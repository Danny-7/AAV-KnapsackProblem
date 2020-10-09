package project;

public class App {

	public static void main(String[] args) {
		
		BagPack bag = new BagPack("items.txt", 4);
		bag.resolve(ALGORITHM.PSE);
		System.out.println(bag.toString());

	}

}
