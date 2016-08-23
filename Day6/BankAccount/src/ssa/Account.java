package ssa;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class Account
{
    private static int idCounter = 1;
    private static Map<Integer, Transaction> transMap = new HashMap<>(); // transaction records
    private int accountId;
	private String description = null;
    private String acctType;
    private String[] type = {"checkings", "savings"};
    private double balance;    
    NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
    
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
    
    // Resync the idCounter index to maintain unique Id's for bank accounts
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
    		System.out.println(aId + "is an invalid account Id. Please try again");
    	}
    }  
    
    // return account Id
    public int getId() {
    	return this.accountId;
    }
    
    // set the description. Parses the description and also tries to set the account type.
    public void setDescription(String aDescription) {
    	this.description = aDescription;
    	
    	if (aDescription.toLowerCase().contains("checking")) {
    		this.setAccountType(0);
    	} else {
    			if (aDescription.toLowerCase().contains("saving")) {
    				this.setAccountType(1);
    			}
    	}
    }

    // returns description
    public String getDescription() {
    	return this.description;
    }
    
    // returns the balance
    public double getBalance() {   	
    	return this.balance;
    }
    
    // sets the account type
    public void setAccountType(int aType) {
    	if (aType == 0 || aType == 1) {
        	this.acctType = this.type[aType];
    	} else {
    		System.out.println("Invalid account type. Please select 0 for checking or 1 for savings");
    	}
    }
    
    // returns account type
    public String getAccountType() {
    	return this.acctType;
    }

    // deposits and returns the balance
    public double deposit(double depositAmt) {
    	
    	this.balance += depositAmt;
    	generateTrans("Deposit", depositAmt);
    	return getBalance();
    }
    
    // withdraws and returns the balance
    public double withdraw(double withdrawAmt) {
		    	
    	if (this.balance >= withdrawAmt) {
    		this.balance -= withdrawAmt;
    		generateTrans("Withdrawal", withdrawAmt);
		} 	else {
			generateTrans("Invalid withdrawal", withdrawAmt);
			System.out.println("Invalid transaction. Reason: Insufficient Funds.");
			}			
    	return getBalance();
	}

    // Display account status
    public String displayAcct() {
    	String fullDesc = null;
    	
    	String headerString = "                              ----------------Account Status-------------\n";
    	String idString = "                              Account ID: " + Integer.toString(this.accountId) + "\n";
    	String typeString = "                              Account Type: " + this.acctType + " - Description: " + description + "\n";
    	String balanceString = "                              Account balance: " + String.format("%.2f", this.getBalance()) + "\n";
    	String footerString = "                              -------------------------------------------\n";
    	
    	fullDesc = headerString + idString + typeString + balanceString + footerString;
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
    		generateTrans("Invalid transfer transaction", transferAmt);
    		System.out.println("Invalid transaction. Reason: Insufficient Funds to transfer.");
    		}
    }
    
    // Generate transaction data instance and store in transaction Map
    public void generateTrans(String transType, double amt) { 	
    	Transaction trans = new Transaction();
    	trans.addTrans(accountId, acctType, transType, amt);
        transMap.put(trans.getTransactionId(), trans);
        // trans.displayTrans();
    }
    
    // Display all transaction data for this account
    public void displayTrans() {
    	System.out.println("-------------------------------------------------------------");
    	System.out.println("        Transaction data for account Id: " + accountId);
		System.out.println("-------------------------------------------------------------");
    	for (Entry<Integer, Transaction> entry : transMap.entrySet()) {
    		//System.out.println("Transaction ID : " + entry.getKey() + " | Data : " + entry.getValue());    		
    		if ( (entry.getValue()).getAcctId() == this.accountId ) {
    			(entry.getValue()).displayTrans();
    		}
    	}
    }
    	
    // return short balance data
    public String print() {
    	// return ("Account " + this.getId() + " balance is $" + String.format("%.2f", this.getBalance())) ;
    	return ("Account " + this.getId() + " balance is " + nf.format(this.getBalance()));
    }

}