package ssa;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BankingTests {

	Savings savingsTest1;
	Savings savingsTest2;
	Checkings checkingsTest1;
	static int testNo = 1;
	
	@Before
	public void setup() {
		savingsTest1 = new Savings();
		savingsTest2 = new Savings();
		checkingsTest1 = new Checkings();
		System.out.printf("Running Savings class test %d\n", testNo);
		testNo++;
	}
	
	@Test
	public void testCheckNumberGetAndSet() {
		assertEquals(100, checkingsTest1.getCheckNumber());
		checkingsTest1.setLastCheckNbr(200);
		assertEquals(200, checkingsTest1.getCheckNumber());
		
		// assert that negative numbers are not accepted as valid check numbers
		checkingsTest1.setLastCheckNbr(-200);
		assertEquals(200, checkingsTest1.getCheckNumber());
	}
	
	@Test
	public void testCheckNumberIncrementOnWithdraw() {
		assertEquals(100, checkingsTest1.getCheckNumber());
		checkingsTest1.deposit(200);
		assertEquals(100, checkingsTest1.getCheckNumber());
		checkingsTest1.withdraw(50);
		
		assertEquals(101, checkingsTest1.getCheckNumber());
	}
	
	
	@Test
	public void testGetBalance() {	
		savingsTest1.deposit(4000);
		assertEquals(4000, savingsTest1.getBalance(), 0);		
	}
	
	@Test
	public void testGetAccountType() {	
		assertEquals('S', savingsTest1.getType());
		assertEquals('C', checkingsTest1.getType());	
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
		savingsTest1.setInterestRate("3%");
		assertEquals(0.03, savingsTest1.getInterestRate(),0);
		
		// test for double argument type on setInterestRate method
		savingsTest2.setInterestRate(0.05);
		assertEquals(0.05, savingsTest2.getInterestRate(),0);
		
		// assert that negative interest rates are ignored
		savingsTest2.setInterestRate(-0.1);
		assertEquals(0.05, savingsTest2.getInterestRate(),0);
		savingsTest1.setInterestRate("-10%");
		assertEquals(0.03, savingsTest1.getInterestRate(),0);
	}
	
	@Test
	public void testDeposit() {
		savingsTest1.deposit(456.15);
		assertEquals(456.15, savingsTest1.getBalance(),0);
		
		// assert that negative deposit amounts are invalid
		savingsTest1.deposit(-1.0);
		assertEquals(456.15, savingsTest1.getBalance(),0);
	}
	
	@Test
	public void testWithdraw() {
		savingsTest1.deposit(1000);
		assertEquals(1000, savingsTest1.getBalance(),0);
		savingsTest1.withdraw(800);
		assertEquals(200, savingsTest1.getBalance(),0);
		
		// assert that negative withdrawal values are invalid 
		savingsTest1.withdraw(-100);
		assertEquals(200, savingsTest1.getBalance(),0);
	}
	
	@Test
	public void testWithdrawMoreThanAvailable() {
		savingsTest1.deposit(400);		
		assertEquals(400, savingsTest1.getBalance(),0);
		
		// Asserts that the withdrawn amount can not be more than the existing amount in savings	
		assertEquals(400, savingsTest1.withdraw(500), 0);	
	}
	
	@Test
	public void testSetAndGetDescription() {
		Savings savingsTest3 = new Savings("Savings description test 1");
		assertEquals("Savings description test 1", savingsTest3.getDescription());
		
		savingsTest3.setDescription("changed description");
		assertEquals("changed description", savingsTest3.getDescription());
	}

	@Test
	public void testSetMinimumBalance() {
		savingsTest1.setMinimumBalance(100);
		assertEquals(100, savingsTest1.getMinimumBalance(),0);
		
		// assert that negative values can not be set for minimumBalance
		savingsTest1.setMinimumBalance(-500);
		assertEquals(100, savingsTest1.getMinimumBalance(),0);
	}
	
	@Test
	public void testInterestShouldNotAccrueIfMinimumBalanceNotMet() {
		savingsTest1.setMinimumBalance(501);
		savingsTest1.deposit(500);
		savingsTest1.setInterestRate(0.05);	
		
		//assert that no interest is generated if the minimum balance is not met
		assertEquals(501, savingsTest1.getMinimumBalance(),0);
		assertEquals(500, savingsTest1.getBalance(),0);
		assertEquals(0.05, savingsTest1.getInterestRate(),0);
		assertEquals(0, savingsTest1.calcDepositInterest(12),0);
	}
	
	@Test
	public void testInterestGeneratedAndAccrued() {
		savingsTest1.deposit(500);
		assertEquals(0, savingsTest1.getInterestPaid(),0);
		
		// assert that interest is generated 
		savingsTest1.setInterestRate(0.05);	
		assertEquals(25, savingsTest1.calcDepositInterest(12),0);
		assertEquals(25, savingsTest1.getInterestPaid(),0);
		
		// assert that interest is accrued in interestPaid variable
		savingsTest1.calcDepositInterest(12);
		assertEquals(51.25, savingsTest1.getInterestPaid(),0);
	}
	
	@Test
	public void testTransferBetweenAccounts() {
		savingsTest1.deposit(500);
		savingsTest2.deposit(500);
		assertEquals(500, savingsTest1.getBalance(),0);
		assertEquals(500, savingsTest2.getBalance(),0);
		
		// test transfer between accounts
		savingsTest1.transferFrom(savingsTest2, 250);
		assertEquals(750, savingsTest1.getBalance(),0);
		assertEquals(250, savingsTest2.getBalance(),0);
		
		// assert that if there is not enough funds to be transferred, the transaction does not take place
		savingsTest1.transferFrom(savingsTest2, 1000);
		assertEquals(750, savingsTest1.getBalance(),0);
		assertEquals(250, savingsTest2.getBalance(),0);
		
		// assert that a negative transfer amount will not take place
		savingsTest1.transferFrom(savingsTest2, -10);
		assertEquals(750, savingsTest1.getBalance(),0);
		assertEquals(250, savingsTest2.getBalance(),0);
	}
	
	@Test
	public void testPrint() {
		String strMatchC = "S 100 test print savings                   $0.00        0.00%";
		Savings savingsC = new Savings(100, "test print savings");
		System.out.println(savingsC.print());
		assertEquals(strMatchC, savingsC.print());
		
		String strMatchD = "C 200 test print checking                  $0.00   100";
		Checkings checkingsC = new Checkings(200, "test print checking");
		System.out.println(checkingsC.print());
		assertEquals(strMatchD, checkingsC.print());
	}
}
