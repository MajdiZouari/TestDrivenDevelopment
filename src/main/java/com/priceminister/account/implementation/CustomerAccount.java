package com.priceminister.account.implementation;

import java.math.BigDecimal;

import com.priceminister.account.Account;
import com.priceminister.account.AccountRule;
import com.priceminister.account.IllegalBalanceException;

public class CustomerAccount implements Account {

	private String client;

	private volatile BigDecimal balance;

	private BigDecimal withdrawnAmount;

	public CustomerAccount() {

	}

	public CustomerAccount(String client, BigDecimal balance) {

		this.client = client;
		this.balance = balance;
	}

	//@Override
	public synchronized void add(BigDecimal addedAmount) {
		// TODO Auto-generated method stub

		balance = balance.add(addedAmount);

	}

	//@Override
	public synchronized BigDecimal withdrawAndReportBalance(BigDecimal withdrawnAmount, AccountRule rule) throws IllegalBalanceException {
		// TODO Auto-generated method stub

		BigDecimal resultingAccountBalance = balance.subtract(withdrawnAmount);
		if (rule.withdrawPermitted(resultingAccountBalance))
			balance = balance.subtract(withdrawnAmount);
		else
			throw new IllegalBalanceException(resultingAccountBalance);
		return balance;
	}

	//@Override
	public synchronized BigDecimal getBalance() {
		// TODO Auto-generated method stub
		return balance;
	}

	//@Override
	public String getClient() {
		return client;
	}

	public BigDecimal getWithdrawnAmount() {
		return withdrawnAmount;
	}
	
	
	

}
