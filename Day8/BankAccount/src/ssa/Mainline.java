package ssa;

public class Mainline {

	public static void main(String[] args) {
		
		int months = 12;
		double interest1 = 0;
		
		Savings savings3 = new Savings("Savings 3");
		//savings3.setMinimumBalance(501);
		savings3.deposit(500);
		System.out.printf("Total for Savings after deposit is %.2f\n", savings3.getBalance());
		
		savings3.setInterestRate(0.015);
		//test string input method for interest rate
		//savings3.setInterestRate("1.5%");
		
		System.out.printf("Interest rate is %.4f\n", savings3.getInterestRate());
		interest1 = savings3.calcDepositInterest(months);
		System.out.printf("Interest calculated at %.2f for %d months\n", interest1, months);
		System.out.printf("Total for Savings after %d month calculation is %.2f\n", months, savings3.getBalance());
		
		System.out.println(savings3.print());
		System.out.println(savings3.accountStatus());
		
		months = 24;
		savings3.setInterestRate("3%");
		interest1 = savings3.calcDepositInterest(months);
		System.out.printf("Interest calculated at %.2f for %d months\n", interest1, months);
		System.out.printf("Total for Savings after %d month calculation is %.2f\n", months, savings3.getBalance());
		
		System.out.println(savings3.print());
		System.out.println(savings3.accountStatus());
	}

}
