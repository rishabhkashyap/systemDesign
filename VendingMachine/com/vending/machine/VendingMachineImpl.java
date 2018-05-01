package com.vending.machine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.vending.Product.Product;
import com.vending.constants.Coin;
import com.vending.exception.InsufficientMoneyException;
import com.vending.exception.NoSuchProductException;
import com.vending.exception.OutOfStockException;
import com.vending.inventories.CoinInventory;
import com.vending.inventories.ProductInventory;

public class VendingMachineImpl implements VendingMachine {
	private ProductInventory productInventory;
	private CoinInventory coinInventory;
	private Set<Product> products;

	public VendingMachineImpl() {
		this.productInventory = new ProductInventory();
		this.coinInventory = new CoinInventory();
		this.products = new HashSet<>();
	}

	@Override
	public List<Coin> sellProduct(String productName, int money) throws InsufficientMoneyException {
		List<Coin> change = new ArrayList<>();
		Product product = getProductDetails(productName);
		try {
			if (product == null || productInventory.hasProduct(product.getProductName())) {
				throw new OutOfStockException(productName + "  is  currently unavailable.Try some other drink.");
			} else if (money < product.getPrice()) {
				throw new InsufficientMoneyException("Insufficient money for " + productName);
			} else {
				updateProductInventory(productName);
				System.out.println("Enjoy  your " + product.getProductName());
				updateCoinInventory(money, product.getPrice());

			}

		} catch (NoSuchProductException noSuchProductException) {
			System.out.println(noSuchProductException.getMessage());
		} catch (OutOfStockException outOfStockException) {
			System.out.println(outOfStockException.getMessage());
		}

		return change;
	}

	@Override
	public int addProduct(Product product, int quantity) {
		products.add(product);
		return productInventory.addProduct(product.getProductName(), quantity);

	}

	private void updateProductInventory(String productName) throws OutOfStockException, NoSuchProductException {
		productInventory.updateInventory(productName);

	}

	private List<Coin> updateCoinInventory(int money, int productPrice)
			throws NoSuchProductException, OutOfStockException {
		List<Coin> change = new ArrayList<>();
		int balance = money = productPrice;
		coinInventory.addAndArrangeMoney(productPrice);
		if (balance > 0) {
			change = coinInventory.getRefund(balance);
		}
		return change;

	}

	public Set<Product> getProducts() {
		return products;
	}

	@Override
	public Product getProductDetails(String productName) {
		Product product = null;
		if (productInventory.hasProduct(productName)) {
			Optional<Product> productOptional = products.stream().filter(p -> p.getProductName().equals(productName))
					.findFirst();
			if (productOptional.isPresent()) {
				return productOptional.get();
			}
		} else {
			return null;
		}
		return null;
	}

	@Override
	public int getProductCount(String productName) {
		return productInventory.getProductCount(productName);
		
	}
	

}
