package ssa;

import java.util.Date;
import java.util.Random;

public class SimulatedBowling {

	final static long seed = (new Date()).getTime();
	private static Random rnd = new Random(seed);
	
	final static int games = 3;
	final static int frames = 10;
	private int bowlingScores[][] = new int[games][frames];
	
	// get random bowling result 
	public int getPinsKnockedDown(int range) {		 
		  return rnd.nextInt(range); // zero to ten
		}
	
	// Default constructor
	public SimulatedBowling() {}
	
	// play the game
	public void bowl() {
		rollThemBalls();
		displayScores();
	}
	
	// populate scores with bowling results of 2 rolls
	private void rollThemBalls() {
		
		for (int idxGames = 0; idxGames < bowlingScores.length; idxGames++) {
		    for (int idxScores = 0; idxScores < bowlingScores[idxGames].length; idxScores++) {
		    	int firstRoll = 0;
				int secondRoll = 0;
		    	firstRoll = getPinsKnockedDown(11);
		    	if (firstRoll < 10) {secondRoll = getPinsKnockedDown(11-firstRoll);}
		    	bowlingScores[idxGames][idxScores] = firstRoll + secondRoll;
		    }
		}
	}

	// format scores for output
	public String formatScore(int aScore) {		
		if (aScore > 99)
			return String.format("%03d", aScore);
		else
			if (aScore < 10)
				return "  " + String.format("%01d", aScore);
			else
				return " " + String.format("%02d", aScore);
	}
	
	// display the bowlers results
	public void displayScores() {
		
		int total = 0;
		int subtotal = 0;
		
		System.out.println("Frames    1   2   3   4   5   6   7   8   9  10   Total");

		for (int idxGames = 0; idxGames < bowlingScores.length; idxGames++) {
			System.out.printf("Game %d  ", idxGames+1);
		    for (int idxScores = 0; idxScores < bowlingScores[idxGames].length; idxScores++) {
		    	System.out.printf("%s ", formatScore(bowlingScores[idxGames][idxScores]));
		    	subtotal += bowlingScores[idxGames][idxScores];
		    }
		    System.out.printf("    %s\n", formatScore(subtotal));
		    total += subtotal;
		    subtotal = 0;		    
		}
		System.out.printf("Total Series                                        %s\n\n", formatScore(total));	
	}
	
}
