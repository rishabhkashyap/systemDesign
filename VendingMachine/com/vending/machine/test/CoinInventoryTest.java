package com.vending.machine.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.vending.constants.Coin;
import com.vending.exception.NoSuchProductException;
import com.vending.exception.OutOfStockException;
import com.vending.inventories.CoinInventory;

public class CoinInventoryTest {
	CoinInventory cointInventory;

	// @Rule
	// ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		cointInventory = new CoinInventory();

	}

	@Test
	public void testAddAndArrangeMoney() throws NoSuchProductException {
		cointInventory.addCoins(Coin.PENNY.toString(), 20);
		cointInventory.addCoins(Coin.NICKEL.toString(), 15);
		cointInventory.addCoins(Coin.DIME.toString(), 10);
		cointInventory.addCoins(Coin.QUATER.toString(), 5);

		cointInventory.addAndArrangeMoney(35);

		assertTrue(cointInventory.getCoinDetail(Coin.QUATER.toString()) == 6
				&& cointInventory.getCoinDetail(Coin.DIME.toString()) == 11);

	}

	@Test
	public void testAddAndArrangeMoneyWithNoQuater() throws NoSuchProductException {
		cointInventory.addCoins(Coin.PENNY.toString(), 20);
		cointInventory.addCoins(Coin.NICKEL.toString(), 15);
		cointInventory.addCoins(Coin.DIME.toString(), 10);
		cointInventory.addAndArrangeMoney(35);
		assertTrue(cointInventory.getCoinDetail(Coin.NICKEL.toString()) == 17
				&& cointInventory.getCoinDetail(Coin.DIME.toString()) == 12
				&& cointInventory.getCoinDetail(Coin.PENNY.toString()) == 25);

	}

	@Test
	public void testGetRefundWithAllCoins() throws NoSuchProductException, OutOfStockException {
		cointInventory.addCoins(Coin.PENNY.toString(), 20);
		cointInventory.addCoins(Coin.NICKEL.toString(), 15);
		cointInventory.addCoins(Coin.DIME.toString(), 10);
		cointInventory.addCoins(Coin.QUATER.toString(), 5);
		List<Coin> change = cointInventory.getRefund(35);
		int changeSum = change.stream().map(coin -> coin.getDenomination()).reduce(0, Integer::sum);

		change.forEach(coin -> System.out.println(coin.getDenomination()));
		assertTrue(changeSum == 35 && cointInventory.getCoinDetail(Coin.QUATER.toString()) == 4
				&& cointInventory.getCoinDetail(Coin.DIME.toString()) == 9
				&& cointInventory.getCoinDetail(Coin.NICKEL.toString()) == 15
				&& cointInventory.getCoinDetail(Coin.PENNY.toString()) == 20);

	}

	@Test
	public void testGetRefundWithNoQuater() throws NoSuchProductException, OutOfStockException {
		cointInventory.addCoins(Coin.PENNY.toString(), 20);
		cointInventory.addCoins(Coin.NICKEL.toString(), 15);
		cointInventory.addCoins(Coin.DIME.toString(), 10);
		// cointInventory.addCoins(Coin.QUATER.toString(), 5);
		List<Coin> change = cointInventory.getRefund(35);
		int changeSum = change.stream().map(coin -> coin.getDenomination()).reduce(0, Integer::sum);

		change.forEach(coin -> System.out.println(coin.getDenomination()));
		assertTrue(changeSum == 35 && cointInventory.getCoinDetail(Coin.DIME.toString()) == 8
				&& cointInventory.getCoinDetail(Coin.NICKEL.toString()) == 13
				&& cointInventory.getCoinDetail(Coin.PENNY.toString()) == 15);

	}

	@Test(expected = OutOfStockException.class)
	public void testDeductMoney() throws NoSuchProductException, OutOfStockException {
		cointInventory.addCoins(Coin.QUATER.toString(), 0);
		cointInventory.deductCoin(Coin.QUATER.toString());

	}

}
