package ssa;


public class Transaction {

    private static int transCounter = 1;
    private int transId;
    private int accountId;
    private String acctType;
    private String transType;
    private double transAmt;
	
    // returns unique transaction Id
    public static synchronized int createTransID()
    {
    	return transCounter++;
    }
    
    // default constructor
    public Transaction() {
    	this.transId = createTransID();
    }
    
    // constructor adding all relevant transaction data
    public Transaction(int aId, String aType, String tType, double tAmt) {
    	this.transId = createTransID();
    	this.accountId = aId;
    	this.acctType = aType;
    	this.transType = tType;
    	this.transAmt = tAmt;
    }
    
    // add transaction data to existing transaction instance
    public void addTrans(int aId, String aType, String tType, double tAmt) {
    	this.accountId = aId;
    	this.acctType = aType;
    	this.transType = tType;
    	this.transAmt = tAmt;
    	
    }
    
    public int getTransactionId() {
    	return this.transId;
    }
    
    public int getAcctId() {	
    	return this.accountId;
    }
    
    public String getAcctType() {
    	return this.acctType;
    }
    
    public double getTransAmt() {
    	return this.transAmt;
    }
    
    public String getTransType() {
    	return this.transType;
    }
    
    // Display transaction data
    public void displayTrans() {
    	System.out.println("Transaction ID   : " + this.transId);
    	System.out.println("Account ID       : " + this.accountId);
    	System.out.println("Account Type     : " + this.acctType);
    	System.out.println("Transaction Type : " + this.transType);
    	System.out.printf("Transaction Amt  : %.2f\n", this.transAmt);
    }
}
