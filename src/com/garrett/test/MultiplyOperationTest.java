package com.garrett.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Stack;

import org.junit.jupiter.api.Test;

import com.garrett.MultiplyOperation;
import com.garrett.PushOperation;
import com.garrett.SymbolTable;

class MultiplyOperationTest {

	@Test
	public void multiplyOperationTest() {
		MultiplyOperation mult = new MultiplyOperation();
		assertNotNull(mult);
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
		
		/* Populate stack with 2 values */
		PushOperation push1 = new PushOperation(5);
		PushOperation push2 = new PushOperation(10);
		push1.execute(0, stack, symbols);
		push2.execute(1, stack, symbols);
		
		/* Create AddOperation */
		MultiplyOperation mult = new MultiplyOperation();
		mult.execute(0, stack, symbols);
		assertEquals(Integer.valueOf(50), stack.peek());
	}

}
