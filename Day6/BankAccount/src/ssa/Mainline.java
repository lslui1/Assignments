package ssa;

public class Mainline {

	public static void main(String[] args) {

		Account checkings = new Account();
		Account savings = new Account();
		
		String checkBalance;
		String savingsBalance;
		
		//checkings.setId(100);       Account ID is automatically generated
		checkings.setAccountType(0);  // 0 represents checking account
		checkings.setDescription("My personal checking account");		
		// In real life, banks always set new accounts to 0 balance (as in constructor) and deposits when initially opened
		checkBalance = checkings.deposit("500.00");
		System.out.println(checkings.displayAcct());
		checkBalance = checkings.deposit("200.00");
		System.out.println(checkings.displayAcct());
		checkBalance = checkings.withdraw("600.00");
		System.out.println(checkings.displayAcct());
		checkBalance = checkings.deposit("100.00");
		checkBalance = checkings.withdraw("300.00");
		System.out.println(checkings.displayAcct());
		checkBalance = checkings.withdraw("200.00");
		System.out.println(checkings.displayAcct());
		
		System.out.println("------------------------------------------------------------");
		
		//savings.setId(200);         Account ID is automatically generated
		savings.setAccountType(1);  // 1 represents checking account
		savings.setDescription("My personal savings account");
		savingsBalance = savings.deposit("1000.00");
		System.out.println(savings.displayAcct());
		savingsBalance = savings.withdraw("750.00");
		System.out.println(savings.displayAcct());
		savingsBalance = savings.withdraw("250.00");
		System.out.println(savings.displayAcct());
		savingsBalance = savings.deposit("200.00");
		System.out.println(savings.displayAcct());
		
		System.out.println("Ending balances on both accounts");
		System.out.println("--------------------------------");
		System.out.println(checkings.displayAcct());
		System.out.println(savings.displayAcct());
		
		// transfer money from savings to checking
		checkings.transfer("100.00", savings);
		System.out.println("--------------------------------");
		System.out.println("Ending balances on both accounts after $100 transfer");
		System.out.println("--------------------------------");
		System.out.println(checkings.displayAcct());
		System.out.println(savings.displayAcct());
	}

}
