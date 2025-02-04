package com.garrett;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the available variables via a list of Symbols.
 * 
 * @author Tim and Joshua S. Garrett
 * @version Jan 13, 2008 - Revised on 01/31/2025 by Joshua S. Garrett
 */
public class SymbolTable
{
	private List<Symbol> symbols;
	
    /**
     * Creates a SymbolTable.
     */
    public SymbolTable()
    {
    	symbols = new ArrayList<Symbol>();
    }
    
    /**
     * Sets the value of the given variable.
     * 
     * @param name the name
     * @param value the value
     * @author Joshua S. Garrett
     */
    public void setValue(String name, int value)
    {
    	int symbolLocation = hasSymbol(name);
    	// if table doesn't have the variable, add it
    	if (symbolLocation == -1) {
    		symbols.add(new Symbol(name, value));
    		System.out.println("Symbol table does not contain the variable " + name 
    				+ ". Adding to table.");
    	} else {
    		// otherwise, set the new value for the symbol
    		symbols.get(symbolLocation).setValue(value);
    		System.out.println("Symbol table contains the variable " + name + ". Changing symbol " 
    		+ name + " to " + value + ".");
    	}
    }
    
    /**
     * Returns the value of the given variable.
     * @param name the name
     * @return the value
     * @throws RuntimeException if the variable is not defined
     * @author Joshua S. Garrett
     */
    public int getValue(String name)
    {
    	for (int i = 0; i < symbols.size(); i++) {
    		Symbol currentSymbol = symbols.get(i);
    		if (currentSymbol.getSymbol().equals(name)) {
    			return currentSymbol.getValue();
    		}
    	}
        throw new RuntimeException("The variable is not defined in the table.");
    }
    
    /**
     * Returns the size of the symbol table.
     * @return the number of symbols currently in the table.
     * @author Joshua S. Garrett
     */
    public int size() {
    	return symbols.size();
    }
    
    /**
     * Determines if the table contains the symbol or not.
     * @param string The symbol to check.
     * @return int returns the index of the symbol in the list, otherwise returns -1 if it
     * doesn't exist.
     * @author Joshua S. Garrett
     * @version Feb 3rd, 2025
     */
    public int hasSymbol(String symbol) {
    	// if symbol exists
    	for (int i = 0; i < symbols.size(); i++) {
    		Symbol currentSymbol = symbols.get(i);
    		if (currentSymbol.getSymbol().equals(symbol)) {
    			return i;		// return it's index
    		}
    	}
    	return -1;
    }
}
