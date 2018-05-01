package com.vending.machine.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.vending.Product.Coke;
import com.vending.Product.Pepsi;
import com.vending.Product.Product;
import com.vending.Product.Soda;
import com.vending.constants.Coin;
import com.vending.exception.InsufficientMoneyException;
import com.vending.exception.NoSuchProductException;
import com.vending.exception.OutOfStockException;
import com.vending.machine.VendingMachine;
import com.vending.machine.VendingMachineImpl;

public class VendingMachineImplTest {
	VendingMachine vendingMachine;

	@Before
	public void setUp() throws Exception {
		vendingMachine = new VendingMachineImpl();
		Product pepsi = new Pepsi();
		vendingMachine.addProduct(pepsi, 20);
		vendingMachine.addCoins(Coin.QUATER.toString(), 5);
		vendingMachine.addCoins(Coin.DIME.toString(), 10);
		vendingMachine.addCoins(Coin.NICKEL.toString(), 15);
		vendingMachine.addCoins(Coin.PENNY.toString(), 20);

	}

	@Test
	public void testGetProductDetailsHappyPath() {
		Product pepsiTest = new Pepsi();

		Product product = vendingMachine.getProductDetails("Pepsi");
		assertTrue(pepsiTest.getProductName().equals(product.getProductName())
				&& pepsiTest.getPrice() == product.getPrice());
	}

	@Test
	public void testGetProductDetailsWithNoProduct() {
		Product product = vendingMachine.getProductDetails("Coke");
		assertNull(product);
	}

	@Test
	public void testSellProductHappyPath() throws OutOfStockException, InsufficientMoneyException {
		Product coke = new Coke();
		int changeSum = 0;
		List<Coin> change = new ArrayList<>();

		vendingMachine.addProduct(coke, 20);
		change = vendingMachine.sellProduct(coke.getProductName(), 45);
		if (change.size() > 0) {
			changeSum = change.stream().map(coin -> coin.getDenomination()).reduce(0, Integer::sum);
		}
		int quantityAvailable = vendingMachine.getProductCount(coke.getProductName());
		assertTrue(changeSum == 20 && quantityAvailable == 19);

	}

	@Test(expected = InsufficientMoneyException.class)
	public void testSellProductWithNotEnoughCash() throws InsufficientMoneyException, OutOfStockException {
		Product coke = new Coke();
		vendingMachine.addProduct(coke, 20);
		vendingMachine.sellProduct(coke.getProductName(), 20);

	}

	@Test
	public void testSellWhenProductIsNotInList() throws InsufficientMoneyException {
		Product soda = new Soda();
		vendingMachine.sellProduct(soda.getProductName(), 45);
		boolean isProductAvailable = vendingMachine.getProducts().contains(soda);
		assertFalse(isProductAvailable);
	}

	@Test

	public void testSellProductWithExactMoney() throws InsufficientMoneyException {
		List<Coin> change = new ArrayList<>();
		int changeSum = 0;
		Product coke = new Coke();
		vendingMachine.addProduct(coke, 20);
		change = vendingMachine.sellProduct(coke.getProductName(), 25);
		if (change.size() > 0) {
			changeSum = change.stream().map(coin -> coin.getDenomination()).reduce(0, Integer::sum);
		}

		int quantityAvailable = vendingMachine.getProductCount(coke.getProductName());
		assertTrue(changeSum == 0 && quantityAvailable == 19);

	}

	@Test
	public void testAddProductHappyPath() {
		Product soda = new Soda();
		int quantity = vendingMachine.addProduct(soda, 20);
		assertTrue(quantity == 20 && vendingMachine.getProducts().contains(soda));

	}

	@Test
	public void testAddProductThatAlreadyExist() {
		Product soda = new Soda();
		int quantity = vendingMachine.addProduct(soda, 20);
		int latestQuantity = vendingMachine.addProduct(soda, 30);
		assertTrue(latestQuantity == 50 && vendingMachine.getProducts().contains(soda));
	}

	

	

	

}
