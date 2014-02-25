package com.epam.koryagin.wp;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * First attempt to split sentences
 * 
 * @author Alexandr Koryagin
 * 
 */
public class Parser {
	private TextReader doc;
	private List<String> tokens;

	public Parser(TextReader doc) {
		this.setDoc(doc);
		tokens = new LinkedList<String>();
	}

	public List<String> getTokens() {
		return tokens;
	}

	public void setTokens(List<String> tokens) {
		this.tokens = tokens;
	}

	public TextReader getDoc() {
		return doc;
	}

	public void setDoc(TextReader doc) {
		this.doc = doc;
	}

	public List<String> parseLine(int i) {
		String line = doc.getContent().get(i);
		Pattern pat = Pattern.compile("([^\\s,?!;:.]+)");
		Matcher mat = pat.matcher(line);
		while (mat.find()) {
			tokens.add(mat.group());
		}
		return tokens;
	}

	public List<String> sentences(String line) {
		List<String> sentences = new LinkedList<String>();
		String[] result = line.split("[.?!]+");
		if (result != null) {
			for (String str : result) {
				sentences.add(str.trim());
			}
		}
		return sentences;
	}
}
