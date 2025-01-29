package com.garrett;

import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import javax.naming.OperationNotSupportedException;

public class SimpleVMDriver {

	public static void main(String[] args) throws IOException, OperationNotSupportedException {
		Scanner reader1 = new Scanner(new StringReader(
                "push 5\n"
                + "pop x\n"
                ));
		SimpleVM vm = new SimpleVM(reader1);
		vm.convertListToOperations(vm.list);
		//System.out.println(vm);
		System.out.println("Size: " + vm.list.size());
		
		System.out.println("--------------------");
		
		/*
		 * Scanner reader2 = new Scanner(new StringReader( "push 5\n" + "push 7\n" +
		 * "add\n" + "pop x\n" + "push x\n" + "push 3\n" + "add\n" + "pop y\n" ));
		 * 
		 * vm = new SimpleVM(reader2); vm.convertListToOperations(vm.list);
		 * //System.out.println(vm); System.out.println("Size: " + vm.list.size());
		 */
        
        vm.run();
		
	}

}
