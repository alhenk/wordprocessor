package com.epam.koryagin.wp;

import java.util.Set;
import java.util.TreeSet;

import com.epam.koryagin.wp.text.Paragraph;
import com.epam.koryagin.wp.text.Sentence;
import com.epam.koryagin.wp.text.TextDocument;
import com.epam.koryagin.wp.text.Token;

public final class TaskLogic {

	private TaskLogic() {
	}

	public static Set<String> pickupUniqWords(TextDocument document) {
		Set<String> words = new TreeSet<String>();
		for (Paragraph paragraph : document.getParagraphs()) {
			for (Sentence sentence : paragraph.getSentences()) {
				for (Token token : sentence.getTokens()) {
					String value = token.getValue();
					if (token.getType() == Token.Type.WORD) {
						words.add(value);
					}
				}
			}
		}
		return words;
	}

}
