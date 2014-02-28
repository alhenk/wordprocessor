package com.epam.koryagin.wp.text;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Paragraph consist of a list of sentences
 * 
 * @author Alexandr Koryagin
 * 
 */
public class Paragraph implements Serializable, Comparable<Paragraph> {
	private static final long serialVersionUID = 5886337702405072061L;

	public static Paragraph create(List<Sentence> sentences) {
		return new Paragraph(sentences);
	}

	private List<Sentence> sentences;

	/**
	 * Vague idea - default constructor should exist
	 */
	public Paragraph() {
		this.sentences = new LinkedList<Sentence>();
	}

	private Paragraph(List<Sentence> sentences) {
		this.setSentences(sentences);
	}

	public List<Sentence> getSentences() {
		return sentences;
	}

	public void setSentences(List<Sentence> sentences) {
		this.sentences = sentences;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sentences == null) ? 0 : sentences.hashCode());
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
		Paragraph other = (Paragraph) obj;
		if (sentences == null) {
			if (other.sentences != null)
				return false;
		} else if (!sentences.equals(other.sentences))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Paragraph [ ").append(sentences.size())
				.append(" - sentenses]\n");
		sb.append(sentences.toString());
		return sb.toString();
	}

	@Override
	public int compareTo(Paragraph o) {
		return (this.hashCode() < o.hashCode()) ? -1 : ((this.hashCode() == o
				.hashCode()) ? 0 : 1);
	}
}
