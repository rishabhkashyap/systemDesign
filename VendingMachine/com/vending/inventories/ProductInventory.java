package com.vending.inventories;

import java.util.HashMap;
import java.util.Map;

import com.vending.exception.NoSuchProductException;
import com.vending.exception.OutOfStockException;

public class ProductInventory {
	private Map<String, Integer> bucket;

	public ProductInventory() {
		this.bucket = new HashMap<>();
	}

	public int addProduct(String productName, int productQuantity) {
		if (bucket.containsKey(productName)) {
			int currentQuantity = bucket.get(productName);
			bucket.put(productName, currentQuantity + productQuantity);
			return currentQuantity+productQuantity;
		} else {
			bucket.put(productName, productQuantity);
			return productQuantity;
		}

	}

	public void updateInventory(String productName) throws OutOfStockException, NoSuchProductException {
		if (bucket.containsKey(productName)) {
			int availableQuantity = bucket.get(productName);
			if (availableQuantity == 0) {
				throw new OutOfStockException(productName + " is currently not available...try something else");
			} else {
				--availableQuantity;
				bucket.put(productName, availableQuantity);
			}

		} else {
			throw new NoSuchProductException("Prduct does not exist");
		}

	}

	

	public void clearInventory() {
		bucket.clear();
	}

	public void removeProductFromInventory(String productName) throws NoSuchProductException {
		if (bucket.containsKey(productName)) {
			bucket.remove(productName);
		} else {
			throw new NoSuchProductException("No such product exist !!");
		}

	}
	
	public int getProductCount(String ProductName) {
		if(bucket.containsKey(ProductName)) {
			return bucket.get(ProductName);
		}else {
			return -1;
		}
		
	}
	
	public boolean hasProduct(String productName) {
		if(bucket.containsKey(productName) && bucket.get(productName)>0) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	

}
