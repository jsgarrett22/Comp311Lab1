package com.garrett;

import java.util.Stack;

/**
 * A subtraction arithmetic command that subtracts the top two values from the stack and pushes the result back 
 * onto the stack.
 * Example: 'subtract' will pop the top two values from the stack, and push the difference back onto the stack.
 * @author Joshua S. Garrett
 * @version Feb 4rd, 2025
 */
public class SubtractOperation implements Operation {
	
	public SubtractOperation() {
		System.out.println("New SubtractOperation created.");
	}

	@Override
	public int execute(int programCounter, Stack<Integer> stack, SymbolTable symbolTable) {
		int num1 = stack.pop();
		System.out.println("Popping " + num1 + " off the stack.");
		int num2 = stack.pop();
		System.out.println("Popping " + num2 + " off the stack.");
		int result = subtract(num1, num2);
		stack.push(result);
		System.out.println("Pushing value '" + result + "' on stack.");
		return programCounter + 1;
	}
	
	/**
	 * Subtracts two operands and returns the difference.
	 * @author Joshua S. Garrett
	 * @version Feb 4th, 2025
	 * @param num1 the first operand
	 * @param num2 the second operand
	 * @return the result of subtracting both operands.
	 */
	private int subtract(int num1, int num2) {
		if (num1 >= num2) {
			return num1 - num2;
		} else {
			return num2 - num1;
		}
	}

}
