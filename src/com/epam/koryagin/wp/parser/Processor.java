package com.epam.koryagin.wp.parser;

import java.io.File;
import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.epam.koryagin.wp.TextReader;
import com.epam.koryagin.wp.components.TextComponent;
import com.epam.koryagin.wp.components.TextComponent.TextComponentName;
import com.epam.koryagin.wp.components.text.CompositeText;
import com.epam.koryagin.wp.components.text.DefaultType;
import com.epam.koryagin.wp.components.text.ParagraphType;
import com.epam.koryagin.wp.components.text.SentenceType;
import com.epam.koryagin.wp.components.text.Token;
import com.epam.koryagin.wp.components.text.TokenType;

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
	public static TextComponent breakSentence(List<Token> tokens)
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
		TextComponent sentence;
		TextComponent paragrpaph = CompositeText
				.create(TextComponentName.PARAGRPAPH);
		List<TextComponent> phrase = new LinkedList<TextComponent>();

		for (Token token : tokens) {
			if (token != null) {
				quotationMatcher = quotationPattern.matcher(token.getValue());
				if (quotationMatcher.matches()) {
					if (phrase.size() > 0) {
						sentence = CompositeText.create(phrase,
								TextComponentName.SENTENCE);
						paragrpaph.add(sentence);
					}
					phrase = new LinkedList<TextComponent>();
					token.setType(TokenType.PUNCTUATION);
					phrase.add(token);
					sentence = CompositeText.create(phrase,
							TextComponentName.SENTENCE);
					sentence.setType(SentenceType.DEFAULT);
					paragrpaph.add(sentence);
					phrase = new LinkedList<TextComponent>();
					continue;
				}

				if (!token.getValue().equals(System.lineSeparator())) {
					phrase.add(token);
				}

				if (token.getValue().equals(System.lineSeparator())) {
					if (phrase.size() > 0) {
						sentence = CompositeText.create(phrase,
								TextComponentName.SENTENCE);
						paragrpaph.setType(ParagraphType.HEADER);
						paragrpaph.add(sentence);
					}
					phrase = new LinkedList<TextComponent>();
				} else if (token.getValue().equals(".")) {
					if (phrase.size() > 0) {
						sentence = CompositeText.create(phrase,
								TextComponentName.SENTENCE);
						// actually it is not always declarative
						sentence.setType(SentenceType.DECLARATIVE);
						paragrpaph.add(sentence);
					}
					phrase = new LinkedList<TextComponent>();
				} else if (token.getValue().equals("?")) {
					if (phrase.size() > 0) {
						sentence = CompositeText.create(phrase,
								TextComponentName.SENTENCE);
						sentence.setType(SentenceType.INTERROGATIVE);
						paragrpaph.add(sentence);
					}
					phrase = new LinkedList<TextComponent>();
				} else if (token.getValue().equals("!")) {
					if (phrase.size() > 0) {
						sentence = CompositeText.create(phrase,
								TextComponentName.SENTENCE);
						sentence.setType(SentenceType.EXCLAMATORY);
						paragrpaph.add(sentence);
					}
					phrase = new LinkedList<TextComponent>();
				}
			}
		}
		return paragrpaph;
	}

	// public static List<String> breakSentence(String txtLine) {
	// return null;
	// }

	/**
	 * Parse sentence and tokens
	 * 
	 * @param txtLine
	 *            string line
	 * @return paragraph list of Sentences
	 * @throws ParseException
	 */
	public static TextComponent tokenizer(String txtLine) throws ParseException {
		return breakSentence(tokenize(txtLine));
	}

	/**
	 * Parse tokens
	 * 
	 * @param textLine
	 * @return list of String
	 * @throws ParseException
	 */
	public static List<Token> tokenize(String textLine) throws ParseException {
		String tokenRegex = properties.getString("regex.token");
		List<Token> tokens = new LinkedList<Token>();

		Pattern tokenPattern;
		try {
			tokenPattern = Pattern.compile(tokenRegex);
		} catch (IllegalArgumentException e) {
			LOGGER.error("Sintax error in token regex" + e);
			throw new ParseException(tokenRegex, 0);
		}

		Matcher tokenMatcher = tokenPattern.matcher(textLine);
		while (tokenMatcher.find()) {
			tokens.add(new Token(tokenMatcher.group()));
		}
		tokens.add(new Token(System.lineSeparator()));
		return tokens;
	}

	/**
	 * Create TextDocument from the file
	 * 
	 * @param file
	 * @return
	 * @throws ParseException
	 */
	public static TextComponent parse(File file) throws ParseException {
		List<TextComponent> paragraphs = new LinkedList<TextComponent>();

		TextReader doc = new TextReader(file);
		if (doc == null || doc.getContent().size() == 0) {
			return CompositeText.create(paragraphs);
		}
		LinkedList<String> content = (LinkedList<String>) purge(new LinkedList<String>(
				doc.getContent()));
		LinkedList<String> rawParagraphs = (LinkedList<String>) paragraphDetector(content);
		for (String rawParagraph : rawParagraphs) {
			TextComponent paragraph = tokenizer(rawParagraph);
			paragraphs.add(paragraph);
		}
		TextComponent document = CompositeText.create(paragraphs,TextComponentName.DOCUMENT);
		assignTokenAttribute(document);
		return document;
	}

	/**
	 * Print every sentence of the document in a new line, with empty line
	 * between paragraphs
	 * 
	 * @param document
	 * @return string
	 */
	public static String printText(TextComponent document) {
		return new StringBuilder().append(document.toOriginalString())
				.toString();
	}

	/**
	 * Print document in XML style
	 * 
	 * @param document
	 * @return string
	 */
	public static String printXML(TextComponent document) {
		StringBuilder sb = new StringBuilder();
		String name = DefaultType.DEFAULT.toString();
		if (document.getName() != null) {
			name = document.getName().toString();
		}
		String type = DefaultType.DEFAULT.toString();
		if (document.getType() != null) {
			type = document.getType().toString();
		}
		sb.append("<").append(name).append(" type=\"").append(type)
				.append("\">\n");
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
		
		
		Iterator<?> iterator= document.createIterator();
		while(iterator.hasNext()){
			TextComponent token = (TextComponent)iterator.next();
			if(token.getName().equals(TextComponentName.TOKEN)){
				value = token.getValue();
				numericMatcher = numericPattern.matcher(value);
				wordMatcher = wordPattern.matcher(value);
				punctuationMatcher = punctuationPattern.matcher(value);
				if (numericMatcher.matches()) {
					token.setType(TokenType.NUMERIC);
				} else if (wordMatcher.matches()) {
					token.setType(TokenType.WORD);
				} else if (punctuationMatcher.matches()) {
					token.setType(TokenType.PUNCTUATION);
				}
			}
		}
	}
}
