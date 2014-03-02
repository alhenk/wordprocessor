package com.epam.koryagin.wp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.epam.koryagin.wp.components.TextComponent;
import com.epam.koryagin.wp.components.TextComponent.TextComponentName;
import com.epam.koryagin.wp.components.text.CompositeText;
import com.epam.koryagin.wp.components.text.SentenceType;
import com.epam.koryagin.wp.components.text.TokenType;
import com.epam.koryagin.wp.parser.Parser;

/**
 * Logic for JAVALAB TASK2
 * @author Alexandr Koryagin
 */
public final class TaskLogic {
	private static final Logger LOGGER = Logger.getLogger(Parser.class);
	private TaskLogic() {
	}

	/**
	 * Task 4
	 * Find and print all unique words of a given length
	 * in interrogative sentences
	 * @param document
	 * @param wordlength
	 * @return words - a set of words
	 */
	public static Set<String> findWordsInterrogative(CompositeText document,
			int wordlength) {
		if (wordlength < 0){
			LOGGER.error("Word length parameter must be positive");
			throw new IllegalArgumentException("Word length parameter must be positive");
		}
		Set<String> words = new TreeSet<String>();
		Iterator<?> iterator = document.createIterator();
		while (iterator.hasNext()) {
			TextComponent sentence = (TextComponent) iterator.next();
			if (sentence.getName().equals(TextComponentName.SENTENCE)
					&& sentence.getType().equals(SentenceType.INTERROGATIVE)) {
				for (TextComponent token : sentence) {
					if (token.getType().equals(TokenType.WORD)) {
						String value = token.getValue();
						if (value.length() == wordlength) {
							words.add(value);
						}
					}
				}
			}
		}
		return words;
	}

	/**
	 * Task 2
	 * Print out all sentences in ascending order of words count
	 * @param document
	 * @return
	 */
	public static String sortSentencesInAsceascendingOrderOfWordCount(
			CompositeText document) {
		List<TextComponent> sentences = new ArrayList<TextComponent>();
		Iterator<?> iterator = document.createIterator();
		while (iterator.hasNext()) {
			TextComponent sentence = (TextComponent) iterator.next();
			if (sentence.getName().equals(TextComponentName.SENTENCE)) {
				sentences.add(sentence);
			}
		}
		Collections.sort(sentences, new CompositeText.ComponentSizeComparator());
		StringBuilder sb = new StringBuilder();
		for(TextComponent sentence: sentences){
			sb.append(sentence.toOriginalString()).append("\n");
		}
		return sb.toString();
	}

	/**
	 * Task 0
	 * Find all unique words in the document
	 * @param document
	 * @return
	 */
	public static Set<String> pickupUniqueWords(CompositeText document) {
		Set<String> words = new TreeSet<String>();
		Iterator<?> iterator = document.createIterator();
		while (iterator.hasNext()) {
			TextComponent token = (TextComponent) iterator.next();
			if (token.getName().equals(TextComponentName.TOKEN)) {
				if (token.getType().equals(TokenType.WORD)) {
					String value = token.getValue();
					words.add(value);

				}
			}
		}
		return words;
	}
}
