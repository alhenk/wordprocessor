package com.epam.koryagin.wp.components.text;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import com.epam.koryagin.wp.components.TextComponent;
import com.epam.koryagin.wp.components.TextComponentType;

public class CompositeText extends TextComponent implements Serializable,
		Comparable<Paragraph> {
	private static final long serialVersionUID = -1751752160132151666L;
	private List<TextComponent> components;
	private TextComponentType type;
	private TextComponentName name;
	private Iterator<?> iterator = null;
	
	
	
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
	
	

	@Override
	public int compareTo(Paragraph o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}


}
