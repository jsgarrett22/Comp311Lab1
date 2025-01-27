package com.garrett;

import java.io.IOException;
import java.util.Scanner;

public class SimpleVM
{   
    /**
     * Creates a SimpleVM with the program contained in 
     * the supplied Scanner.
     * 
     * @param scanner the Scanner containing the program
     */
    public SimpleVM(Scanner scanner) throws IOException
    {
        // Convert the text program into a list of Operation objects
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
}
