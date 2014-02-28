package com.epam.koryagin.wp.components.text;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.epam.koryagin.wp.components.CompositeIterator;
import com.epam.koryagin.wp.components.TextComponent;

/**
 * Paragraph consist of a list of sentences
 * 
 * @author Alexandr Koryagin
 * 
 */
public class Paragraph extends TextComponent implements Serializable,
		Comparable<Paragraph> {
	private static final long serialVersionUID = 5886337702405072061L;
	private static final String DEFAULT_TYPE = "PARAGRAPH";
	// composite node sentences
	private List<TextComponent> sentences;
	private String type;
	private Iterator<?> iterator = null;

	/**
	 * Default constructor
	 */
	public Paragraph() {
		this.sentences = new LinkedList<TextComponent>();
		this.type = DEFAULT_TYPE;
	}

	private Paragraph(List<TextComponent> sentences) {
		this.sentences = sentences;
	}

	/**
	 * Static constructor (factory method)
	 */
	public static Paragraph create(List<TextComponent> sentences) {
		Paragraph paragraph = new Paragraph(sentences);
		paragraph.type = DEFAULT_TYPE;
		return paragraph;
	}

	public static Paragraph create(List<TextComponent> sentences, String type) {
		return new Paragraph(sentences);
	}

	@Override
	public void add(TextComponent sentence) {
		sentences.add(sentence);
	}

	@Override
	public int compareTo(Paragraph o) {
		return (this.hashCode() < o.hashCode()) ? -1 : ((this.hashCode() == o
				.hashCode()) ? 0 : 1);
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
	public TextComponent getComponent(int index) {
		return (TextComponent) sentences.get(index);
	}

	public List<TextComponent> getSentences() {
		return (List<TextComponent>) Collections
				.unmodifiableCollection(sentences);
	}

	@Override
	public String getType() {
		return type;
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
	public void remove(TextComponent sentence) {
		sentence.remove(sentence);
	}

	public void setSentences(List<TextComponent> sentences) {
		this.sentences = sentences;
	}

	@Override
	public String toOriginalString() {
		StringBuilder sb = new StringBuilder();
		Iterator<TextComponent> iterator = sentences.iterator();
		while (iterator.hasNext()) {
			TextComponent component = (TextComponent) iterator.next();
			sb.append(component.toOriginalString());
		}
		return sb.toString();
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
	public Iterator<?> createIterator() {
		if (iterator == null) {
			iterator = new CompositeIterator(sentences.iterator());
		}
		return iterator;
	}
}
