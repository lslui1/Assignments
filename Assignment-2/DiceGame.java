package theIronYard;

import java.util.Date;
import java.util.Random;

public class DiceGame {
	
	public static void main(String[] args) {
		
		int totalRndNum = 0;
		int randomNumber = 0;
		int numOfRolls = 1;
		int upperBound = 6;
		int lowerBound = 1;
		
		// Random seed generator
		long seed = (new Date()).getTime();
		Random rnd = new Random(seed);
		
		// Generate initial random roll
		randomNumber = rnd.nextInt(upperBound - lowerBound) + lowerBound;
		
		//System.out.println("Dice roll number: " + numOfRolls);
		//System.out.printf("%d\n", randomNumber);

		// Loop until the dice roll = 1
		while(randomNumber != 1) {
			totalRndNum = totalRndNum + randomNumber;
			numOfRolls += 1;
			randomNumber = rnd.nextInt(upperBound - lowerBound) + lowerBound;
			//System.out.println("Dice roll number: " + numOfRolls);
			//System.out.println(randomNumber);
		}
		
		// Print final statistics
		System.out.println("Total Number: " + totalRndNum);
		System.out.printf("The dice was rolled %d times\n", numOfRolls);

	}

}
