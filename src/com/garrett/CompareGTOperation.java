package com.garrett;

import java.util.Stack;

public class CompareGTOperation implements Operation {
	
	public CompareGTOperation() {
		System.out.println("New CompareGTOperation created.");
	}

	@Override
	public int execute(int programCounter, Stack<Integer> stack, SymbolTable symbolTable) {
		int num1 = stack.pop();
		System.out.println("Popping " + num1 + " off the stack.");
		int num2 = stack.pop();
		System.out.println("Popping " + num2 + " off the stack.");
		int result = compare(num1, num2);
		stack.push(result);
		System.out.println("Pushing value '" + result + "' on stack.");
		return programCounter + 1;
	}
	
	private int compare(int num1, int num2) {
		System.out.println("Count: " + num2 + " > Limit " + num1 + " = [" + (num2 > num1) + "]");
		return (num2 > num1) ? 1 : 0;
	}

}
