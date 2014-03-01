package com.epam.koryagin.wp.components.text;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.epam.koryagin.wp.components.CompositeIterator;
import com.epam.koryagin.wp.components.TextComponent;
import com.epam.koryagin.wp.components.TextComponentType;

/**
 * Top hierarchy class of a textual entity consist of a list of paragraphs
 * 
 * @author Alexandr Koryagin
 * 
 */
public class TextDocument extends TextComponent implements Serializable,
		Comparable<TextDocument> {
	private static final long serialVersionUID = -4353199310763248838L;
	// composite node paragraphs
	private List<TextComponent> paragraphs;
	private TextComponentName name;
	private Iterator<?> iterator = null;

	/**
	 * Default constructor
	 */
	public TextDocument() {
		this.paragraphs = new LinkedList<TextComponent>();
		this.name = TextComponentName.DOCUMENT;
	}

	/**
	 * Constructor with a parameter
	 */
	private TextDocument(List<TextComponent> paragraphs) {
		this.paragraphs = paragraphs;
	}

	/**
	 * Static constructor (factory method)
	 */
	public static TextDocument create(List<TextComponent> paragraphs) {
		TextDocument textDocument = new TextDocument(paragraphs);
		textDocument.name = TextComponentName.DOCUMENT;
		return textDocument;
	}

	public static TextDocument create(List<TextComponent> paragraphs,
			String type) {
		TextDocument textDocument = new TextDocument(paragraphs);
		textDocument.name = TextComponentName.DOCUMENT;
		return textDocument;
	}

	@Override
	public void add(TextComponent paragraph) {
		paragraphs.add(paragraph);
	}

	@Override
	public int compareTo(TextDocument o) {
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
		TextDocument other = (TextDocument) obj;
		if (paragraphs == null) {
			if (other.paragraphs != null)
				return false;
		} else if (!paragraphs.equals(other.paragraphs))
			return false;
		return true;
	}

	@Override
	public TextComponent getComponent(int index) {
		return (TextComponent) paragraphs.get(index);
	}

	public List<TextComponent> getParagraphs() {
		return (List<TextComponent>) Collections
				.unmodifiableCollection(paragraphs);
	}

	public void setParagraphs(List<TextComponent> paragraphs) {
		this.paragraphs = paragraphs;
	}

	@Override
	public TextComponentType getType() {
		throw new UnsupportedOperationException();
	}
	public TextComponentName getName(){
		return name;
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
	public void remove(TextComponent paragraph) {
		paragraphs.remove(paragraph);
	}

	@Override
	public String toOriginalString() {
		StringBuilder sb = new StringBuilder();
		Iterator<TextComponent> iterator = paragraphs.iterator();
		while (iterator.hasNext()) {
			TextComponent component = (TextComponent) iterator.next();
			sb.append(component.toOriginalString());
		}
		return sb.toString();
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
	public Iterator<?> createIterator() {
		if (iterator == null) {
			iterator =  new CompositeIterator(paragraphs.iterator());
		}
		return iterator;
	}

}
