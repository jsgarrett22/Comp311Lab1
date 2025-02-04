package com.garrett.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.security.InvalidParameterException;

import org.junit.Test;

import com.garrett.SymbolTable;

/**
 * Tests the SymbolTable class.
 * @author Joshua S. Garrett
 * @version Jan 31st, 2025
 */
public class SymbolTableTest {

	@Test
	public void testSymbolTableDefaultConstructor() {
		assertNotNull(new SymbolTable());
	}

	@Test
	public void testGetAndSetValue() {
		SymbolTable symbols = new SymbolTable();
		
		/* Set an invalid symbol in the table and catch the exception */
		assertThrows("A symbol can not be empty",
				InvalidParameterException.class, 
				() -> symbols.setValue("", 100));
		
		/* Set a valid symbol in the table and retrieve it */
		symbols.setValue("x", 5);
		assertEquals(5, symbols.getValue("x"));
		
		/* Try getting an undefined symbol */
		assertThrows("A symbol can not be empty",
				RuntimeException.class, 
				() -> symbols.getValue("a"));
	}
	
	@Test
	public void testReplacingExistingVariable() {
		SymbolTable symbols = new SymbolTable();
		symbols.setValue("x", 5);
		assertEquals(1, symbols.size());
		assertEquals(5, symbols.getValue("x"));
		symbols.setValue("x", 10);
		assertEquals(1, symbols.size());
		assertEquals(10, symbols.getValue("x"));
	}
	
	/**
	 * Tests whether the symbol table contains a particular symbol or not.
	 */
	@Test
	public void testhasSymbol() {
		SymbolTable symbols = new SymbolTable();
		symbols.setValue("x", 5);
		symbols.setValue("y", 10);
		symbols.setValue("z", 10);
		assertEquals(3, symbols.size());
		assertEquals(0, symbols.hasSymbol("x"));
		assertEquals(1, symbols.hasSymbol("y"));
		assertEquals(2, symbols.hasSymbol("z"));
		assertEquals(-1, symbols.hasSymbol("a"));
	}

}
