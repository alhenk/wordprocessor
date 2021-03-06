package com.epam.koryagin.wp.text;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.epam.koryagin.wp.components.text.Token;
import com.epam.koryagin.wp.parser.Parser;

public class TokenizeTest {
	private String line = "'I'm a Duchess,' she said."
			+ "Man-made GAZ-24 and $180.5 a \"word\" by 3.62 5,45." + "I is .5.";
	private Set<String> testTokens;

	@Before
	public void setUp() {
		testTokens = new LinkedHashSet<String>();
		testTokens.add("'");
		testTokens.add("I'm");
		testTokens.add("a");
		testTokens.add("Duchess");
		testTokens.add(",");
		testTokens.add("'");
		testTokens.add("she");
		testTokens.add("said");
		testTokens.add(".");
		testTokens.add("Man-made");
		testTokens.add("GAZ-24");
		testTokens.add("and");
		testTokens.add("$180.5");
		testTokens.add("a");
		testTokens.add("\"");
		testTokens.add("word");
		testTokens.add("\"");
		testTokens.add("by");
		testTokens.add(" 3.62");
		testTokens.add(" 5,45");
		testTokens.add(".");
		testTokens.add("I");
		testTokens.add("is");
		testTokens.add(" .5");
		testTokens.add(".");
		testTokens.add(System.lineSeparator());
	}

	@Test
	public void testParagraphDetector() throws ParseException {
		List<Token> tokens = Parser.tokenize(line);
		List<String> strings = new LinkedList<String>();
		for(Token token: tokens){
			strings.add(token.getValue());
		}
		assertEquals("Tokenize Test", testTokens, new LinkedHashSet<String>(strings));
	}
}
