package com.epam.koryagin.wp;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TextDocumentTest extends TextDocumentTestCase {

	public TextDocumentTest() {
		super();
	}
	
	TextDocument doc;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
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
	public void testDoc() throws IOException{
		//doc = new TextDocument(getFile("test.txt"));
		assertEquals("Test getFirstLine()", "First line.", doc.getFirstLine());
	}
}
