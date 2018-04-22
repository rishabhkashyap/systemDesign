package com.vending.machine.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.vending.Product.Coke;
import com.vending.Product.Pepsi;
import com.vending.Product.Product;
import com.vending.Product.Soda;
import com.vending.machine.VendingMachine;
import com.vending.machine.VendingMachineImpl;

public class VendingMachineImplTest {
	VendingMachine vendingMachine;

	@Before
	public void setUp() throws Exception {
		vendingMachine=new VendingMachineImpl();
		Product coke=new Coke();
		Product pepsi=new Pepsi();
		Product soda=new Soda();
		vendingMachine.addProduct(pepsi, 20);
	
		
	}

	@Test
	public void testVendingMachineImpl() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProductDetailsHappyPath() {
		Product pepsiTest=new Pepsi();
		
		Product product=vendingMachine.getProductDetails("Pepsi");
		assertTrue(pepsiTest.getProductName().equals(product.getProductName()) 
				&& pepsiTest.getPrice()==product.getPrice());
	}
	
	@Test
	public void testGetProductDetailsWithNoProduct(){
		Product product=vendingMachine.getProductDetails("Coke");
		assertNull(product);
	}

	@Test
	public void testSellProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddProductHappyPath() {
		Product soda=new Soda();
		int quantity = vendingMachine.addProduct(soda, 20);
		assertTrue(quantity==20 && vendingMachine.getProducts().contains(soda));
			
	}
	
	@Test
	public void testAddProductThatAlreadyExist(){
		Product soda=new Soda();
		int quantity=vendingMachine.addProduct(soda, 20);
		int latestQuantity=vendingMachine.addProduct(soda, 30);
		assertTrue(latestQuantity==50 && vendingMachine.getProducts().contains(soda));
	}

	@Test
	public void testUpdateProductInventory() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCoinInventory() {
		fail("Not yet implemented");
	}

	@Test
	public void testObject() {
		fail("Not yet implemented");
	}

	
	

	
}
