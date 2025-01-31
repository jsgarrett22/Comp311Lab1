package com.garrett.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Stack;

import org.junit.Test;

import com.garrett.PushOperation;
import com.garrett.SymbolTable;

/**
 * Tests the PushOperation class.
 * @author Joshua S. Garrett
 * @version Jan 31st, 2025
 */
public class PushOperationTest {

	@Test
	public void testPushOperationConstructor() {
		/* Initialize dummy stack and symboltable */
		Stack<Integer> stack = new Stack<Integer>();
		SymbolTable symbols = new SymbolTable();
		
		/* populate symboltable with test symbols */
		symbols.setValue("x", 5);
		symbols.setValue("y", 10);
		symbols.setValue("z", 15);
		
		/* Create PushOperation object */
		PushOperation op = new PushOperation(5);
		assertNotNull(op);
	}
	
	@Test
	public void testExecute() {
		/* Initialize dummy stack and symboltable */
		Stack<Integer> stack = new Stack<Integer>();
		SymbolTable symbols = new SymbolTable();
		
		/* populate symboltable with test symbols */
		symbols.setValue("x", 5);
		symbols.setValue("y", 10);
		symbols.setValue("z", 15);
		
		/* Check empty stack size */
		assertEquals(0, stack.size());
		
		/* Create PushOperation object #1 */
		PushOperation op = new PushOperation(5);
		
		/* Execute */
		assertEquals(1, op.execute(0, stack, symbols));		// program counter is incremented by 1
		assertEquals(1, stack.size());						// the stack grew by one
		assertEquals(Integer.valueOf(5), stack.peek());		// the value was properly added to the stack, because we were able to retrieve it
		
		/* Create PushOperation object #2 */
		PushOperation op2 = new PushOperation(10);
		
		/* Execute */
		assertEquals(2, op2.execute(1, stack, symbols));		// program counter is incremented by 1
		assertEquals(2, stack.size());						// the stack grew by one
		assertEquals(Integer.valueOf(10), stack.peek());
		
	}

}
