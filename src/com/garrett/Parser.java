package com.garrett;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class that is responsible for parsing a program inputed into a scanner object.
 * @author Joshua S. Garrett
 * @version Feb 6th, 2025
 */
public class Parser {
	
	private Scanner scanner;
	
	public Parser(Scanner scanner) {
		this.scanner = scanner;	
	}
	
	/**
     * Adds each line command of the program passed in to a list.
     * @param scanner the program containing lines of different commands.
     * @return a list containing each program line as a String.
     * @author Joshua S. Garrett
     */
	public List<String> parseStrings() {
		List<String> output = new ArrayList<String>();
		while (scanner.hasNext()) {
			output.add(scanner.nextLine());
		}
		return output;
	}
}
