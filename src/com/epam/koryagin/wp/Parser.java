package com.epam.koryagin.wp;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
	private TxtDocument doc;
	private List<String> tokens;

	public Parser(TxtDocument doc) {
		this.setDoc(doc);
		tokens = new LinkedList<String>();
	}

	public List<String> getTokens() {
		return tokens;
	}

	public void setTokens(List<String> tokens) {
		this.tokens = tokens;
	}
	
	public TxtDocument getDoc() {
		return doc;
	}

	public void setDoc(TxtDocument doc) {
		this.doc = doc;
	}
	
	public List<String> parseLine(int i) {
		String line = doc.getContent().get(i);
		Pattern pat = Pattern.compile("([^\\s,?!;:.]+)");
		Matcher mat = pat.matcher(line);
		while (mat.find()){
			tokens.add(mat.group());
		}
//		tokens.add("Second");
//		tokens.add("line");
//		tokens.add("with");
//		tokens.add("white");
//		tokens.add("spaces");
		return tokens;
	}
}
