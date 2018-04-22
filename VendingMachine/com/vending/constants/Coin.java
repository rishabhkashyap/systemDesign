package com.vending.constants;

public enum Coin {
	PENNY(1),
	NICKEL(5),
	DIME(10),
	QUATER(25);
	
	Coin(int denomination){
		this.denomination=denomination;
	}
	
	public int  getDenomination() {
		return this.denomination;
	}
	int denomination;
	

}
