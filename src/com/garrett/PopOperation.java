package com.garrett;

import java.util.Stack;

/**
 * A pop command that removes a value from the top of a stack, and assigns it
 * to a particular variable located in the symbol table. 
 * Example: 'pop x' will pop a value from the top of the stack
 * and assign it to the variable 'x' in the a symbol table.
 * @author Joshua S. Garrett
 * @version Feb 3rd, 2025
 */
public class PopOperation implements Operation {
	
	private String symbol;
	
	public PopOperation(String symbol) {
		this.symbol = symbol;
		System.out.println("New PopOperation created with variable: '" + symbol + "'");
	}

	/**
	 * Remove the top value from the stack and assign it to a variable located
	 * in the symbol table.
	 * @return int returns 1 if variable is properly set in table, otherwise, returns 0,
	 * indicating a failure.
	 */
	@Override
	public int execute(int programCounter, Stack<Integer> stack, SymbolTable symbolTable) {
		int value = stack.pop();
		System.out.println("Popping " + value + " off the stack.");
		symbolTable.setValue(symbol, value);
		System.out.println("Assigning " + value + " to '" + symbol + "' in the symbol table.");
		return programCounter + 1;
	}

}
