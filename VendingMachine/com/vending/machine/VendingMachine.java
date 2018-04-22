package com.vending.machine;

import java.util.List;
import java.util.Set;

import com.vending.Product.Product;
import com.vending.constants.Coin;
import com.vending.exception.InsufficientMoneyException;
import com.vending.exception.OutOfStockException;

public interface VendingMachine {

	public Product getProductDetails(String productName);
	public List<Coin> sellProduct(String productName,int money) throws OutOfStockException, InsufficientMoneyException;
	public int addProduct(Product product,int quantity);
	public void updateProductInventory(String productName);
	public void updateCoinInventory();
	public Set<Product> getProducts();

}
