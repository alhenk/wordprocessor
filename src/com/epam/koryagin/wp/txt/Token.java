package com.epam.koryagin.wp.txt;

import java.io.Serializable;
/**
 * Token the lowest hierarchy textual entity
 * 
 * @author Alexandr Koryagin
 *
 */
public class Token implements Serializable, Comparable<Token> {
	private static final long serialVersionUID = 5160172131539910739L;

	public static enum Type {
		WORD, NUMERIC, QUOTATION_MARK, PUNCTUATION, UNDEFINED;
	}

	private String value;
	private Type type;

	public Token() {
		this.value = "";
		this.type = Type.UNDEFINED;
	}

	public Token(String value) {
		this.value = value;
		this.type = Type.UNDEFINED;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
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
	public String toString() {
		return "Token [value=" + value + ", type=" + type + "]";
	}

	@Override
	public int compareTo(Token o) {
		return value.compareTo(o.getValue());
	}
}
