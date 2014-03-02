package com.epam.koryagin.wp.components;

import java.util.Iterator;
import java.util.Stack;

import com.epam.koryagin.wp.components.text.CompositeText;

public class CompositeIterator implements Iterator<Object> {
	Stack<Iterator<?>> stack = new Stack<Iterator<?>>();

	public CompositeIterator(Iterator<TextComponent> iterator) {
		stack.push(iterator);
	}

	@Override
	public boolean hasNext() {
		if (stack.empty()) {
			return false;
		} else {
			Iterator<?> iterator = (Iterator<?>) stack.peek();
			if (!iterator.hasNext()) {
				stack.pop();
				return hasNext();//recursion
			} else {
				return true;
			}
		}
	}

	@Override
	public Object next() {
		if (hasNext()) {
			Iterator<?> iterator = (Iterator<?>) stack.peek();
			TextComponent component = (TextComponent) iterator.next();
			if (component instanceof CompositeText) {
				stack.push(component.createIterator());
			}
			return component;
		} else {
			return null;
		}
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
