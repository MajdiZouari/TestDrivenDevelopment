package com.priceminister.account;

import java.math.BigDecimal;

import com.priceminister.account.implementation.CustomerAccount;
import com.priceminister.account.implementation.CustomerAccountRule;

import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Test;

public class CustomerAccountTest extends AbstractTestCase {

	private Account _customerAccount;
	private AccountRule _customerAccountRule;
	;

	public CustomerAccountTest(final String s) {
		super(s);
	}

	public static TestSuite suite() {
		return new TestSuite(CustomerAccountTest.class);
	}

	@Before
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
	public void testAccountWithoutMoneyHasPositiveBalance() throws IllegalBalanceException {
		//Given
		_customerAccount = new CustomerAccount("Legrand", new BigDecimal(50.00));
		//When
		//Then
		assertEquals(_customerAccount.getBalance(), new BigDecimal(50.00));

	}

	@Test
	public void testAccountWithoutMoneyHasNegativeBalance() throws IllegalBalanceException {
		//Given
		_customerAccount = new CustomerAccount("Legrand", new BigDecimal(-50.00));
		//When
		//Then
		assertEquals(_customerAccount.getBalance(), new BigDecimal(-50.00));

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
	public void testAddPositiveDecimalAmount() {
		//Given
		_customerAccount = new CustomerAccount("Client", new BigDecimal(100.25));
		//When
		_customerAccount.add(new BigDecimal(200.25));
		//Then
		assertEquals(_customerAccount.getBalance().compareTo(new BigDecimal(300.50)), 0);

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

	@Test
	public void testWithdrawAndReportBalanceIllegalBalanceException() throws Exception {
		//Given
		BigDecimal widthDrawalAmount = new BigDecimal(150.0);

		_customerAccount = new CustomerAccount("Legrand", new BigDecimal(100));
		_customerAccountRule = new CustomerAccountRule();
		//When
		try {

			if (_customerAccountRule.withdrawPermitted(_customerAccount.getBalance()))
				_customerAccount.withdrawAndReportBalance(widthDrawalAmount, _customerAccountRule);
			//Then
		}catch (IllegalBalanceException e) {

			assertEquals(e.toString(),("Illegal account balance: -50"));
		}

	}

}
