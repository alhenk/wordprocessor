package com.epam.koryagin.wp.txt;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Processor {
	public static ResourceBundle properties = ResourceBundle.getBundle("com.epam.koryagin.wp.resources.paragraph");
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
		line = line.replaceAll("\u00ac", "");
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

	/**
	 * Paragraph detector return merges lines of content in one paragraph by the
	 * empty string followed the block or end of line and full stop sign
	 * 
	 * @param content
	 * @return
	 */
	public static List<String> paragraphDetector(List<String> content) {
		List<String> paragraphs = new LinkedList<String>();
		StringBuilder sb = new StringBuilder();
		//String endOfParagraphRegex = "[!?\\.…]+[\"]*$";
		String endOfParagraphRegex = properties.getString("paragraph.separator");
		//String emptyLineRegex = "(^[\\s]*$)";
		String emptyLineRegex = properties.getString("paragraph.emptyline");
		Pattern endOfParagraphPattern = Pattern.compile(endOfParagraphRegex);
		Pattern emptyLinePattern = Pattern.compile(emptyLineRegex);
		Matcher endOfParagraph;
		Matcher emptyLine;
		ListIterator<String> iterator = (ListIterator<String>) content
				.iterator();
		while (iterator.hasNext()) {
			String currentLine = (String) iterator.next();
			emptyLine = emptyLinePattern.matcher(currentLine);
			endOfParagraph = endOfParagraphPattern.matcher(currentLine);
			if (emptyLine.matches()) {
				paragraphs.add(purge(sb.toString()));
				sb = new StringBuilder();
			} else if (endOfParagraph.find()
					|| iterator.nextIndex() == content.size()) {
				sb.append(currentLine).append(" ");// append(" ") - for avoiding
													// concatenation of two edge
													// words
				paragraphs.add(purge(sb.toString()));
				sb = new StringBuilder();
			} else {
				sb.append(currentLine).append(" ");
			}
		}
		return paragraphs;
	}

}
