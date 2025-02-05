package com.garrett.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.util.Stack;

import org.junit.jupiter.api.Test;

import com.garrett.DivideOperation;
import com.garrett.PushOperation;
import com.garrett.SymbolTable;

class DivideOperationTest {

	@Test
	public void divideOperationTest() {
		DivideOperation div = new DivideOperation();
		assertNotNull(div);
	}
	
	/**
	 * Tests the valid division of two operands from the top of the stack and
	 * pushing the result back onto the stack.
	 * @author Joshua S. Garrett
	 * @version Feb 5th, 2025
	 */
	@Test 
	public void testValidExecute() {
		/* Initialize dummy stack and symboltable */
		Stack<Integer> stack = new Stack<Integer>();
		SymbolTable symbols = new SymbolTable();
		
		/* populate symboltable with test symbols */
		symbols.setValue("x", 5);
		symbols.setValue("y", 10);
		symbols.setValue("z", 15);
		
		/* Populate stack with 2 values */
		PushOperation push1 = new PushOperation(10);
		PushOperation push2 = new PushOperation(5);
		push1.execute(0, stack, symbols);
		push2.execute(1, stack, symbols);
		
		/* Create Divide Operation */
		DivideOperation div = new DivideOperation();
		div.execute(0, stack, symbols);
		assertEquals(Integer.valueOf(2), stack.peek());
	}
	
	/**
	 * Tests an IllegalArgumentException being thrown if attempting to divide
	 * by zero.
	 * @author Joshua S. Garrett
	 * @version Feb 5th, 2025
	 */
	@Test 
	public void testDivideByZero() {
		/* Initialize dummy stack and symboltable */
		Stack<Integer> stack = new Stack<Integer>();
		SymbolTable symbols = new SymbolTable();
		
		/* populate symboltable with test symbols */
		symbols.setValue("x", 5);
		symbols.setValue("y", 10);
		symbols.setValue("z", 15);
		
		/* Populate stack with 2 values */
		PushOperation push1 = new PushOperation(0);
		PushOperation push2 = new PushOperation(10);
		push1.execute(0, stack, symbols);
		push2.execute(1, stack, symbols);
		
		/* Create Divide Operation */
		DivideOperation div = new DivideOperation();
		assertThrows(IllegalArgumentException.class, () -> div.execute(0, stack, symbols));
	}

}
