package com.garrett.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import java.security.InvalidParameterException;

import org.junit.Test;

import com.garrett.Symbol;

public class SymbolTest {

	/**
	 * Tests the default constructor of the Symbol class.
	 */
	@Test
	public void testSymbol() {
		Symbol symbol = new Symbol();
		assertNotNull(symbol);
		assertNull(symbol.getSymbol());
		assertEquals(0, symbol.getValue());
	}

	/**
	 * Tests the creation of a Symbol, given a symbol and a value. A
	 * symbol cannot be empty.
	 */
	@Test
	public void testSymbolStringInt() {
		/* Test trying to create an empty symbol */
		assertThrows("A symbol can not be empty",
				InvalidParameterException.class, 
				() -> new Symbol("", 5));

		/* Test creating an appropriate symbol */
		Symbol symbol = new Symbol("x", 5);
		assertNotNull(symbol);
		assertEquals("x", symbol.getSymbol());
		assertEquals(5, symbol.getValue());
	}

	/** 
	 * Tests the getting and setting of a Symbol's symbol string. Cannot
	 * be an empty string.
	 */
	@Test
	public void testGetSetSymbol() {
		/* Test trying to create an empty symbol */
		assertThrows("A symbol can not be empty",
				InvalidParameterException.class, 
				() -> new Symbol("", 5));
		
		Symbol symbol = new Symbol("x", 5);
		assertEquals("x", symbol.getSymbol());
		symbol.setSymbol("y");
		assertEquals("y", symbol.getSymbol());
	}

	/** 
	 * Tests the getting and setting of a Symbol's value.
	 */
	@Test
	public void testGetSetValue() {
		/* Test setting a positive value */
		Symbol symbol = new Symbol("x", 5);
		assertEquals(5, symbol.getValue());
		symbol.setValue(10);
		assertEquals(10, symbol.getValue());
		
		/* Test setting a negative value (perfectly acceptable given context) */
		symbol.setValue(-100);
		assertEquals(-100, symbol.getValue());
	}

}
