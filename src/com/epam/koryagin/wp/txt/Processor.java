package com.epam.koryagin.wp.txt;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.koryagin.wp.TextReader;

/**
 * Utility class for text processing purge(String) purge(LinkedList<String>)
 * paragraphDetector(List<String>) tokenize(String txtLine)
 * 
 * @author Alexandr Koryagin
 * @date 20140225
 */
public final class Processor {
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
		// get rid of hyphen � \u00ac in some txt files
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
	 */
	public static List<String> paragraphDetector(List<String> content) {
		List<String> paragraphs = new LinkedList<String>();
		StringBuilder sb = new StringBuilder();
		String endOfParagraphRegex = properties
				.getString("regex.paragraph.separator");
		String emptyLineRegex = properties.getString("regex.emptyline");
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

	public static List<Sentence> breakSentence(List<String> tokens) {
		String quotationRegex = properties.getString("regex.quotation");
		Pattern quotationPattern = Pattern.compile(quotationRegex);
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


	public static List<String> breakSentence(String txtLine) {
		return null;
	}

	/**
	 * Parse sentence and tokens
	 * 
	 * @param txtLine
	 *            string line
	 * @return paragraph list of Sentences
	 */
	public static List<Sentence> tokenizer(String txtLine) {
		return breakSentence(tokenize(txtLine));
	}

	/**
	 * Parse tokens
	 * 
	 * @param textLine
	 * @return list of String
	 */
	public static List<String> tokenize(String textLine) {
		String tokenRegex = properties.getString("regex.token");
		List<String> tokens = new LinkedList<String>();
		Pattern tokenPattern = Pattern.compile(tokenRegex);
		Matcher tokenMatcher = tokenPattern.matcher(textLine);
		while (tokenMatcher.find()) {
			tokens.add(tokenMatcher.group());
		}
		tokens.add(System.lineSeparator());

		return tokens;
	}

	public static TextDocument parser(File file) {
		Paragraph paragraph;
		List<Paragraph> paragraphs = new LinkedList<Paragraph>();
		TextReader doc = new TextReader(file);
		if(doc == null || doc.getContent().size() == 0){
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
	
	public static String printText(TextDocument document) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Paragraph paragraph : document.getParagraphs()) {
			for (Sentence sentence : paragraph.getSentences()) {
				for (Token token : sentence.getTokens()) {
					stringBuilder.append(token.getValue()).append(" ");
				}
				stringBuilder.append("\n");
			}
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}
	
	public static String printXML(TextDocument document) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Paragraph paragraph : document.getParagraphs()) {
			stringBuilder.append("<p>\n");
			for (Sentence sentence : paragraph.getSentences()) {
				stringBuilder.append("\t<sentence type=")
						.append(sentence.getType()).append(">\n");
				for (Token token : sentence.getTokens()) {
					stringBuilder.append("\t\t<token type=")
							.append(token.getType()).append(">\n\t\t\t");
					stringBuilder.append(token.getValue()).append(" ");
					stringBuilder.append("\n\t\t</token>\n");
				}
				stringBuilder.append("\t</sentence>\n");
			}
			stringBuilder.append("</p>\n");
		}
		return stringBuilder.toString();
	}
}
