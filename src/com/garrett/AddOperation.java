package com.garrett;

import java.util.Stack;

/**
 * An add arithmetic command that adds the top two values from the stack and pushes the result back onto
 * the stack.
 * Example: 'add' will pop the top two values from the stack, add them together, and push the result back
 * onto the stack.
 * @author Joshua S. Garrett
 * @version Feb 4rd, 2025
 */
public class AddOperation implements Operation {
	
	public AddOperation() {
		System.out.println("New AddOperation created.");
	}

	@Override
	public int execute(int programCounter, Stack<Integer> stack, SymbolTable symbolTable) {
		int num1 = stack.pop();
		System.out.println("Popping " + num1 + " off the stack.");
		int num2 = stack.pop();
		System.out.println("Popping " + num2 + " off the stack.");
		int result = add(num1, num2);
		stack.push(result);
		System.out.println("Pushing value '" + result + "' on stack.");
		return programCounter + 1;
	}
	
	/**
	 * Adds two operands together and returns the result
	 * @author Joshua S. Garrett
	 * @version Feb 4th, 2025
	 * @param num1 the first operand
	 * @param num2 the second operand
	 * @return the result of adding both operands together
	 */
	final private int add(int num1, int num2) {
		System.out.println("Adding " + num1 + " and " + num2);
		return num1 + num2;
	}

}
