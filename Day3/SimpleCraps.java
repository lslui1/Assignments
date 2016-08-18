package theIronYard;

import java.util.Date;
import java.util.Random;

public class SimpleCraps {
	
	public static void main(String[] args) {
		
		int randomNumber = 0;
		int randomNumber2 = 0;
		int upperBound = 6;
		int lowerBound = 1;
		int crapRoll = 0;
		int pointRoll = 0;
		int numOfWins = 0;
		int numOfLosses = 0;
		
		// Random seed generator
		long seed = (new Date()).getTime();
		Random rnd = new Random(seed);
		
		// Run the game 100 times
		for (int gameCount = 1; gameCount < 101; gameCount++)
		{
			System.out.printf("\n -------------------GAME NUMBER: %d----------------------\n\n", gameCount);
			
			// Generate craps roll
			randomNumber = rnd.nextInt(upperBound - lowerBound) + lowerBound;
			randomNumber2 = rnd.nextInt(upperBound - lowerBound) + lowerBound;
			crapRoll = randomNumber + randomNumber2;
			
			// Initial game conditions when the point is not set
			if (pointRoll == 0) {
				if (crapRoll == 7) {
					numOfWins++;
					System.out.println("No point set. You rolled " + crapRoll + ". Wins go up to " + numOfWins);
					pointRoll = 0;
				} 
				else {
					if ((crapRoll == 2) || (crapRoll == 3) || (crapRoll == 11)) {
						numOfLosses ++;
						System.out.println("No point is set. Crap roll is " + crapRoll + " . Losses go up to " + numOfLosses);
					}
					else {
						pointRoll = crapRoll;
						System.out.println("Since you rolled " + crapRoll + ", point is now set at " + pointRoll);
					}
				}
			}			
			
			// Game conditions when the point is set
			while (pointRoll != 0) {
				randomNumber = rnd.nextInt(upperBound - lowerBound) + lowerBound;
				randomNumber2 = rnd.nextInt(upperBound - lowerBound) + lowerBound;
				crapRoll = randomNumber + randomNumber2;
				System.out.println("Rolled a: " + crapRoll);
				
				if (crapRoll == 7) {
					numOfLosses++;
					System.out.println("Point is " + pointRoll + ". Crap roll is " + crapRoll + ". Losses go up to " + numOfLosses);
					pointRoll = 0;
				}
				
				if (crapRoll == pointRoll) {
					numOfWins++;
					System.out.println("Point is " + pointRoll + ". Crap roll is " + crapRoll + ". Wins go up to " + numOfWins);
					pointRoll = 0;
				}
			}
		}
		
		// Print final statistics
		System.out.println("-----------------------------------------------------------");
		System.out.println("Total number of wins: " + numOfWins);
		System.out.println("Total number of losses: " + numOfLosses);
	}

}

