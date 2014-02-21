package com.epam.koryagin.wp.txt;

import java.util.LinkedList;
import java.util.List;

public final class Processor {
	
	private Processor(){
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

	public static List<String> purge(LinkedList<String> content) {
		String line;
		for (int i = 0; i < content.size(); i++) {
			line = purge(content.get(i));
			content.set(i, line);
		}
		return content;
	}

	public static void paragraphDetector(String text) {
		// TODO Auto-generated method stub
		
	}
	
}
