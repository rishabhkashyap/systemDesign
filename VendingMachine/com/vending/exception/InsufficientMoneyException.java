package com.vending.exception;

public class InsufficientMoneyException extends Exception {
	public InsufficientMoneyException(String message){
		super(message);
	}

}
