package com.garrett;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class SimpleVM
{   
	List<String> list = new ArrayList<String>();
	Stack<Operation> stack = new Stack<Operation>();
	
    /**
     * Creates a SimpleVM with the program contained in 
     * the supplied Scanner.
     * 
     * @param scanner the Scanner containing the program
     */
    public SimpleVM(Scanner scanner) throws IOException
    {
        // Convert the text program into a list of Operation objects
    	convertTextToList(scanner);
    }

    /**
     * Runs the loaded program.
     */
    public void run()
    {
        //  Execute the program
    }
    
    /**
     * Returns the value of the given variable.
     * 
     * @param name the variable name
     * @return the value
     */
    public int getValue(String name)
    {
        //  Looks things up in the symbol table
        return 0;
    }
    
    /**
     * Adds each line command of the program passed in to a list.
     * @param scanner the program containing lines of different commands.
     * @return the size of the list containing all the commands.
     * @author Joshua S. Garrett
     */
    public void convertTextToList(Scanner scanner) {
    	while (scanner.hasNextLine()) {
    		// print each item to console
    		list.add(scanner.nextLine());
    	}
    }
    
    /**
     * Converts each list element to its corresponding operation type.
     * @author Joshua S. Garrett
     */
    public void convertListToOperations(List<String> list) {
    	// for each element in list
    		// get command
    		// get the value
    		//		new PushOperation(value);
    }
    
    /**
     * Prints each element of the list to console.
     */
    @Override
    public String toString() {
    	StringBuilder str = new StringBuilder();
    	str.append("[");
    	for (int i = 0; i < list.size(); i++) {
    		if (i == list.size() - 1) {
    			str.append(list.get(i));
    			break;
    		}
    		str.append(list.get(i));
    		str.append(", ");
    	}
    	str.append("]");
		return str.toString();
    }
    
    /**
     * Utility method: Returns the command of a line item.
     * @author Joshua S. Garrett
     */
    public String getItemCommand(String lineItem) {
    	// split the lineItem into two parts, the first part is the command
    	String[] str = lineItem.split("\s");
    	return str[0];
    }
    
    /**
     * Utility method: Returns the value of a line item, if it has one.
     * @author Joshua S. Garrett
     */
    public String getItemValue(String lineItem) {
    	// if line item has value
    	// return value
    	// else return ""
    	if (hasValue(lineItem)) {
    		String[] str = lineItem.split("\s");
    		return str[1];
    	}
    	return "";
    }
    
    /**
     * Utility method: Determines if a line item has a value or not.
     * @author Joshua S. Garrett
     */
    public boolean hasValue(String lineItem) {
    	return lineItem.contains(" ") ? true : false;
    }
}
