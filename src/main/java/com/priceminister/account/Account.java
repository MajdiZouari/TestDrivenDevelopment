package com.priceminister.account;

import java.math.BigDecimal;

public interface Account {
	
	
	/**
	 * Adds money to this account.
	 * 
	 * @param addedAmount - the money to add
	 */
	public void add(BigDecimal addedAmount);

	/**
	 * Withdraws money from the account.
	 * 
	 * @param withdrawnAmount - the money to withdraw
	 * @param rule            - the AccountRule that defines which balance is
	 *                        allowed for this account
	 * @return the remaining account balance
	 * @throws IllegalBalanceException if the withdrawal leaves the account with a
	 *                                 forbidden balance
	 */
	public BigDecimal withdrawAndReportBalance(BigDecimal withdrawnAmount, AccountRule rule) throws IllegalBalanceException;

	/**
	 * Gets the current account balance.
	 * 
	 * @return the account's balance
	 */
	public BigDecimal getBalance();

	String getClient();


}
