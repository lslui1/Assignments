package ssa;


public class Account
{
    private static int idCounter = 100;
    private int accountId;
	@SuppressWarnings("unused")
	private String description;
    private String acctType;
    private String[] type = {"checkings", "savings"};
    private double balance = 0;    

    
    // Automatically generate account ID starting from 100
    public static synchronized int createID()
    {
        // return String.valueOf(idCounter++);
    	return idCounter++;
    }
    
    Account()
    {
    	accountId = createID();
    }

/*  Constructors for manually setting account IDs  
    Account() {}
    
    Account(int aId) {
    	accountId = aId;
    }
   
    public void setId(int aId) {
    	accountId = aId;
    }
*/
    
    public int getId() {
    	return accountId;
    }
    
    public void setDescription(String aDescription) {
    	description = aDescription;
    }

    public String getDescription(String aDescription) {
    	return aDescription;
    }
    
    public String getBalance() {   	
    	return String.format("%.2f", balance);

    }
    
    public void setAccountType(int aType) {
    	acctType = type[aType];
    }
    
    public String getAccountType() {
    	return acctType;
    }

    public String deposit(String depositAmt) {
    	
    	double number = Double.parseDouble(depositAmt);
    	
    	balance += number;
    	return getBalance();
    }
    
    public String withdraw(String withdrawAmt) {
		
    	double number = Double.parseDouble(withdrawAmt);
    	
    	if (balance >= number) {
    		balance -= number;
		} 	else {
			System.out.println("Invalid transaction. Reason: Insufficient Funds.\n");
			}			
    	return getBalance();
	}

    public String displayAcct() {
    	String fullDesc = null;
    	
    	String idString = " Account ID: " + Integer.toString(accountId) + "\n";
    	String typeString = " Account Type: " + acctType + " - Description: " + description + "\n";
    	String balanceString = " Account balance: " + this.getBalance() + "\n";
    	
    	fullDesc = idString + typeString + balanceString;
    	return fullDesc;
    }

    // Transfer funds from argument account to current account
    public void transfer(String transferAmt, Account remFromAcct) {
    	
    	double number2 = Double.parseDouble((remFromAcct.getBalance()));
    	double number1 = Double.parseDouble(transferAmt);
    	String tempBal;
    	
    	if (number2 >= number1) {
    		tempBal = remFromAcct.withdraw(transferAmt);
    		balance += number1;
    	} else {
    		System.out.println("Invalid transaction. Reason: Insufficient Funds to transfer.\n");
    		}
    }
}