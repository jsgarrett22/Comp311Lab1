package com.garrett;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class SimpleVM
{   
	public List<String> list;
	public List<Operation> operations;
	public SymbolTable symbols;
	public Stack<Integer> stack;
	public int programCount;
	public int loopIndex;
	public Parser parser;
	List<Integer> listOfSkips;
	
    /**
     * Creates a SimpleVM with the program contained in 
     * the supplied Scanner.
     * 
     * @param scanner the Scanner containing the program
     * @throws OperationNotSupportedException 
     */
    public SimpleVM(Scanner scanner)
    {
    	// Initialize variables
    	parser = new Parser(scanner);
    	operations = new ArrayList<Operation>();
    	symbols = new SymbolTable();
    	stack = new Stack<Integer>();
    	programCount = 0;
    	loopIndex = 0;
    	
        // Convert the text program into a list of Operation objects
    	list = parser.parseStrings();
    	listOfSkips = calculateSkips(list);
    	convertListToOperations(this.list);
    }

    /**
     * Runs the loaded program.
     */
    public void run()
    {
    	while (programCount < operations.size()) {
    		programCount = operations.get(programCount).execute(programCount, stack, symbols);
    	}
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
    	return this.symbols.getValue(name);
    }
    
    /**
     * Converts each list element to its corresponding operation type.
     * @author Joshua S. Garrett
     * @throws OperationNotSupportedException 
     */
    public void convertListToOperations(List<String> list) {
    	
    	String cmd = "";
    	String value = "";
    	
    	for (int i = 0; i < this.list.size(); i++) {
    		
    		String currentItem = this.list.get(i);
    		cmd = getItemCommand(currentItem);
    		
    		if (cmd.equalsIgnoreCase("push") && hasValue(currentItem)) {
    			
    			value = getItemValue(currentItem);
    			char c = value.charAt(0);
    			if (Character.isDigit(c)) {
    				System.out.println("The value " + value + " is numeric.");
        			operations.add(new PushOperation(Integer.parseInt(value)));
    			} else {
    				operations.add(new PushOperation(value));
    			}
    		} else if (cmd.equalsIgnoreCase("pop") && hasValue(currentItem)) {
    			value = getItemValue(currentItem);
    			operations.add(new PopOperation(value));
    		} else if (cmd.equalsIgnoreCase("add")) {
    			operations.add(new AddOperation());
    		} else if (cmd.equalsIgnoreCase("subtract")) {
    			operations.add(new SubtractOperation());
    		} else if (cmd.equalsIgnoreCase("multiply")) {
    			operations.add(new MultiplyOperation());
    		} else if (cmd.equalsIgnoreCase("divide")) {
    			operations.add(new DivideOperation());
    		} else if (cmd.equalsIgnoreCase("nop")) {
    			operations.add(new NOPOperation());
    		} else if (cmd.equalsIgnoreCase("compareEQ")) {
    			operations.add(new CompareEQOperation());
    		} else if (cmd.equalsIgnoreCase("compareNEQ")) {
        		operations.add(new CompareNEQOperation());
    		} else if (cmd.equalsIgnoreCase("compareGT")) {
        		operations.add(new CompareGTOperation());
    		} else if (cmd.equalsIgnoreCase("compareGTE")) {
            	operations.add(new CompareGTEOperation());
    		} else if (cmd.equalsIgnoreCase("compareLT")) {
                operations.add(new CompareLTOperation());
    		} else if (cmd.equalsIgnoreCase("compareLTE")) {
                operations.add(new CompareLTEOperation());
    		} else if (cmd.equalsIgnoreCase("loop:") && hasLabel(currentItem)) {
    			loopIndex = i;
    			value = getSecondItemValue(currentItem);
				operations.add(new PushOperation(value));
    		} else if (cmd.equalsIgnoreCase("branchT")) {
                operations.add(new BranchTOperation(list.size()));
    		} else if (cmd.equalsIgnoreCase("branch") && hasValue(currentItem)) {
    			value = getItemValue(currentItem);
    			// if branch has a label of 'loop', then branch loop
    			if (value.equals("loop")) {
    				operations.add(new BranchOperation(loopIndex));
    			// if branch has a label of 'skip', then branch skip index 0
    			} else if (value.equals("skip")) {
    				operations.add(new BranchOperation(listOfSkips.get(0)));
    			} else if (value.equals("skip2")) {
    				operations.add(new BranchOperation(listOfSkips.get(1)));
    			} else if (value.equals("skip3")) {
    				operations.add(new BranchOperation(listOfSkips.get(2)));
    			}
    		} else if (cmd.equalsIgnoreCase("skip:") && hasValue(currentItem)) {
    			operations.add(new PushOperation(1));
    		} else if (cmd.equalsIgnoreCase("skip2:") && hasValue(currentItem)) {
    			operations.add(new BranchOperation(5));
    		} else if (cmd.equalsIgnoreCase("skip3:") && hasValue(currentItem)) {
    			value = getSecondItemValue(currentItem);
				operations.add(new PopOperation(value));
    		} else if (cmd.equalsIgnoreCase("name:") && hasValue(currentItem)) {
    			operations.add(new NOPOperation());
    		} else if (cmd.equalsIgnoreCase("jumpback:") && hasValue(currentItem)) {
    			value = getSecondItemValue(currentItem);
				operations.add(new PushOperation(Integer.valueOf(value)));
    		}else if (cmd.equalsIgnoreCase("end:") && hasValue(currentItem)) {
    			operations.add(new NOPOperation());
    		} else {
    			throw new IllegalArgumentException("That operation is not supported.");
    		}
    	}
    	System.out.println("-----------------------------------------");
    	System.out.println("Done converting list items to operations.");
		System.out.println("Current list item size: " + this.list.size());
		System.out.println("The loop command is at index: " + loopIndex);
		System.out.println("-----------------------------------------");
		System.out.println();
    }
    
    /**
     * Prints each element of the list to console.
     * @author Joshua S. Garrett
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
    	if (hasValue(lineItem)) {
    		String[] str = lineItem.split("\s");
    		return str[1];
    	}
    	return "";
    }
    
    /**
     * Utility method: Returns the second value of a line item if it has one.
     * @author Joshua S. Garrett
     */
    public String getSecondItemValue(String lineItem) {
    	if (hasValue(lineItem)) {
    		String[] str = lineItem.split("\s");
    		return str[2];
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
    
    /**
     * Utility method: Determines if a line item has a valid label following
     * the command or not.
     * @author Joshua S. Garrett
     */
    public boolean hasLabel(String lineItem) {
    	if (hasValue(lineItem)) {
    		String[] str = lineItem.split("\s");
    		if (str.length > 1) {
    			return true;
    		}
    		else {
    			return false;
    		}
    	}
    	return false;
    }
    
    /**
     * Locates the index of each skip command and assigns it an index
     * in a returned list of integers.
     * @param list the list of Strings to check
     * @return list the list of indexes containing the skip commands in order.
     */
    private List<Integer> calculateSkips(List<String> list) {
    	List<Integer> output = new ArrayList<Integer>();
    	// loop for every item
    	for (int i = 0; i < list.size(); i++) {
    		String currentStr = list.get(i);
    		if (currentStr.startsWith("skip")) {
    			output.add(i);
    		}
    	}
    	return output;
    }
}
