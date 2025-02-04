package com.garrett.test;

import static org.junit.Assert.assertThrows;

import java.io.IOException;
import java.io.StringReader;
import java.security.InvalidParameterException;
import java.util.Scanner;

import javax.naming.OperationNotSupportedException;

import org.junit.Test;

import com.garrett.SimpleVM;

import junit.framework.TestCase;

public class SimpleVMTest extends TestCase {
	
	@Test
	/**
	 * Tests converting each line of the program's commands into an element
	 * in a list container for the SimpleVM.
	 * @throws Exception
	 * @author Joshua S. Garrett
	 */
	public void testConvertToList() throws Exception {
		Scanner reader = new Scanner(new StringReader(
                "push 5\n"
                + "pop x\n"
                ));

        SimpleVM vm = new SimpleVM(reader);
        assertEquals("push 5", vm.list.get(0));
        assertEquals("pop x", vm.list.get(1));
        
        Scanner reader2 = new Scanner(new StringReader(
                "push 5\n"
                + "push 7\n"
                + "add\n"
                + "pop x\n"
                + "push x\n"
                + "push 3\n"
                + "add\n"
                + "pop y\n"
                ));

        vm = new SimpleVM(reader2);
        assertEquals("push 5", vm.list.get(0));
        assertEquals("push 7", vm.list.get(1));
        
        assertEquals("add", vm.list.get(2));
        assertEquals("pop x", vm.list.get(3));
        
        assertEquals("push x", vm.list.get(4));
        assertEquals("push 3", vm.list.get(5));
        
        assertEquals("add", vm.list.get(6));
        assertEquals("pop y", vm.list.get(7));
	}
	
	/**
	 * Tests an invalid push command.
	 */
	@Test
	public void testInvalidPush() throws OperationNotSupportedException, IOException {
		Scanner reader = new Scanner(new StringReader(
                "push\n"));
		
        SimpleVM vm = new SimpleVM(reader);
        assertThrows("That operation is not supported.", 
        		OperationNotSupportedException.class, () -> vm.run());
	}
	
	/**
	 * Tests a push command with a variable that doesn't exist yet.
	 */
	@Test
	public void testPushWithoutExistingValue() throws OperationNotSupportedException, IOException {
		Scanner reader = new Scanner(new StringReader(
                "push x\n"));
		
        SimpleVM vm = new SimpleVM(reader);
        assertThrows("The variable is not defined in the table.", 
        		RuntimeException.class, () -> vm.run());
	}

	/**
	 * Tests an invalid pop command.
	 * @throws IOException 
	 * @throws OperationNotSupportedException 
	 */
	@Test
	public void testInvalidPop() throws OperationNotSupportedException, IOException {
		Scanner reader = new Scanner(new StringReader(
                "pop\n"));
		
        SimpleVM vm = new SimpleVM(reader);
        assertThrows("That operation is not supported.", 
        		OperationNotSupportedException.class, () -> vm.run());
	}
	
	/**
	 * Tests a valid push command of a variable.
	 * @throws IOException 
	 * @throws OperationNotSupportedException 
	 */
	@Test
	public void testValidPushVariable() throws OperationNotSupportedException, IOException {
		Scanner reader = new Scanner(new StringReader(
                "push 5\n"
                + "push 7\n"
                + "pop x\n"
                + "push x\n"
                ));
		
		SimpleVM vm = new SimpleVM(reader);
		vm.run();
	}
	
	/**
	 * Tests converting push line items to push operation objects.
	 * @throws IOException 
	 * @throws OperationNotSupportedException 
	 */
	@Test
	public void testconvertListToOperations() throws IOException, OperationNotSupportedException {
		/* Test simple, invalid program case */
		Scanner reader1 = new Scanner(new StringReader(
				"calculate 100\n"
				));
		
		SimpleVM vm1 = new SimpleVM(reader1);
		assertThrows("That operation is not supported.", 
				OperationNotSupportedException.class, () -> 
					vm1.convertListToOperations(vm1.list));
		
		/* Test simple, valid program case */
		Scanner reader2 = new Scanner(new StringReader(
                "push 5\n"
                + "push 7\n"
                + "pop x"
                ));
		
        SimpleVM vm2 = new SimpleVM(reader2);
        vm2.convertListToOperations(vm2.list);
        assertEquals(3, vm2.operations.size());
        vm2.run();
        // check symbol 'x' equals the value 7
        assertEquals(7, vm2.symbols.getValue("x"));
	}
	
	/**
	 * Tests a RuntimeException is thrown if attempting to get a variable
	 * that is not defined in the symbols table.
	 * @throws IOException 
	 * @throws OperationNotSupportedException 
	 */
	@Test
	public void testInvalidGetValue() throws OperationNotSupportedException, IOException {
		Scanner reader = new Scanner(new StringReader(
                "push 5\n"
                + "pop x\n"
                ));

        SimpleVM vm = new SimpleVM(reader);
        assertThrows("The variable is not defined.",
				RuntimeException.class, 
				() -> vm.getValue("x"));
	}
	
	/**
	 * Tests a RuntimeException is thrown if attempting to get a variable
	 * that is not defined in the symbols table.
	 * @throws IOException 
	 * @throws OperationNotSupportedException 
	 */
	@Test
	public void testValidGetValue() throws OperationNotSupportedException, IOException {
		Scanner reader = new Scanner(new StringReader(
                "push 5\n"
                + "pop x\n"
                ));
		SimpleVM vm = new SimpleVM(reader);
        vm.run();
		assertEquals(5, vm.getValue("x"));
	}
	
	@Test
	/**
	 * Tests if pushing and popping operations work.
	 * @throws Exception
	 */
    public void testPushPop() throws Exception {
        Scanner reader = new Scanner(new StringReader(
                "push 5\n"
                + "pop x\n"
                ));

        SimpleVM vm = new SimpleVM(reader);
        vm.run();
        //assertEquals(0, vm.stack.size());
        //assertEquals(2, vm.operations.size());
        //assertEquals(2, vm.programCount);
        assertEquals(5, vm.getValue("x"));
    }
    
    @Test
    public void testWriteup() throws Exception {
        Scanner reader = new Scanner(new StringReader(
                "push 5\n"
                + "push 7\n"
                + "add\n"
                + "pop x\n"
                + "push x\n"
                + "push 3\n"
                + "add\n"
                + "pop y\n"
                ));

        SimpleVM vm = new SimpleVM(reader);
        vm.run();
        assertEquals(12, vm.getValue("x"));
        assertEquals(15, vm.getValue("y"));
    }
    
    /**
     * Tests if a list item has a value or not.
     * @throws Exception
     */
    @Test
    public void testhasValue() throws Exception {
    	/* Test to see if each line command has a value */
		Scanner reader1 = new Scanner(new StringReader(
                "push 5\n"
                + "pop x\n"
                ));

        SimpleVM vm = new SimpleVM(reader1);
        assertTrue(vm.hasValue(vm.list.get(0)));
        assertTrue(vm.hasValue(vm.list.get(1)));
        
        Scanner reader2 = new Scanner(new StringReader(
                "push 5\n"
                + "push 7\n"
                + "add\n"
                + "pop x\n"
                + "push x\n"
                + "push 3\n"
                + "add\n"
                + "pop y\n"
                ));

        vm = new SimpleVM(reader2);
        assertTrue(vm.hasValue(vm.list.get(0)));
        assertTrue(vm.hasValue(vm.list.get(1)));
        assertFalse(vm.hasValue(vm.list.get(2)));		// add commands does not have a value
        assertTrue(vm.hasValue(vm.list.get(3)));
        assertTrue(vm.hasValue(vm.list.get(4)));
        assertTrue(vm.hasValue(vm.list.get(5)));
        assertFalse(vm.hasValue(vm.list.get(6)));		// add command does not have a value
        assertTrue(vm.hasValue(vm.list.get(7)));
	}
    
    /**
     * Tests if a line item returns the correct command.
     * @throws IOException 
     * @throws OperationNotSupportedException 
     */
    @Test
    public void testgetItemCommand() throws IOException, OperationNotSupportedException {
    	/* Test to see if each line command is correctly returned */
		Scanner reader1 = new Scanner(new StringReader(
                "push 5\n"
                + "pop x\n"
                ));

        SimpleVM vm = new SimpleVM(reader1);
        assertEquals("push", vm.getItemCommand(vm.list.get(0)));
        assertEquals("pop", vm.getItemCommand(vm.list.get(1)));
        
        Scanner reader2 = new Scanner(new StringReader(
                "push 5\n"
                + "push 7\n"
                + "add\n"
                + "pop x\n"
                + "push x\n"
                + "push 3\n"
                + "add\n"
                + "pop y\n"
                ));

        vm = new SimpleVM(reader2);
        assertEquals("push", vm.getItemCommand(vm.list.get(0)));
        assertEquals("push", vm.getItemCommand(vm.list.get(1)));
        
        assertEquals("add", vm.getItemCommand(vm.list.get(2)));
        assertEquals("pop", vm.getItemCommand(vm.list.get(3)));
        
        assertEquals("push", vm.getItemCommand(vm.list.get(4)));
        assertEquals("push", vm.getItemCommand(vm.list.get(5)));
        
        assertEquals("add", vm.getItemCommand(vm.list.get(6)));
        assertEquals("pop", vm.getItemCommand(vm.list.get(7)));
    }
    
    /**
     * Tests if a line item that has a value returns the correct value.
     * @throws IOException 
     * @throws OperationNotSupportedException 
     */
    @Test
    public void testGetItemValue() throws IOException, OperationNotSupportedException {
    	/* Test to see if each line item with a value is correctly returned */
		Scanner reader1 = new Scanner(new StringReader(
                "push 5\n"
                + "pop x\n"
                ));

        SimpleVM vm = new SimpleVM(reader1);
        assertEquals("5", vm.getItemValue(vm.list.get(0)));
        assertEquals("x", vm.getItemValue(vm.list.get(1)));
        
        Scanner reader2 = new Scanner(new StringReader(
                "push 5\n"
                + "push 7\n"
                + "add\n"
                + "pop x\n"
                + "push x\n"
                + "push 3\n"
                + "add\n"
                + "pop y\n"
                ));

        vm = new SimpleVM(reader2);
        assertEquals("5", vm.getItemValue(vm.list.get(0)));
        assertEquals("7", vm.getItemValue(vm.list.get(1)));
        
        assertEquals("", vm.getItemValue(vm.list.get(2)));
        assertEquals("x", vm.getItemValue(vm.list.get(3)));
        
        assertEquals("x", vm.getItemValue(vm.list.get(4)));
        assertEquals("3", vm.getItemValue(vm.list.get(5)));
        
        assertEquals("", vm.getItemValue(vm.list.get(6)));
        assertEquals("y", vm.getItemValue(vm.list.get(7)));
    }
    
    /**
     * Tests the toString method of the list containing all the passed in program's
     * list of commands.
     * @throws IOException 
     * @throws OperationNotSupportedException 
     */
    @Test
    public void testtoString() throws IOException, OperationNotSupportedException {
    	/* Try program with no commands */
    	Scanner reader0 = new Scanner(new StringReader(
                ""
                ));
    	SimpleVM vm = new SimpleVM(reader0);
        assertEquals("[]", vm.toString());
                
		Scanner reader1 = new Scanner(new StringReader(
                "push 5\n"
                + "pop x\n"
                ));

        vm = new SimpleVM(reader1);
        assertEquals("[push 5, pop x]", vm.toString());
        
        Scanner reader2 = new Scanner(new StringReader(
                "push 5\n"
                + "push 7\n"
                + "add\n"
                + "pop x\n"
                + "push x\n"
                + "push 3\n"
                + "add\n"
                + "pop y\n"
                ));

        vm = new SimpleVM(reader2);
        assertEquals("[push 5, push 7, add, pop x, push x, push 3, add, pop y]", vm.toString());
    }
}
