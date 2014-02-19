package com.epam.koryagin.wp;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TxtDocumentTest extends TxtDocumentTestCase {
	private TxtDocument doc;
	private Parser parser;
	private Set<String> content;
	private Set<String> tokens;

	public TxtDocumentTest() {
		super();
	}

	@Before
	public void setUp() throws IOException {
		doc = new TxtDocument(getFile("test.txt"));
		parser = new Parser(doc);
		content = new LinkedHashSet<String>();
		content.add("First line.");
		content.add("Second line with white spaces.");
		content.add("Lorem ipsum dolor sit amet");
		content.add("consectetur adipisicing elit,");
		content.add("sed do eiusmod tempor incididunt");
		content.add("ut labore et dolore magna aliqua.");
		
		tokens = new LinkedHashSet<String>();
		tokens.add("Second");
		tokens.add("line");
		tokens.add("with");
		tokens.add("white");
		tokens.add("spaces");
		
	}

	@After
	public void tearDown() throws Exception {
		doc = null;
	}

	@Test
	public void testTxtDocument() throws IOException {
		assertEquals("Test getLine()", "First line.", doc.getLine(0));
		assertEquals("Test purge white spaces",
				"Second line with white spaces.",
				doc.purge(doc.getLine(1)));
		assertEquals(
				"Test purge white spaces in content collection",
				content,
				new LinkedHashSet<String>(doc.purge(new LinkedList<String>(doc
						.getContent()))));
		assertEquals("Match word",tokens, new LinkedHashSet<String>(parser.parseLine(1)) );
	}

}