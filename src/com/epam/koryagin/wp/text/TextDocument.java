package com.epam.koryagin.wp.txt;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Top hierarchy class of a textual entity consist of a list of paragraphs
 * 
 * @author Alexandr Koryagin
 * 
 */
public class TextDocument implements Serializable, Comparable<TextDocument> {
	private static final long serialVersionUID = -4353199310763248838L;

	/**
	 * Static constructor
	 * 
	 * @param paragraphs
	 * @return TextDocument instance
	 */
	public static TextDocument create(List<Paragraph> paragraphs) {
		return new TextDocument(paragraphs);
	}

	private List<Paragraph> paragraphs;

	/**
	 * Default constructor
	 */
	private TextDocument() {
		this.paragraphs = new LinkedList<Paragraph>();
	}

	/**
	 * Constructor with a parameter
	 * 
	 * @param paragraphs
	 */
	private TextDocument(List<Paragraph> paragraphs) {
		this.paragraphs = paragraphs;
	}

	public List<Paragraph> getParagraphs() {
		return paragraphs;
	}

	public void setParagraphs(List<Paragraph> paragraphs) {
		this.paragraphs = paragraphs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((paragraphs == null) ? 0 : paragraphs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TextDocument other = (TextDocument) obj;
		if (paragraphs == null) {
			if (other.paragraphs != null)
				return false;
		} else if (!paragraphs.equals(other.paragraphs))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("TextDocument [ ").append(paragraphs.size())
				.append(" - paragraphs ]\n");
		sb.append(paragraphs.toString());
		return sb.toString();
	}

	@Override
	public int compareTo(TextDocument o) {
		return (this.hashCode() < o.hashCode()) ? -1 : ((this.hashCode() == o
				.hashCode()) ? 0 : 1);
	}

}
