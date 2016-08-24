package ssa;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;


public class Account
{
    private static int idCounter = 1;
    private int accountId;
	private String description = "";
    private double balance;    
    public NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US); // Use for US dollar format ex. nf.format(double)
    public DecimalFormat twoDForm = new DecimalFormat("#.##"); // Use for 2 decimal formats ex. twoDForm.format(double)
    
    // private method to set the balance
    private void setBalance() {
    	this.balance = 0;
    }
    
    // private method to set the account Id
    private void setId(int aId) {
    	this.accountId = aId;
    }
    
    // Automatically generate account ID starting from 1
    private static synchronized int createID()
    {
    	return idCounter++;
    }
    
    // Re-sync the idCounter index to maintain unique Id's for bank accounts
    private static synchronized void syncID(int currentId)
    {
    	idCounter = ++currentId;
    }
    
    // default constructor
    public Account()
    {
    	this.accountId = createID();
    	setBalance();
    }
    
    // constructor with description argument
    public Account(String aDescription)
    {
    	this.accountId = createID();
    	setBalance();
    	this.setDescription(aDescription);
    }
    
    // constructor with account Id and description arguments
    public Account(int aId, String aDescription)
    {
    	if ((aId > 0) && (aId > idCounter)) {
    		setId(aId);
        	setBalance();
        	this.setDescription(aDescription);
        	syncID(aId);
    	} else {
    		System.out.println("Error: <" + aId + "> is an invalid account Id.");
    	}
    }  
    
    // return account Id
    public int getId() {
    	return this.accountId;
    }
    
    // set the description. Parses the description and also tries to set the account type.
    public void setDescription(String aDescription) {
    	this.description = aDescription;
    }

    // returns description
    public String getDescription() {
    	return this.description;
    }
    
    // returns the balance
    public double getBalance() {   	
    	return this.balance;
    }

    // deposits and returns the balance
    public double deposit(double depositAmt) {
    	if (depositAmt > 0) {
    		this.balance += depositAmt;
    	}
    	else {
    		System.out.printf("Error: invalid transaction. Reason: Invalid deposit amount of %d.\n", depositAmt);
    	}
    	return getBalance();
    }
    
    // withdraws and returns the balance
    public double withdraw(double withdrawAmt) {
    	if ((this.getBalance() >= withdrawAmt) && (withdrawAmt > 0)){
    		this.balance -= withdrawAmt;
		} 	else {
				System.out.println("Error: Invalid transaction. Reason: Insufficient Funds.");
			}			
    	return getBalance();
	}

    // Display account status
    public String accountStatus() {
    	String fullDesc = null;
    	
    	String headerString = "----------------Account Status-------------\n";
    	String idString = "Account ID: " + Integer.toString(this.accountId) + "\n";
    	String typeString = "Description: " + description + "\n";
    	String balanceString = "Account balance: " + nf.format(this.getBalance()) + "\n";
    	
    	fullDesc = headerString + idString + typeString + balanceString;
    	return fullDesc;
    }

    // Transfer funds from argument account to current account
    public void transferFrom(Account remFromAcct, double transferAmt) {
    	
    	double number2 = remFromAcct.getBalance();
    	double number1 = transferAmt;
    	
    	if (number2 >= number1) {
    		remFromAcct.withdraw(transferAmt);
    		this.deposit(transferAmt);
    	} else {
    		System.out.println("Error: Transfer aborted. Reason: Insufficient Funds to transfer.");
    		}
    }
    	
    // return short balance data
    public String print() {
    	return ("Account " + this.getId() + " balance is " + nf.format(this.getBalance()));
    }

}