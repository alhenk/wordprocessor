package com.epam.koryagin.wp;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.epam.koryagin.wp.parser.Parser;

public class TextReaderTest extends TextReaderTestCase {
	private TextReader doc;
	private Set<String> content;
	public TextReaderTest() {
		super();
	}

	@Before
	public void setUp() throws IOException {
		doc = new TextReader(getFile("sample_content.txt"));
		content = new LinkedHashSet<String>();
		content.add("First line.");
		content.add("Second line with white spaces.");
		content.add("Lorem ipsum dolor sit amet");
		content.add("consectetur adipisicing elit,");
		content.add("sed do eiusmod tempor incididunt");
		content.add("ut labore et dolore magna aliqua.");
	}

	@After
	public void tearDown() throws Exception {
		doc = null;
		content = null;
	}

	@Test
	public void testTxtDocument() throws IOException {
		assertEquals("Test getLine()", "First line.", doc.getLine(0));
		assertEquals("Test purge white spaces in single line",
				"Second line with white spaces.",
				Parser.cleanWhiteSpaces(doc.getLine(1)));
		assertEquals(
				"Test purge white spaces in collection content",
				content,
				new LinkedHashSet<String>(Parser.cleanWhiteSpaces(new LinkedList<String>(doc
						.getContent()))));
	}

}
