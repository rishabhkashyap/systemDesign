package com.vending.Product;

public abstract class Product {
	protected String productName;
	protected int price;
	public String getProductName() {
		return this.productName;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public void setProductPrice(int price) {
		this.price=price;
	}
	

}
