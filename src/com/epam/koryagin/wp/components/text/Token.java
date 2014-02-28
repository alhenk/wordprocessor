package com.epam.koryagin.wp.components.text;

import java.io.Serializable;
import java.util.Iterator;

import com.epam.koryagin.wp.components.NullIterator;
import com.epam.koryagin.wp.components.TextComponent;
/**
 * Token the lowest hierarchy textual entity
 * "leaf" node
 * @author Alexandr Koryagin
 *
 */
public class Token extends TextComponent implements Serializable, Comparable<Token> {
	private static final long serialVersionUID = 5160172131539910739L;
	private static final String DEFAULT_TYPE = "TOKEN";
	private String value;
	private String type;

	public Token() {
		this.value = "";
		this.type = DEFAULT_TYPE;
	}

	public Token(String value) {
		this.value = value;
		this.type = DEFAULT_TYPE;
	}

	@Override
	public int compareTo(Token o) {
		return value.compareTo(o.getValue());
	}

	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String getType(){
		return type;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		if (type != other.type)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Token [value=" + value + ", type=" + type + "]";
	}

	@Override
	public String toOriginalString() {
		return value + " ";
	}
	@Override
	public Iterator<?> createIterator(){
		return new NullIterator();
	}
}
