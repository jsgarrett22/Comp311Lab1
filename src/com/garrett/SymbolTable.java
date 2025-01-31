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
    	if (!hasSymbol(name)) {
    		symbols.add(new Symbol(name, value));
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
     * Determines if the symbol table currently contains the symbol or not.
     * @param string The symbol to check.
     * @return boolean true if the symbol table current contains that symbol, false if not.
     * @author Joshua S. Garrett
     */
    public boolean hasSymbol(String symbol) {
    	for (int i = 0; i < symbols.size(); i++) {
    		Symbol currentSymbol = symbols.get(i);
    		if (currentSymbol.getSymbol().equals(symbol)) {
    			return true;
    		}
    	}
    	return false;
    }
}
