package com.epam.koryagin.wp;

import java.util.Set;
import java.util.TreeSet;

import com.epam.koryagin.wp.txt.Paragraph;
import com.epam.koryagin.wp.txt.Sentence;
import com.epam.koryagin.wp.txt.TextDocument;
import com.epam.koryagin.wp.txt.Token;

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
