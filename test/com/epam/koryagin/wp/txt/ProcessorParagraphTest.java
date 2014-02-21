package com.epam.koryagin.wp.txt;

import static org.junit.Assert.*;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProcessorParagraphTest {
	private List<String> content;
	private Set<String> testParagraphs;
	
	@Before
	public void setUp() {
		content = new LinkedList<String>();
		content.add("To Sherlock Holmes she is always THE woman. I have seldom heard\n");
		content.add("him mention her under any other name.\n");
		content.add("In his eyes she eclipses\n");
		content.add("and predominates the whole of her sex. It was not that he felt\n");
		content.add("any emotion akin to love for Irene Adler. All emotions, and that\n");
		content.add("one particularly, were abhorrent to his cold, precise but\n");
		content.add("admirably balanced mind.\n");
		content.add("He was, I take it, the most perfect\n");
		content.add("reasoning and observing machine that the world has seen, but as a\n");
		content.add("lover he would have placed himself in a false position. He never\n");
		content.add("spoke of the softer passions, save with a gibe and a sneer. They\n");
		content.add("were admirable things for the observer--excellent for drawing the\n");
		content.add("veil from men's motives and actions.\n");
		content.add("But for the trained reasoner\n");
		content.add("to admit such intrusions into his own delicate and finely\n");
		content.add("adjusted temperament was to introduce a distracting factor which\n");
		content.add("might throw a doubt upon all his mental results.");
		content.add("Grit in a\n");
		content.add("sensitive instrument, or a crack in one of his own high-power\n");
		content.add("lenses, would not be more disturbing than a strong emotion in a\n");
		content.add("nature such as his. And yet there was but one woman to him, and\n");
		content.add("that woman was the late Irene Adler, of dubious and questionable\n");
		content.add("memory.\n");
		
		testParagraphs = new LinkedHashSet<String>();
		testParagraphs.add("To Sherlock Holmes she is always THE woman. I have seldom heard "
				+ "him mention her under any other name.");
		testParagraphs.add("In his eyes she eclipses and predominates the whole of her sex. "
				+ "It was not that he felt any emotion akin to love for Irene Adler. All emotions, "
				+ "and that one particularly, were abhorrent to his cold, precise but admirably balanced mind.");
		testParagraphs.add("He was, I take it, the most perfect reasoning and observing machine "
				+ "that the world has seen, but as a lover he would have placed himself in a false "
				+ "position. He never spoke of the softer passions, save with a gibe and a sneer. "
				+ "They were admirable things for the observer--excellent for drawing the veil from men's motives and actions.");
		testParagraphs.add("But for the trained reasoner to admit such intrusions into his own "
				+ "delicate and finely adjusted temperament was to introduce a distracting factor "
				+ "which might throw a doubt upon all his mental results.");
		testParagraphs.add("Grit in a sensitive instrument, or a crack in one of his own high-power "
				+ "lenses, would not be more disturbing than a strong emotion in a nature such as his. "
				+ "And yet there was but one woman to him, and that woman was the late Irene Adler, "
				+ "of dubious and questionable memory.");
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testParagraphDetector() {
		List<String> paragraphs = new LinkedList<String>();
		paragraphs = Processor.paragraphDetector(content);
		assertEquals("Paragraph Detector", testParagraphs, new LinkedHashSet<String>(paragraphs));
	}
}
