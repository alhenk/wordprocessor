package com.epam.koryagin.wp;

import com.epam.koryagin.wp.components.text.Paragraph;
import com.epam.koryagin.wp.components.text.Sentence;
import com.epam.koryagin.wp.components.text.TextDocument;
import com.epam.koryagin.wp.components.text.Token;

public class TestDrive {
	public static void main(String[] args) {
		TextDocument document = new TextDocument();
		Paragraph paragraph = new Paragraph();
		Sentence sentence = new Sentence();
		//Token token = new Token("A");
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
		document.add(paragraph);
		
//		TaskLogic task = new TaskLogic(document);
//		System.out.println(task.print());
		
		System.out.println(document.toOriginalString());
		
	}
}
