package com.epam.koryagin.wp.components;

import java.util.Iterator;
import java.util.List;

public abstract class TextComponent implements Iterable<TextComponent> {
	public static enum TextComponentName {
		DOCUMENT, PARAGRPAPH, SENTENCE, TOKEN, IMAGE, DEFAULT;
	}

	public abstract int hashCode();

	public abstract boolean equals(Object obj);

	public void add(TextComponent component) {
		throw new UnsupportedOperationException();
	}

	public String getValue() {
		throw new UnsupportedOperationException();
	}
	public List<TextComponent> getComponents(){
		throw new UnsupportedOperationException();
	}

	public TextComponentName getName() {
		throw new UnsupportedOperationException();
	}
	
	public void setName(TextComponentName name) {
		throw new UnsupportedOperationException();
	}

	public TextComponentType getType() {
		throw new UnsupportedOperationException();
	}
	public void setType(TextComponentType type) {
		throw new UnsupportedOperationException();
	}

	public void remove(TextComponent component) {
		throw new UnsupportedOperationException();
	}

	public String toOriginalString() {
		throw new UnsupportedOperationException();
	}
	public String printXML(){
		throw new UnsupportedOperationException();
	}

	public Iterator<?> createIterator() {
		throw new UnsupportedOperationException();
	}
}
