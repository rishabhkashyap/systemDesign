package com.vending.machine;

import java.util.List;

import com.vending.Product.Product;
import com.vending.constants.Coin;

public interface VendingMachine {

	public Product getProductDetails(String productName);
	public List<Coin> sellProduct(String productName);
	public void addProduct(Product product,int quantity);
	public void updateProductInventory();
	public void updateCoinInventory();

}
