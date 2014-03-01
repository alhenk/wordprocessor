package com.epam.koryagin.wp;

import com.epam.koryagin.wp.components.TextComponent.TextComponentName;
import com.epam.koryagin.wp.components.text.CompositeText;
import com.epam.koryagin.wp.components.text.Token;

public class TestDrive {
	public static void main(String[] args) {
		CompositeText document = CompositeText.create(TextComponentName.DOCUMENT);
		CompositeText paragraph = CompositeText.create(TextComponentName.PARAGRPAPH);
		CompositeText sentence = CompositeText.create(TextComponentName.SENTENCE);

		sentence.add(new Token("A"));
		sentence.add(new Token(" "));
		sentence.add(new Token("f"));
		sentence.add(new Token("o"));
		sentence.add(new Token("x"));
		sentence.add(new Token(" "));
		sentence.add(new Token("i"));
		sentence.add(new Token("s"));
		sentence.add(new Token(" "));
		sentence.add(new Token("i"));
		sentence.add(new Token("n"));
		sentence.add(new Token(" "));
		sentence.add(new Token("t"));
		sentence.add(new Token("h"));
		sentence.add(new Token("e"));
		sentence.add(new Token(" "));
		sentence.add(new Token("b"));
		sentence.add(new Token("o"));
		sentence.add(new Token("x"));
		sentence.add(new Token("."));
		
		paragraph.add(sentence);
		paragraph.add(sentence);
		document.add(paragraph);
		
		
		System.out.println(document.toOriginalString());
		
//		StringBuilder sb = new StringBuilder();
//		Iterator<TextComponent> tokens = sentence.getComponents().iterator();
//		while (tokens.hasNext()) {
//			sb.append(tokens.next().getComponent(0).toString());
//		}
//		System.out.println(sb.toString());
	}
}
