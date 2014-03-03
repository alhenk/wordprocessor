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
	 * @throws IOException 
	 */
	private static File getFile(String... args) throws ParseException, IOException {
		String fileName = "sample_doc.txt";
		//default file
		File file = new FileResource().getFile(fileName);
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
		} else {
			LOGGER.info("No parameters, trying to run default txt file");
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
		if (file == null) {
			LOGGER.error("Missing File Name argument");
			throw new IllegalArgumentException("Missing File Name argument");
		}
		CompositeText document = (CompositeText) Parser.parse(file);
		if (document == null || document.getComponents().size() == 0) {
			LOGGER.error("Failed to get document");
		} else {
			// LOGGER.info("\n" + TextComponentPrinter.printText(document));
			LOGGER.info("\n" + TextComponentPrinter.printXML(document));
		}

		/**
		 * Subtask 1 find maximum number of sentences that have identical words
		 */
		LOGGER.info("SUBTASK 1:");
		LOGGER.info("MaxNumber = " + TaskLogic.subtask1(document));
		/**
		 * Subtask 2 Print out all sentences in ascending order of words count
		 */
		LOGGER.info("SUBTASK 2:");
		LOGGER.info("Sentences in ascending order of words count\n"
				+ TaskLogic
						.sortSentencesInAsceascendingOrderOfWordCount(document));

		/**
		 * Subtask 3 Find a word (a set of words) in a particular sentence that
		 * is not included in all other sentences
		 */
		LOGGER.info("SUBTASK 3:");
		int sentenceIndex = 10;
		LOGGER.info("A set of words in a particular sentence ("+sentenceIndex+") that is not included in all other sentences");
		LOGGER.info(TaskLogic.subtask3(document, sentenceIndex));

		/**
		 * Subtask 4 Find and print all unique words of a given length in
		 * interrogative sentences
		 */
		int wordLength = 3;
		Set<String> words = TaskLogic.findWordsInterrogative(document,
				wordLength);
		LOGGER.info("SUBTASK 4:");
		LOGGER.info("The unique words of a given length = " + wordLength
				+ " : " + words);

		/**
		 * Subtask 6 Find and print all unique words in the document in alphabetic order
		 */
		String uniqueWords = TaskLogic.pickupUniqueWords(document);
		LOGGER.info("SUBTASK 6:");
		LOGGER.info("All unique words in the document in alphabetic order");
		LOGGER.info(uniqueWords);

		TaskLogic.subtask1(document);
	}
}
