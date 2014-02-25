package com.epam.koryagin.wp;

import java.io.File;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.epam.koryagin.wp.txt.Processor;
import com.epam.koryagin.wp.txt.TextDocument;

public class Runner {
	private static final Logger LOGGER = Logger.getLogger(Runner.class);
	public static ResourceBundle properties = ResourceBundle
			.getBundle("com.epam.koryagin.wp.resources.regex");

	/**
	 * 
	 * @param args - file name with path
	 */
	public static void main(String[] args) {
		File file = null;
		String fileNameRegex = properties.getString("regex.textfile");
		Pattern fileNamePattern = Pattern.compile(fileNameRegex);
		Matcher fileNameMatcher;
		if (args.length == 1) {
			fileNameMatcher = fileNamePattern.matcher(args[0]);
			if (fileNameMatcher.matches()) {
				file = new File(args[0]);
			}
		} else if (args.length > 1) {
			LOGGER.info("Too much parameters, trying to run default txt file");
			file = new File("h:\\JAVALAB\\wordprocessor\\sample_doc.txt");
		} else {
			LOGGER.info("No parameters, trying to run default txt file");
			file = new File("h:\\JAVALAB\\wordprocessor\\sample_doc.txt");
		}

		TextDocument document = Processor.parser(file);
		if (document == null || document.getParagraphs().size() == 0) {
			LOGGER.error("Failed to get document");
		} else {
			LOGGER.info(Processor.printText(document));
			LOGGER.info(Processor.printXML(document));
		}
	}
}
