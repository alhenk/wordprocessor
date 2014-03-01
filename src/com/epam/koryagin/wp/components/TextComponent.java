package com.epam.koryagin.wp.components;

import java.util.Iterator;

public abstract class TextComponent {
	public static enum TextComponentName {
		DOCUMENT, PARAGRPAPH, SENTENCE, TOKEN, IMAGE;
	}

	public abstract int hashCode();

	public abstract boolean equals(Object obj);

	public void add(TextComponent component) {
		throw new UnsupportedOperationException();
	}

	public TextComponent getComponent(int index) {
		throw new UnsupportedOperationException();
	}

	public TextComponentName getName() {
		throw new UnsupportedOperationException();
	}

	public TextComponentType getType() {
		throw new UnsupportedOperationException();
	}

	public void remove(TextComponent component) {
		throw new UnsupportedOperationException();
	}

	public String toOriginalString() {
		throw new UnsupportedOperationException();
	}

	public Iterator<?> createIterator() {
		throw new UnsupportedOperationException();
	}
}
