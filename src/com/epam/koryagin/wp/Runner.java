package com.epam.koryagin.wp;

import java.io.File;
import java.text.ParseException;
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
	 * @param args
	 *            - file name with path
	 * @throws ParseException 
	 * @throws Exception
	 */
	public static void main(String[] args) throws ParseException  {
		File file = null;
		String fileNameRegex = properties.getString("regex.textfile");

		Pattern fileNamePattern;
		try {
			fileNamePattern = Pattern.compile(fileNameRegex);
		} catch (IllegalArgumentException e) {
			LOGGER.error("Sintax error in regex" + e);
			throw new ParseException(fileNameRegex, 0);
		}

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

		TextDocument document = Processor.parse(file);
		if (document == null || document.getParagraphs().size() == 0) {
			LOGGER.error("Failed to get document");
		} else {
			// LOGGER.info("\n"+Processor.printText(document));
			LOGGER.info("\n" + Processor.printXML(document));
		}
	}
}
