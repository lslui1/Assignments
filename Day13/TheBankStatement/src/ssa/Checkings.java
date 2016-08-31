package ssa;

public class Checkings extends Account{

	private int checkNumber = 100;
	public final char accountType = 'C';
	
	public Checkings() {
		super();
	}

	public Checkings(String aDescription) {
		super(aDescription);

	}
	
	public Checkings(int aId, String aDescription) {
		super(aId, aDescription);
	}
	
	private void incrementCheck() {
		this.checkNumber++;
	}
	
	public int getCheckNumber() {
		return this.checkNumber;
	}
	
	public void setLastCheckNbr(int setNum) {
		if (setNum > 0) {
			this.checkNumber = setNum;
		}
	}
	
	public char getType() {
		return this.accountType;
	}
	
	public double withdraw(double withdrawAmt) {
		incrementCheck();
		return super.withdraw(withdrawAmt);
	}
	
	// return account information string
	public String print() {
		String stringCheck = String.format("%1$5s", Integer.toString(this.getCheckNumber()));
		return (accountType + " " + super.print() + " " + stringCheck);
	}
}
