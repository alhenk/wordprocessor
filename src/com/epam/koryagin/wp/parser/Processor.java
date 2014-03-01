package com.epam.koryagin.wp.parser;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.epam.koryagin.wp.components.TextComponent;

/**
 * Utility class for text processing purge(String) purge(LinkedList<String>)
 * paragraphDetector(List<String>) tokenize(String txtLine)
 * 
 * @author Alexandr Koryagin
 * @date 20140225
 */
public final class Processor {
	private static final Logger LOGGER = Logger.getLogger(Processor.class);
	// ResourceBundle is more convenient than Properties
	public static ResourceBundle properties = ResourceBundle
			.getBundle("com.epam.koryagin.wp.resources.regex");

	private Processor() {
	}

	/**
	 * Remove all extra whitespace and replace it with one space
	 * 
	 * @param line
	 *            input String
	 * @return line output String
	 */
	public static String purge(String line) {
		line = line.trim();
		// multiple white spaces before full stop
		line = line.replaceAll("\\s+\\.$", ".");
		// multiple white spaces before comma
		line = line.replaceAll("\\s+,$", ",");
		// multiple white spaces before bang
		line = line.replaceAll("\\s+!$", "!");
		// multiple white spaces before ?
		line = line.replaceAll("\\s+?$", "?");
		// multiple white spaces
		line = line.replaceAll("\\s+", " ");
		// get rid of hyphen ¬ \u00ac in some txt files
		line = line.replaceAll("\u00ac", "");
		return line;
	}

	/**
	 * Overloaded purge() for a list
	 * 
	 * @param content
	 *            input list
	 * @return content output list
	 */
	public static List<String> purge(LinkedList<String> content) {
		String line;
		for (int i = 0; i < content.size(); i++) {
			line = purge(content.get(i));
			content.set(i, line);
		}
		return content;
	}

	/**
	 * Paragraph detector return merged lines of content in one paragraph by the
	 * empty string followed the block or by the end of line and full stop sign
	 * 
	 * @param content
	 * @return
	 * @throws ParseException
	 */
	public static List<String> paragraphDetector(List<String> content)
			throws ParseException {
		List<String> paragraphs = new LinkedList<String>();
		StringBuilder sb = new StringBuilder();
		String endOfParagraphRegex = properties
				.getString("regex.paragraph.separator");
		String emptyLineRegex = properties.getString("regex.emptyline");

		Pattern endOfParagraphPattern;
		try {
			endOfParagraphPattern = Pattern.compile(endOfParagraphRegex);
		} catch (IllegalArgumentException e) {
			LOGGER.error("Sintax error in end of paragraph regex" + e);
			throw new ParseException(endOfParagraphRegex, 0);
		}

		Pattern emptyLinePattern;
		try {
			emptyLinePattern = Pattern.compile(emptyLineRegex);
		} catch (IllegalArgumentException e) {
			LOGGER.error("Sintax error in empty line regex" + e);
			throw new ParseException(endOfParagraphRegex, 0);
		}

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
				// append(" ") - for avoiding concatenation of two edge words
				sb.append(currentLine).append(" ");
				paragraphs.add(purge(sb.toString()));
				sb = new StringBuilder();
			} else {
				sb.append(currentLine).append(" ");
			}
		}
		return paragraphs;
	}

	/**
	 * One particular way to detect sentences on the base of tokens
	 * 
	 * @param tokens
	 * @return
	 * @throws ParseException
	 */
	public static TextComponent breakSentence(List<String> tokens)
			throws ParseException {
		String quotationRegex = properties.getString("regex.quotation");

		Pattern quotationPattern;
		try {
			quotationPattern = Pattern.compile(quotationRegex);
		} catch (IllegalArgumentException e) {
			LOGGER.error("Sintax error in quotation regex" + e);
			throw new ParseException(quotationRegex, 0);
		}

		Matcher quotationMatcher;
		Sentence sentence;
		List<Sentence> sentences = new LinkedList<Sentence>();
		List<Token> phrase = new LinkedList<Token>();

		for (String element : tokens) {
			if (element != null) {
				quotationMatcher = quotationPattern.matcher(element);
				if (quotationMatcher.matches()) {
					if (phrase.size() > 0) {
						sentence = Sentence.create(phrase);
						sentences.add(sentence);
					}
					phrase = new LinkedList<Token>();
					Token theToken = new Token(element);
					theToken.setType(Token.Type.QUOTATION_MARK);
					phrase.add(theToken);
					sentence = Sentence.create(phrase);
					sentence.setType(Sentence.Type.QUOTATION);
					sentences.add(sentence);
					phrase = new LinkedList<Token>();
					continue;
				}

				if (!element.equals(System.lineSeparator())) {
					phrase.add(new Token(element));
				}

				if (element.equals(System.lineSeparator())) {
					if (phrase.size() > 0) {
						sentence = Sentence.create(phrase);
						sentence.setType(Sentence.Type.HEADER);
						sentences.add(sentence);
					}
					phrase = new LinkedList<Token>();
				} else if (element.equals(".")) {
					if (phrase.size() > 0) {
						sentence = Sentence.create(phrase);
						// actually it is not always declarative
						sentence.setType(Sentence.Type.DECLARATIVE);
						sentences.add(sentence);
					}
					phrase = new LinkedList<Token>();
				} else if (element.equals("?")) {
					if (phrase.size() > 0) {
						sentence = Sentence.create(phrase);
						sentence.setType(Sentence.Type.INTERROGATIVE);
						sentences.add(sentence);
					}
					phrase = new LinkedList<Token>();
				} else if (element.equals("!")) {
					if (phrase.size() > 0) {
						sentence = Sentence.create(phrase);
						sentence.setType(Sentence.Type.EXCLAMATORY);
						sentences.add(sentence);
					}
					phrase = new LinkedList<Token>();
				}
			}
		}
		return sentences;
	}
//
//	public static List<String> breakSentence(String txtLine) {
//		return null;
//	}
//
	/**
	 * Parse sentence and tokens
	 * 
	 * @param txtLine
	 *            string line
	 * @return paragraph list of Sentences
	 * @throws ParseException
	 */
	public static List<TextComponent> tokenizer(String txtLine)
			throws ParseException {
		return breakSentence(tokenize(txtLine));
	}

	/**
	 * Parse tokens
	 * 
	 * @param textLine
	 * @return list of String
	 * @throws ParseException
	 */
	public static List<String> tokenize(String textLine) throws ParseException {
		String tokenRegex = properties.getString("regex.token");
		List<String> tokens = new LinkedList<String>();

		Pattern tokenPattern;
		try {
			tokenPattern = Pattern.compile(tokenRegex);
		} catch (IllegalArgumentException e) {
			LOGGER.error("Sintax error in token regex" + e);
			throw new ParseException(tokenRegex, 0);
		}

		Matcher tokenMatcher = tokenPattern.matcher(textLine);
		while (tokenMatcher.find()) {
			tokens.add(tokenMatcher.group());
		}
		tokens.add(System.lineSeparator());

		return tokens;
	}

	/**
	 * Create TextDocument from the file
	 * 
	 * @param file
	 * @return
	 * @throws ParseException
	 */
	public static TextDocument parse(File file) throws ParseException {
		Paragraph paragraph;
		List<Paragraph> paragraphs = new LinkedList<Paragraph>();
		TextReader doc = new TextReader(file);
		if (doc == null || doc.getContent().size() == 0) {
			return TextDocument.create(paragraphs);
		}
		LinkedList<String> content = (LinkedList<String>) Processor
				.purge(new LinkedList<String>(doc.getContent()));
		LinkedList<String> rawParagraphs = (LinkedList<String>) Processor
				.paragraphDetector(content);
		for (String element : rawParagraphs) {
			List<Sentence> sentences = Processor.tokenizer(element);
			paragraph = Paragraph.create(sentences);
			paragraphs.add(paragraph);
		}
		return TextDocument.create(paragraphs);
	}

	/**
	 * Print every sentence of the document in a new line, with empty line
	 * between paragraphs
	 * 
	 * @param document
	 * @return string
	 */
	public static String printText(TextComponent document) {
		return new StringBuilder().append(document.toOriginalString()).toString();
	}

	/**
	 * Print document in XML style
	 * 
	 * @param document
	 * @return string
	 */
	public static String printXML(TextComponent document) {
		StringBuilder sb = new StringBuilder();
		//String name = document.getName().toString();
		String name = "DEFAULT";
		if(document.getName()!= null){
				name = document.getName().toString();
		}
		String type = "DEFAULT";
		if(document.getType()!= null){
			type = document.getType().toString();
		}
		sb.append("<").append(name).append(" type=").append(type).append(">\n");
		sb.append(document.printXML());
		sb.append("</").append(name).append(">");
		return sb.toString();
	}
	

	public static void assignTokenAttribute(TextComponent document)
			throws ParseException {
		String numericRegex = properties.getString("regex.token.numeric");
		String wordRegex = properties.getString("regex.token.word");
		String punctuationRegex = properties
				.getString("regex.token.punctuation");

		Pattern numericPattern;
		Pattern wordPattern;
		Pattern punctuationPattern;
		try {
			wordPattern = Pattern.compile(wordRegex);
			numericPattern = Pattern.compile(numericRegex);
			punctuationPattern = Pattern.compile(punctuationRegex);
		} catch (IllegalArgumentException e) {
			LOGGER.error("Sintax error in token attribute regex" + e);
			throw new ParseException(numericRegex, 0);
		}
		String value;
		Matcher numericMatcher;
		Matcher wordMatcher;
		Matcher punctuationMatcher;
		for (Paragraph paragraph : document.getParagraphs()) {
			for (Sentence sentence : paragraph.getSentences()) {
				for (Token token : sentence.getTokens()) {
					value = token.getValue();
					numericMatcher = numericPattern.matcher(value);
					wordMatcher = wordPattern.matcher(value);
					punctuationMatcher = punctuationPattern.matcher(value);
					if (numericMatcher.matches()) {
						token.setType(Token.Type.NUMERIC);
					} else if (wordMatcher.matches()) {
						token.setType(Token.Type.WORD);
					} else if (punctuationMatcher.matches()) {
						token.setType(Token.Type.PUNCTUATION);
					}
				}
			}
		}
	}
}
