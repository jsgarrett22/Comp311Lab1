package com.garrett.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Stack;

import org.junit.Test;

import com.garrett.PopOperation;
import com.garrett.PushOperation;
import com.garrett.SymbolTable;

public class PopOperationTest {

	@Test
	public void testPopOperationConstructor() {
		/* Create PopOperation object */
		PopOperation op = new PopOperation("x");
		assertNotNull(op);
	}

	@Test
	public void testExecuteWithNewVariable() {
		/* Initialize dummy stack and symboltable */
		Stack<Integer> stack = new Stack<Integer>();
		SymbolTable symbols = new SymbolTable();
		
		/* populate symboltable with test symbols */
		symbols.setValue("x", 5);
		symbols.setValue("y", 10);
		symbols.setValue("z", 15);
		
		/* Check empty stack size */
		assertEquals(0, stack.size());
		
		/* Populate stack with 2 values */
		PushOperation push1 = new PushOperation(5);
		PushOperation push2 = new PushOperation(10);
		push1.execute(0, stack, symbols);
		push2.execute(0, stack, symbols);
		
		assertEquals(2, stack.size());
		
		/* Create PushOperation object #1 */
		PopOperation pop1 = new PopOperation("x");
		assertEquals(1, pop1.execute(0, stack, symbols));
		assertEquals(10, symbols.getValue("x"));
	}

}
