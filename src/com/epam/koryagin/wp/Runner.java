package com.epam.koryagin.wp;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import com.epam.koryagin.wp.txt.Processor;
import com.epam.koryagin.wp.txt.Sentence;
import com.epam.koryagin.wp.txt.Token;

public class Runner {
	private static final Logger LOGGER = Logger.getLogger(Runner.class);

	public static void main(String[] args) {
		File file = new File("h:\\JAVALAB\\wordprocessor\\sample_doc.txt");

		TextReader doc = new TextReader(file);
		LinkedList<String> content = (LinkedList<String>) Processor
				.purge(new LinkedList<String>(doc.getContent()));

		LinkedList<String> rawParagraphs = (LinkedList<String>) Processor
				.paragraphDetector(content);

		StringBuilder stringBuilder = new StringBuilder("\n");
		for (String paragraph : rawParagraphs) {
			List<Sentence> sentence = Processor.tokenize(paragraph);
			stringBuilder.append("<p>\n");
			for (Sentence element : sentence) {
				stringBuilder.append("\t<sentence type=")
						.append(element.getType()).append(">\n");
				for (Token elem : element.getSentence()) {
					stringBuilder.append("\t\t<token type=")
							.append(elem.getType()).append(">\n\t\t\t");
					stringBuilder.append(elem.getValue());
					stringBuilder.append("\n\t\t</token>\n");
				}
				stringBuilder.append("\t</sentence>\n");
			}
			stringBuilder.append("</p>\n");
		}
		LOGGER.info(stringBuilder.toString());
	}
}
