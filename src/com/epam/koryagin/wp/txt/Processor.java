package com.epam.koryagin.wp.txt;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Processor {

	private Processor() {
	}

	/**
	 * Remove all extra whitespace and replace it with one space
	 * 
	 * @param line
	 * @return line
	 */
	public static String purge(String line) {
		line = line.trim();
		line = line.replaceAll("\\s+\\.$", ".");
		line = line.replaceAll("\\s+,$", ",");
		line = line.replaceAll("\\s+!$", "!");
		line = line.replaceAll("\\s+?$", "?");
		line = line.replaceAll("\\s+", " ");
		return line;
	}

	public static String mergeLine(String line) {
		line = line.trim();
		line = line.replace("\n", " ").replace("\r", " ");
		return line;
	}

	public static List<String> purge(LinkedList<String> content) {
		String line;
		for (int i = 0; i < content.size(); i++) {
			line = purge(content.get(i));
			content.set(i, line);
		}
		return content;
	}

	public static List<String> paragraphDetector(List<String> content) {
		List<String> paragraphs = new LinkedList<String>();
		StringBuilder sb = new StringBuilder();
		String endOfParagraphRegex = "[!?\\.]+[\"]*$";
		Pattern endOfParagraphPattern = Pattern.compile(endOfParagraphRegex);
		Matcher endOfParagraph;
		ListIterator<String> iterator = (ListIterator<String>) content
				.iterator();
		while (iterator.hasNext()) {
			String line = (String) iterator.next();
			endOfParagraph = endOfParagraphPattern.matcher(line);
			sb.append(line);
			if (endOfParagraph.find() || iterator.nextIndex() == content.size()) {
				paragraphs.add(purge(mergeLine(sb.toString())));
				sb = new StringBuilder();
			}
		}
		return paragraphs;
	}

}
