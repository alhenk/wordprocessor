package com.epam.koryagin.wp;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParserTest extends TxtDocumentTestCase {
	private TxtDocument doc;
	private Parser parser;
	private Set<String> content;
	private Set<String> tokens;
	private Set<String> sentences;

	@Before
	public void setUp() throws IOException {
		doc = new TxtDocument(getFile("sample_sentences.txt"));
		parser = new Parser(doc);

		content = new LinkedHashSet<String>();
		content.add("The sun did not shine. It was too wet to play. So we sat in the house all that cold, cold, wet day.");
		content.add("I sat there with Sally. We sat there, we two. And I said, How I wish we had something to do!");

		tokens = new LinkedHashSet<String>();
		tokens.add("I");
		tokens.add("sat");
		tokens.add("there");
		tokens.add("with");
		tokens.add("Sally");
		tokens.add("We");
		tokens.add("sat");
		tokens.add("there");
		tokens.add("we");
		tokens.add("two");
		tokens.add("And");
		tokens.add("I");
		tokens.add("said");
		tokens.add("How");
		tokens.add("I");
		tokens.add("wish");
		tokens.add("we");
		tokens.add("had");
		tokens.add("something");
		tokens.add("to");
		tokens.add("do");
		
		sentences = new LinkedHashSet<String>();
		sentences.add("The sun did not shine");
		sentences.add("It was too wet to play");
		sentences.add("So we sat in the house all that cold wet day");

	}

	@After
	public void tearDown() {
	}

	@Test
	public void testParser() {
		assertEquals("Parsing sentences", sentences,
				new LinkedHashSet<String>(parser.sentences(doc.getContent().get(0))));
		assertEquals("Word Matching", tokens,
				new LinkedHashSet<String>(parser.parseLine(1)));
	}
}
