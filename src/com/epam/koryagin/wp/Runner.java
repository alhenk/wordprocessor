package com.epam.koryagin.wp;

import java.io.IOException;
import java.text.ParseException;

public class Runner {
//	private static final Logger LOGGER = Logger.getLogger(Runner.class);
//	public static ResourceBundle properties = ResourceBundle
//			.getBundle("com.epam.koryagin.wp.resources.regex");

	/**
	 * Read filepath from arguments of main()
	 * @param args
	 * @return
	 * @throws ParseException
	 */
//	private static File getFile(String... args) throws ParseException {
//		File file = null;
//		//File file = new File("h:\\JAVALAB\\wordprocessor\\sample_txt.txt");
//		String fileNameRegex = properties.getString("regex.textfile");
//
//		Pattern fileNamePattern;
//		try {
//			fileNamePattern = Pattern.compile(fileNameRegex);
//		} catch (IllegalArgumentException e) {
//			LOGGER.error("Sintax error in regex" + e);
//			throw new ParseException(fileNameRegex, 0);
//		}
//
//		Matcher fileNameMatcher;
//		if (args.length == 1) {
//			fileNameMatcher = fileNamePattern.matcher(args[0]);
//			if (fileNameMatcher.matches()) {
//				file = new File(args[0]);
//			}
//		} else if (args.length > 1) {
//			LOGGER.info("Too much parameters, trying to run default txt file");
//			file = new File("h:\\JAVALAB\\wordprocessor\\sample_doc.txt");
//		} else {
//			LOGGER.info("No parameters, trying to run default txt file");
//			file = new File("h:\\JAVALAB\\wordprocessor\\sample_doc.txt");
//		}
//		return file;
//	}

	/**
	 * 
	 * @param args
	 *            - file name with path
	 * @throws ParseException
	 * @throws IOException 
	 * @throws Exception
	 */
	public static void main(String[] args) throws ParseException, IOException {

//		File file = getFile(args);
//		TextDocument document =null;// Processor.parse(file);
//		if (document == null || document.getParagraphs().size() == 0) {
//			LOGGER.error("Failed to get document");
//		} else {
//			Processor.assignTokenAttribute(document);
//			// LOGGER.info("\n" + Processor.printText(document));
//			//LOGGER.info("\n" + Processor.printXML(document));
//		}
//		Set<String> words = TaskLogic.pickupUniqWords(document);
//		System.out.println(words);
//		FileWriter fr = new FileWriter("c://text_.txt");
//		fr.write(Processor.printText(document));
//		fr.close();
		
		
		
	}
}
