package com.epam.koryagin.wp;

import java.util.Set;
import java.util.TreeSet;

import com.epam.koryagin.wp.components.text.CompositeText;

public final class Logic {

	private Logic() {
	}

	public static Set<String> pickupUniqWords(CompositeText document) {
		Set<String> words = new TreeSet<String>();
//		for (TextComponent paragraph : document.getParagraphs()) {
//			for (Sentence sentence : paragraph.getSentences()) {
//				for (Token token : sentence.getTokens()) {
//					String value = token.getValue();
//					if (token.getType() == Token.Type.WORD) {
//						words.add(value);
//					}
//				}
//			}
//		}
		return words;
	}

}
