package com.epam.koryagin.wp.components;

import java.util.Iterator;
/**
 * Iterator for "leaf" nodes
 * like Token and Image
 * that have no composite components
 * 
 * @author Alexandr Koryagin
 *
 */
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
