package com.vending.Product;

public class Coke extends Product {

	public Coke() {
		this.productName = "Coke";
		this.price = 25;
	}
	
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
