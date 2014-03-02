package com.epam.koryagin.wp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.epam.koryagin.wp.components.TextComponent;
import com.epam.koryagin.wp.components.TextComponent.TextComponentName;
import com.epam.koryagin.wp.components.text.CompositeText;
import com.epam.koryagin.wp.components.text.SentenceType;
import com.epam.koryagin.wp.components.text.TokenType;

public class TaskLogic {
	CompositeText document;

	public TaskLogic(CompositeText document) {
		this.document = document;
	}

	public String print() {
		Iterator<?> iterator = document.createIterator();
		StringBuilder sb = new StringBuilder();

		while (iterator.hasNext()) {
			TextComponent component = (TextComponent) iterator.next();
			sb.append(component.toOriginalString());
		}
		return sb.toString();
	}

	public static Set<String> findWordsInterrogative(CompositeText document,
			int wordlength) {
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
