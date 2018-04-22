package com.vending.machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vending.Product.Product;
import com.vending.constants.Coin;
import com.vending.inventories.CoinInventory;
import com.vending.inventories.ProductInventory;

public class VendingMachineImpl implements VendingMachine {
	private ProductInventory productInventory;
	private CoinInventory coinInventory;
	private List<Product>products;
	
	 public VendingMachineImpl() {
		this.productInventory=new ProductInventory();
		this.coinInventory=new CoinInventory();
		this.products=new ArrayList<>();
	}

	@Override
	public Product getProductDetails(String productName) {
		Product product=null;
		if(productInventory.isProductAvailable(productName)) {
			Optional<Product>productOptional=products.stream()
					                                  .filter(p->p.getProductName().equals(productName))
					                                  .findFirst();
			if(productOptional.isPresent()) {
				return productOptional.get();
			}
		}else{
			return null;
		}
		return null;
	}

	@Override
	public List<Coin> sellProduct(String productName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addProduct(Product product, int quantity) {
		products.add(product);
		productInventory.addProduct(product.getProductName(), quantity);
		

	}

	@Override
	public void updateProductInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCoinInventory() {
		// TODO Auto-generated method stub

	}

}
