package com.priceminister.account;

import junit.framework.TestCase;

public abstract class AbstractTestCase extends TestCase {

    protected AbstractTestCase() {
    }

    protected AbstractTestCase(final String id) {
        super(id);
    }

    protected int getPossibleUniqueIntId() {
    	return (int) (Math.random() * 100000);
    }

    protected String getPossibleUniqueStringId() {
        int id = (int) (Math.random() * 100000);
        return "" + id;
    }

}
