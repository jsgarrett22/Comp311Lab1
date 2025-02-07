package com.garrett.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.StringReader;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import com.garrett.Parser;

class ParserTest {

	@Test
	void testParseStrings() {
		Scanner sc = new Scanner(new StringReader(
                "push 0\n"
    			+ "pop sum\n"
                + "push 0\n"
    			+ "pop count\n"
                + "loop: push count\n"
    			+ "push 100\n"
                + "compareGT\n"
    			+ "branchT end\n"
                + "push sum\n"
    			+ "push count\n"
                + "add\n"
    			+ "pop sum\n"
                + "push count\n"
    			+ "push 1\n"
                + "add\n"
    			+ "pop count\n"
                + "branch loop\n"
    			+ "end: nop\n"
                ));
		
		Parser parser = new Parser(sc);
		List<String> listOfStrings = parser.parseStrings();
		assertEquals(18, listOfStrings.size());
	}

}
