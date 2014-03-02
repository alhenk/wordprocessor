package com.epam.koryagin.wp.components;

import com.epam.koryagin.wp.components.text.DefaultType;
/**
 * Utility class for printing Composite Text
 * 
 * @author Alexandr Koryagin
 */
public final class TextComponentPrinter {

	private TextComponentPrinter(){
	}
	
	/**
	 * Print every sentence of the document in a new line, with empty line
	 * between paragraphs
	 * 
	 * @param document
	 * @return string
	 */
	public static String printText(TextComponent document) {
		return new StringBuilder().append(document.toOriginalString())
				.toString();
	}

	/**
	 * Print document in XML style
	 * 
	 * @param document
	 * @return string
	 */
	public static String printXML(TextComponent document) {
		StringBuilder sb = new StringBuilder();
		String name = DefaultType.DEFAULT.toString();
		if (document.getName() != null) {
			name = document.getName().toString();
		}
		String type = DefaultType.DEFAULT.toString();
		if (document.getType() != null) {
			type = document.getType().toString();
		}
		sb.append("<").append(name).append(" type=\"").append(type)
				.append("\">\n");
		sb.append(document.printXML());
		sb.append("</").append(name).append(">");
		return sb.toString();
	}
}
