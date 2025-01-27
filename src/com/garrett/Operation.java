package com.garrett;


import java.util.Stack;

/**
 * Represents one statement of the program.
 * 
 * @author Tim
 * @version Jan 13, 2008
 */
public interface Operation
{
    /**
     * Executes the operation.
     * 
     * @param programCounter the index of this operation
     * @param stack the current execution stack
     * @param symbolTable the symbol table
     * @return the index of the next operation to execute
     */
    int execute(int programCounter, Stack<Integer> stack, 
        SymbolTable symbolTable);
}