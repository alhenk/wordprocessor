package com.epam.koryagin.wp.components;

import java.util.Iterator;

public class NullIterator implements Iterator<TextComponent>{
	public TextComponent next() {
		return null;
	}

	public boolean hasNext() {
		return false;
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}
}
