package com.epam.koryagin.wp;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TextDocumentTest extends TextDocumentTestCase {
	private TextDocument doc;

	public TextDocumentTest() {
		super();
	}

	@Before
	public void setUp() throws IOException {
		doc = new TextDocument(getFile("test.txt"));
	}

	@After
	public void tearDown() throws Exception {
		doc = null;
	}

	@Test
	public void testDoc() throws IOException {
		assertEquals("Test getLine()", "First line.", doc.getLine(0));
		assertEquals("Test purge white spaces",  
				"Second line with white spaces.", 
				doc.purgeWhitespace(doc.getLine(1)));
	}
}
