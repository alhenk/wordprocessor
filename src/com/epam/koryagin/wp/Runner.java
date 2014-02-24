package com.epam.koryagin.wp;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import com.epam.koryagin.wp.txt.Processor;
import com.epam.koryagin.wp.txt.Sentence;
import com.epam.koryagin.wp.txt.Token;

public class Runner {

	public static void main(String[] args) {
		File file = new File("h:\\JAVALAB\\wordprocessor\\sample_doc.txt");

		TextReader doc = new TextReader(file);
		LinkedList<String> content = (LinkedList<String>) Processor
				.purge(new LinkedList<String>(doc.getContent()));

		System.out.println(content.size());
		LinkedList<String> paragraphs = (LinkedList<String>) Processor
				.paragraphDetector(content);
		System.out.println(paragraphs.size());

		for (String paragraph : paragraphs) {
			List<Sentence> sentence = Processor.tokenize(paragraph);
			System.out.println("<p>");
			for (Sentence element : sentence) {
				System.out.print("\t<sentence type=" + element.getType()
						+ ">\n\t\t");
				for (Token elem : element.getSentence()) {
					System.out.print(elem.getValue() + " ");
				}
				System.out.println("\n\t</sentence>");
			}
			System.out.println("</p>\n");
		}
	}
}
