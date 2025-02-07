package com.garrett;

import java.util.Stack;

/**
 * Command that jumps to the label following the Operation.
 * For example: 'branch loop' will jump back to the loop statement
 */
public class BranchOperation implements Operation {
	
	private int index;

	public BranchOperation(int index) {
		this.index = index;
		System.out.println("New BranchOperation created.");
	}
	
	@Override
	public int execute(int programCounter, Stack<Integer> stack, SymbolTable symbolTable) {
		// jumps to the statement with the matching label
		return index;
	}

}
