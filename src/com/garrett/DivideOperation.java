package com.garrett;

import java.util.Stack;

public class DivideOperation implements Operation {
	
	public DivideOperation() {
		System.out.println("New DivideOperation created.");
	}

	@Override
	public int execute(int programCounter, Stack<Integer> stack, SymbolTable symbolTable) {
		int num1 = stack.pop();
		System.out.println("Popping " + num1 + " off the stack.");
		int num2 = stack.pop();
		System.out.println("Popping " + num2 + " off the stack.");
		int result = calculate(num1, num2);
		stack.push(result);
		System.out.println("Pushing value '" + result + "' on stack.");
		return programCounter + 1;
	}
	
	private int calculate(int num1, int num2) {
		if (num2 == 0) {
            throw new IllegalArgumentException();
        }
		return num2 / num1;
	}

}
