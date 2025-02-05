package com.garrett.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Stack;

import org.junit.jupiter.api.Test;

import com.garrett.CompareGTEOperation;
import com.garrett.PushOperation;
import com.garrett.SymbolTable;

class CompareGTEOperationTest {

	/**
	 * Tests if constructing operation is dont correctly.
	 */
	@Test
	void testCompareGTEOperation() {
		assertNotNull(new CompareGTEOperation());
	}

	/**
	 * Tests if the comparison of two operands results in a 1 (true)
	 * being pushed onto the stack.
	 */
	@Test
	void testTrueComparison() {
		/* Initialize dummy stack and symboltable */
		Stack<Integer> stack = new Stack<Integer>();
		SymbolTable symbols = new SymbolTable();
		
		/* Populate stack with 2 values */
		PushOperation push1 = new PushOperation(7);
		PushOperation push2 = new PushOperation(7);
		push1.execute(0, stack, symbols);
		push2.execute(1, stack, symbols);
		
		/* Check if true */
		CompareGTEOperation compareGTE = new CompareGTEOperation();
		compareGTE.execute(0, stack, symbols);
		assertEquals(Integer.valueOf(1), stack.peek());
	}
	
	/**
	 * Tests if the comparison of two operands results in a 0 (false)
	 * being pushed onto the stack.
	 */
	@Test
	void testFalseComparison() {
		/* Initialize dummy stack and symboltable */
		Stack<Integer> stack = new Stack<Integer>();
		SymbolTable symbols = new SymbolTable();
		
		/* Populate stack with 2 values */
		PushOperation push1 = new PushOperation(5);
		PushOperation push2 = new PushOperation(7);
		push1.execute(0, stack, symbols);
		push2.execute(1, stack, symbols);
		
		/* Check if false */
		CompareGTEOperation compareGTE = new CompareGTEOperation();
		compareGTE.execute(0, stack, symbols);
		assertEquals(Integer.valueOf(0), stack.peek());
	}

}
