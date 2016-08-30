package ssa;

public class Savings extends Account{
	
	private double interestRate;
	private double minimumBalance;
	private double interestPaid;
	
	public Savings() {
		this.setInterestRate(0);
		this.setMinimumBalance(0);
		this.interestPaid = 0;
	}

	public Savings(String aDescription) {
		super(aDescription);
		this.setInterestRate(0);
		this.setMinimumBalance(0);
		this.interestPaid = 0;
	}
	
	public Savings(int aId, String aDescription) {
		super(aId, aDescription);
		this.setInterestRate(0);
		this.setMinimumBalance(0);
		this.interestPaid = 0;
	}
	
	// Set minimum balance to accrue interest
	public void setMinimumBalance(double minBalance) {
		if (minBalance < 0) {
			System.out.println("Error: unable to set the minimum balance to accrue interest. Invalid MINIMUM BALANCE.");
		} else {
			this.minimumBalance = minBalance;
		}
	}
	
	// get minimum balance
	public double getMinimumBalance() {
		return this.minimumBalance;
	}
	
	// Set interest rate
	public void setInterestRate(double intRate) {
		if (intRate < 0) {
			System.out.println("Error: unable to set the interest rate. Invalid INTEREST RATE argument field.");
		} else {
			this.interestRate = intRate;
		}
	}
	
	// Set interest rate via String percentage in format "1.5%"
	public void setInterestRate(String intRate) {
		String strippedIntRate = intRate.replace("%", "");
		double doubleIntRate = Double.valueOf(strippedIntRate) / 100;
		setInterestRate(doubleIntRate);
	}
	
	// Get interest rate method
	public double getInterestRate() {
		return this.interestRate;
	}
	
	// get total interest paid
	public double getInterestPaid() {
		return this.interestPaid;
	}
	
	// Calculate the deposit interest via month argument and return the interest amount generated
	public double calcDepositInterest(int months) {
		double interestGen = 0;
		
		// Must meet minimum balance else error out
		if ((this.getBalance()) < (this.getMinimumBalance()))  {
			System.out.println("Error: Unable to calculate interest. Reason: Account does not meet minimum balance requirements.");
			return 0;
		}
		
		if (months > 0) {
			interestGen = ((this.getInterestRate() / 12) * months) * getBalance();
			deposit(interestGen);
			interestPaid += interestGen;
		}
		else {
			System.out.println("Error: unable to calculate deposit interest. Invalid MONTH argument field.");
		}		
		return Double.valueOf(twoDForm.format(interestGen));
	}
	
	// return account status
	public String accountStatus() {
		String intRateString = "Interest Rate: " + (this.getInterestRate()) + " | Interest Paid: " + nf.format(this.getInterestPaid()) + "\n";	
		return (super.accountStatus() + intRateString);
	}

	// return account information string
	public String print() {
		return (super.print() + " Amount of interest Paid is " + nf.format(this.getInterestPaid()));
	}
	
}