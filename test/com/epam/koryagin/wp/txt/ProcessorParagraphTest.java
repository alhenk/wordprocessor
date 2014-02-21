package com.epam.koryagin.wp.txt;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProcessorParagraphTest {
	private String text;
	
	@Before
	public void setUp() {
	text =
		"To Sherlock Holmes she is always THE woman. I have seldom heard\n"+
		"him mention her under any other name. In his eyes she eclipses\n"+
		"and predominates the whole of her sex. It was not that he felt\n"+
		"any emotion akin to love for Irene Adler. All emotions, and that\n"+
		"one particularly, were abhorrent to his cold, precise but\n"+
		"admirably balanced mind. He was, I take it, the most perfect\n"+
		"reasoning and observing machine that the world has seen, but as a\n"+
		"lover he would have placed himself in a false position. He never\n"+
		"spoke of the softer passions, save with a gibe and a sneer. They\n"+
		"were admirable things for the observer--excellent for drawing the\n"+
		"veil from men's motives and actions. But for the trained reasoner\n"+
		"to admit such intrusions into his own delicate and finely\n"+
		"adjusted temperament was to introduce a distracting factor which\n"+
		"might throw a doubt upon all his mental results. Grit in a\n"+
		"sensitive instrument, or a crack in one of his own high-power\n"+
		"lenses, would not be more disturbing than a strong emotion in a\n"+
		"nature such as his. And yet there was but one woman to him, and\n"+
		"that woman was the late Irene Adler, of dubious and questionable\n"+
		"memory.\n\n";
		
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testParagraphDetector() {
		Processor.paragraphDetector(text);
		fail("Not implemented yet.");
	}
}
