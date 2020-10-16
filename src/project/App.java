package project;

import algorithms.utils.ALGORITHM;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		System.out.println("Follow this pattern -> file; max weight; method (greedy, dynamic or pse) ");
		Scanner sc = new Scanner(System.in);
		String[] arguments = sc.nextLine().trim().split(";");
		ALGORITHM choice = ALGORITHM.valueOf(arguments[2].toUpperCase());
		BagPack bag = new BagPack(arguments[0], Double.parseDouble(arguments[1]));
		long startTime = System.currentTimeMillis();
		bag.resolve(choice);
		long endTime = System.currentTimeMillis();
		System.out.println(bag.toString());
		System.out.println("That took " + (endTime - startTime) + "ms");


	}

}
