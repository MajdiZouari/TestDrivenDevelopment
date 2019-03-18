package com.priceminister.account;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import com.priceminister.account.Account;
import com.priceminister.account.AccountRule;
import com.priceminister.account.IllegalBalanceException;
import com.priceminister.account.implementation.CustomerAccount;
import com.priceminister.account.implementation.CustomerAccountRule;
import com.priceminister.account.CustomerAccountTest;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;

public class CustomerAccountTest extends AbstractTestCase {

	private Account _customerAccount=new CustomerAccount();
	private AccountRule _customerAccountRule = new CustomerAccountRule();;
	
	public CustomerAccountTest(final String s) {
		super(s);
	}

	public static TestSuite suite() {
		return new TestSuite(CustomerAccountTest.class);
	}

	public void setUp() throws Exception {
		_customerAccount = new CustomerAccount();
		_customerAccountRule = new CustomerAccountRule();
	}
	@Test
	public void testAccountWithoutMoneyHasZeroBalance() throws IllegalBalanceException {
		//Given
		_customerAccount = new CustomerAccount("Legrand", new BigDecimal(0.00));
		//When
		//Then
		assertEquals(_customerAccount.getBalance(), new BigDecimal(0.00));

	}
	@Test
	public void testAddPositiveAmount() {
		//Given
		_customerAccount = new CustomerAccount("Client", new BigDecimal(100));
		//When
		_customerAccount.add(new BigDecimal(200));
		//Then
		assertEquals(_customerAccount.getBalance(), new BigDecimal(300));

	}
	@Test
	public void testWithdrawAndReportBalanceIllegalBalance() throws Exception {
		//Given
		BigDecimal widthDrawalAmount = new BigDecimal(150.0);

		_customerAccount = new CustomerAccount("Legrand", new BigDecimal(500.00));
		_customerAccountRule = new CustomerAccountRule();
		//When
		try {

			if (_customerAccountRule.withdrawPermitted(_customerAccount.getBalance()))
				_customerAccount.withdrawAndReportBalance(widthDrawalAmount, _customerAccountRule);
			//Then
			assertEquals(_customerAccount.getBalance(), new BigDecimal(350.0));
		} catch (IllegalBalanceException e) {
			
			fail("Compte non approvisionné");

		}
	}

}
