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
		push2.execute(1, stack, symbols);
		
		assertEquals(2, stack.size());
		
		/* Create PushOperation object #1 */
		PopOperation pop1 = new PopOperation("x");
		assertEquals(3, pop1.execute(2, stack, symbols));
		
		/* Check all values of the symbol table are correct */
		assertEquals(10, symbols.getValue("x"));
		assertEquals(10, symbols.getValue("y"));
		assertEquals(15, symbols.getValue("z"));
		
		/* Create PushOperation object #2 */
		PopOperation pop2 = new PopOperation("z");
		assertEquals(4, pop2.execute(3, stack, symbols));
		
		/* Check all values of the symbol table are correct again */
		assertEquals(10, symbols.getValue("x"));
		assertEquals(10, symbols.getValue("y"));
		assertEquals(5, symbols.getValue("z"));
	}

}
