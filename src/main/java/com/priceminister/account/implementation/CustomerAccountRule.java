package com.priceminister.account.implementation;

import java.math.BigDecimal;

import com.priceminister.account.AccountRule;

public class CustomerAccountRule implements AccountRule {

	//@Override
	public boolean withdrawPermitted(BigDecimal resultingAccountBalance) {
		
		return resultingAccountBalance .intValue()>=0 ;
	}

}
