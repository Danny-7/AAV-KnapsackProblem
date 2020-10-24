package project;

import algorithms.utils.ALGORITHM;

import java.util.Scanner;

public class App {
	public static final int NB_ARGS = 3;

	public static void main(String[] args) {
		System.out.println("Follow this pattern -> file  max weight  method (greedy, dynamic or pse) ");
		Scanner sc = new Scanner(System.in);
		String[] arguments = sc.nextLine().trim().split(" ");
		if(checkCommand(arguments)){
			ALGORITHM choice = ALGORITHM.valueOf(arguments[2].toUpperCase());
			BagPack bag = new BagPack(arguments[0], Double.parseDouble(arguments[1]));
			long startTime = System.currentTimeMillis();
			bag.resolve(choice);
			long endTime = System.currentTimeMillis();
			System.out.println(bag.toString());
			System.out.println("That took " + (endTime - startTime) + "ms");
		}
		else
			System.err.println("Please follow the pattern re-launch");

	}

	/**
	 * Check if the command is valid
	 * @param arguments
	 * @return command is valid
	 */
	private static boolean checkCommand(String[] arguments){
		if(arguments.length != NB_ARGS)
			return false;
		boolean file = arguments[0].endsWith(".txt");
		boolean weight = arguments[1].chars().allMatch(Character:: isDigit);
		boolean algorithm = ALGORITHM.contains(arguments[2]);

		if(!file || !weight || !algorithm)
			return false;
		else
			return true;
	}

}
