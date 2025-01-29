package com.garrett;

import java.security.InvalidParameterException;

/**
 * A class representing a particular symbol, mapped to a particular value.
 * This is my own vision of a simple pair class, taken from:
 * https://www.geeksforgeeks.org/pair-class-in-java/
 * @author Joshua S. Garrett
 */
public class Symbol {
	
	private String symbol;
	private int value;
	
	public Symbol() {};
	
	public Symbol(String symbol, int value) {
		setSymbol(symbol);
		this.value = value;
	}
	
	public String getSymbol() {
		return this.symbol;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void setSymbol(String symbol) {
		if (!symbol.isEmpty()) {
			this.symbol = symbol;
		} else {
			throw new InvalidParameterException("A symbol can not be empty");
		}
	}
	
	public void setValue(int value) {
		this.value = value;
	}

}
