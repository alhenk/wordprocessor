package com.epam.koryagin.wp;

import java.io.File;
import org.apache.log4j.Logger;
import com.epam.koryagin.wp.txt.Processor;
import com.epam.koryagin.wp.txt.TextDocument;


public class Runner {
	private static final Logger LOGGER = Logger.getLogger(Runner.class);

	public static void main(String[] args) {
		File file = new File("h:\\JAVALAB\\wordprocessor\\sample_doc.txt");

		TextDocument document = Processor.parser(file);
		//LOGGER.info(Processor.printText(document));
		LOGGER.info(Processor.printXML(document));
	}
}
