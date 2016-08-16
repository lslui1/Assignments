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
		int randomNumber2 = 0;
		int twoDieTotal = 0;
		
		// Random seed generator
		long seed = (new Date()).getTime();
		Random rnd = new Random(seed);
		
		// Generate initial random roll
		randomNumber = rnd.nextInt(upperBound - lowerBound) + lowerBound;
		randomNumber2 = rnd.nextInt(upperBound - lowerBound) + lowerBound;
		twoDieTotal = randomNumber + randomNumber2;
		
		//System.out.println("Dice roll number:                     " + numOfRolls);
		//System.out.printf("1st single die total is: %d\n", randomNumber);
		//System.out.printf("2nd single die is: %d\n", randomNumber2);
		//System.out.printf("The 2 die total is  %d\n", twoDieTotal);
	
		// Loop until the dice roll = 7
		while(twoDieTotal != 7) {
			totalRndNum = totalRndNum + twoDieTotal;
			numOfRolls += 1;
			randomNumber = rnd.nextInt(upperBound - lowerBound) + lowerBound;
			randomNumber2 = rnd.nextInt(upperBound - lowerBound) + lowerBound;
			twoDieTotal = randomNumber + randomNumber2;
			//System.out.println("Dice roll number:                     " + numOfRolls);
			//System.out.println("The 2 die total is: " + twoDieTotal);
		}
		
		// Print final statistics
		System.out.println("Total Number: " + totalRndNum);
		System.out.printf("The dice was rolled %d times\n", numOfRolls);

	}

}
