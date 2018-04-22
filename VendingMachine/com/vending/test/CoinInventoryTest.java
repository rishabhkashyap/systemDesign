package com.vending.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.vending.inventories.CoinInventory;

public class CoinInventoryTest {
	CoinInventory cointInventory;

	@Before
	public void setUp() throws Exception {
		cointInventory=new CoinInventory();
		
	}

	@Test
	public void testAddAndArrangeMoney() {
		
	}

	@Test
	public void testGetRefund() {
		fail("Not yet implemented");
	}

}
