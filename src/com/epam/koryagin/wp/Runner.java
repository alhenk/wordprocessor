package com.epam.koryagin.wp;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import com.epam.koryagin.wp.components.TextComponentPrinter;
import com.epam.koryagin.wp.components.text.CompositeText;
import com.epam.koryagin.wp.parser.Parser;

public class Runner {
	private static final Logger LOGGER = Logger.getLogger(Runner.class);
	public static ResourceBundle properties = ResourceBundle
			.getBundle("com.epam.koryagin.wp.resources.regex");

	/**
	 * Read filepath from arguments of main()
	 * 
	 * @param args
	 * @return
	 * @throws ParseException
	 */
	private static File getFile(String... args) throws ParseException {
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
			file = null;
		} else {
			LOGGER.info("No parameters, trying to run default txt file");
			file = null;
		}
		return file;
	}

	/**
	 * 
	 * @param args
	 *            - file name with path
	 * @throws ParseException
	 * @throws IOException
	 * @throws Exception
	 */
	public static void main(String[] args) throws ParseException, IOException {

		File file = getFile(args);
		if (file == null){
			LOGGER.error("Missing File Name argument");
			throw new IllegalArgumentException("Missing File Name argument");
		}
		CompositeText document = (CompositeText) Parser.parse(file);
		if (document == null || document.getComponents().size() == 0) {
			LOGGER.error("Failed to get document");
		} else {
			LOGGER.info("\n" + TextComponentPrinter.printText(document));
			LOGGER.info("\n" + TextComponentPrinter.printXML(document));
		}

		Set<String> words;
		// Task 0
		// Find all unique words in the document
		words = TaskLogic.pickupUniqueWords(document);
		LOGGER.info("TASK 0:");
		LOGGER.info("All unique words (" + words.size() + ") in the document ");
		LOGGER.info(words);
		/**
		 *  Subtask 2
		 *  Print out all sentences in ascending order of words count
		 */
		LOGGER.info("TASK 2:");
		LOGGER.info(TaskLogic.sortSentencesInAsceascendingOrderOfWordCount(document));

		/** 
		 * Subtask 3
		 * Find a word (a set of words) in a particular sentence
		 * that is not included in all other sentences
		 */
		LOGGER.info("TASK 3:");
		LOGGER.info("A set of words in a particular sentence that is not included in all other sentences");
		LOGGER.info(TaskLogic.subtask3(document));
		
		/** 
		 * Subtask 4
		 * Find and print all unique words of a given length
		 * in interrogative sentences
		 */
		int wordLength = 3;
		words = TaskLogic.findWordsInterrogative(document, wordLength);
		LOGGER.info("TASK 4:");
		LOGGER.info("The unique words of a given length = " + wordLength + " : "
				+ words);
		

	}
}
