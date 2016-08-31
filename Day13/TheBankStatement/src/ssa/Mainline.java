package ssa;

import java.util.ArrayList;

public class Mainline {

	public static void main(String[] args) {

		Savings savings1 = new Savings("Savings 1");
		savings1.deposit(1500);
		savings1.setInterestRate(0.015);

		Savings savings2 = new Savings("Savings 2");
		savings2.deposit(1500000);
		savings2.setInterestRate(0.05);
		
		Checkings checkings1 = new Checkings("Checkings 1");
		checkings1.deposit(5000);
		checkings1.withdraw(100);
		checkings1.withdraw(200);
		checkings1.withdraw(300);
		
		Checkings checkings2 = new Checkings("Checkings 2");
		checkings2.deposit(58000);
		checkings2.withdraw(0.50);
		checkings2.setLastCheckNbr(230);

		ArrayList<Account> accountList = new ArrayList<Account>();
		accountList.add(savings1);
		accountList.add(checkings1);
		accountList.add(savings2);
		accountList.add(checkings2);

		System.out.println("T Act Description                  Balance       Chk    Rate");
		System.out.println("= === ============================ ============= =====  ======");
		
		for(Account aAcct : accountList) {
			System.out.println(aAcct.print());
		}	
	}

}