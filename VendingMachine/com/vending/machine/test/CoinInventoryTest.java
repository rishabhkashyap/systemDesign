package com.vending.machine.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.vending.constants.Coin;
import com.vending.exception.NoSuchProductException;
import com.vending.inventories.CoinInventory;

public class CoinInventoryTest {
	CoinInventory cointInventory;

	@Before
	public void setUp() throws Exception {
		cointInventory=new CoinInventory();
		
	}

	@Test
	public void testAddAndArrangeMoney() throws NoSuchProductException {
		cointInventory.addCoins(Coin.PENNY.toString(), 20);
		cointInventory.addCoins(Coin.NICKEL.toString(), 15);
		cointInventory.addCoins(Coin.DIME.toString(), 10);
		cointInventory.addCoins(Coin.QUATER.toString(), 5);
		
		cointInventory.addAndArrangeMoney(35);
		
		assertTrue(cointInventory.getCoinDetail(Coin.QUATER.toString())==6 
				&& cointInventory.getCoinDetail(Coin.DIME.toString())==11);
		
		
	}
	
	@Test
	public void testAddAndArrangeMoneyWithNoQuater() throws NoSuchProductException {
		cointInventory.addCoins(Coin.PENNY.toString(), 20);
		cointInventory.addCoins(Coin.NICKEL.toString(), 15);
		cointInventory.addCoins(Coin.DIME.toString(), 10);
		cointInventory.addAndArrangeMoney(35);
		System.out.println("Nickel = " +cointInventory.getCoinDetail());
		
		assertTrue(cointInventory.getCoinDetail(Coin.NICKEL.toString())==16 
				&& cointInventory.getCoinDetail(Coin.DIME.toString())==12
				&& cointInventory.getCoinDetail(Coin.PENNY.toString())==25);
		
		
	}

	@Test
	public void testGetRefund() {
		fail("Not yet implemented");
	}

}
