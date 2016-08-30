package ssa.test;

import static org.junit.Assert.*;
import ssa.Savings;
import org.junit.Before;
import org.junit.Test;


public class SavingsTest {

	@Before
	public void setup() {
		//System.out.println("Savings class test");
	}
	
	@Test
	public void testGetBalance() {	
		Savings savings10 = new Savings("Savings");
		savings10.deposit(4000);
		assertEquals(4000, savings10.getBalance(), 0);		
	}
	
	@Test
	public void testGetId() {
		// assert that the default constructor automatically assigns an integer type Id > 0
		Savings savings11 = new Savings();
		int aInt = savings11.getId();
		
		if ( (aInt == (int)aInt) && (aInt > 0)) { 
			System.out.println("getId returns randomized int type: true & Id indexing starts greater than 0");
		}
		
		// assert that subsequent Id's are automatically incremented by one
		Savings savings111 = new Savings();
		assertEquals(aInt, (savings111.getId())-1);
	}

	@Test
	public void testManualIdGeneration() {
		Savings savings12 = new Savings(5000, "A description");
		assertEquals(5000, savings12.getId());
		
		// assert that the account Id resynchronizes automatic id generation to the next highest index if the
		// manual id constructor is called
		Savings savings120 = new Savings();
		assertEquals(5001, savings120.getId());
	}
	
	@Test
	public void testStringAndDoubleMethodsForSettingInterest() {
		// test for String argument type on setInterestRate method
		Savings savings13 = new Savings();
		savings13.setInterestRate("3%");
		assertEquals(0.03, savings13.getInterestRate(),0);
		
		// test for double argument type on setInterestRate method
		Savings savings14 = new Savings();
		savings14.setInterestRate(0.05);
		assertEquals(0.05, savings14.getInterestRate(),0);
		
		// assert that negative interest rates are ignored
		savings14.setInterestRate(-0.1);
		assertEquals(0.05, savings14.getInterestRate(),0);
		savings13.setInterestRate("-10%");
		assertEquals(0.03, savings13.getInterestRate(),0);
	}
	
	@Test
	public void testDeposit() {
		Savings savingsC = new Savings();
		savingsC.deposit(456.15);
		assertEquals(456.15, savingsC.getBalance(),0);
		
		// assert that negative deposit amounts are invalid
		savingsC.deposit(-1.0);
		assertEquals(456.15, savingsC.getBalance(),0);
	}
	
	@Test
	public void testWithdraw() {
		Savings savingsD = new Savings();
		savingsD.deposit(1000);
		savingsD.withdraw(800);
		assertEquals(200, savingsD.getBalance(),0);
		
		// assert that negative withdrawal values are invalid 
		savingsD.withdraw(-100);
		assertEquals(200, savingsD.getBalance(),0);
	}
	
	@Test
	public void testWithdrawMoreThanAvailable() {
		// Asserts that the withdrawn amount can not be more than the existing amount in savings	
		Savings savings1 = new Savings("Savings test");
		savings1.deposit(400);		
		assertEquals(400, savings1.withdraw(500), 0);	
		
	}
	
	@Test
	public void testSetAndGetDescription() {
		Savings savings2 = new Savings("Savings test2");
		savings2.setDescription("changed description");
		assertEquals("changed description", savings2.getDescription());
		
	}

	@Test
	public void testSetMinimumBalance() {
		Savings savingsE = new Savings("Savings");
		savingsE.setMinimumBalance(100);
		assertEquals(100, savingsE.getMinimumBalance(),0);
		
		// assert that negative values can not be set for minimumBalance
		savingsE.setMinimumBalance(-500);
		assertEquals(100, savingsE.getMinimumBalance(),0);
	}
	
	@Test
	public void testInterestShouldNotAccrueIfMinimumBalanceNotMet() {
		//assert that no interest is generated if the minimum balance is not met
		Savings savings3 = new Savings("Savings 3");
		savings3.setMinimumBalance(501);
		savings3.deposit(500);
		savings3.setInterestRate(0.05);	
		assertEquals(0, savings3.calcDepositInterest(12),0);
	}
	
	@Test
	public void testInterestGeneratedAndAccrued() {
		// assert that interest is generated 
		Savings savings4 = new Savings("Savings 4");
		savings4.deposit(500);
		savings4.setInterestRate(0.05);	
		assertEquals(25, savings4.calcDepositInterest(12),0);
		
		// assert that interest is accrued in interestPaid variable
		savings4.calcDepositInterest(12);
		assertEquals(51.25, savings4.getInterestPaid(),0);
	}
	
	@Test
	public void testTransferBetweenAccounts() {
		Savings savingsA = new Savings();
		Savings savingsB = new Savings();
		savingsA.deposit(500);
		savingsB.deposit(500);
		savingsA.transferFrom(savingsB, 250);
		assertEquals(750, savingsA.getBalance(),0);
		assertEquals(250, savingsB.getBalance(),0);
		
		// assert that if there is not enough funds to be transferred, the transaction does not take place
		savingsA.transferFrom(savingsB, 1000);
		assertEquals(750, savingsA.getBalance(),0);
		assertEquals(250, savingsB.getBalance(),0);
		
		// assert that a negative transfer amount will not take place
		savingsA.transferFrom(savingsB, -10);
		assertEquals(750, savingsA.getBalance(),0);
		assertEquals(250, savingsB.getBalance(),0);
	}
	
	@Test
	public void testPrint() {
		String strMatchA = "Account 100 balance is $0.00 Amount of interest Paid is $0.00";
		String strMatchB = "----------------Account Status-------------\nAccount ID: 100\nDescription: test print\nAccount balance: $0.00\nInterest Rate: 0.0 | Interest Paid: $0.00\n";
		Savings savingsC = new Savings(100, "test print");
		assertEquals(strMatchA, savingsC.print());
		assertEquals(strMatchB, savingsC.accountStatus());
	}
}
