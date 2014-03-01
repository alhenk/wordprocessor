package com.epam.koryagin.wp.components.text;

import java.io.Serializable;
import java.util.Iterator;

import com.epam.koryagin.wp.components.NullIterator;
import com.epam.koryagin.wp.components.TextComponent;
import com.epam.koryagin.wp.components.TextComponentType;
/**
 * Token the lowest hierarchy textual entity
 * "leaf" node
 * @author Alexandr Koryagin
 *
 */
public class Token extends TextComponent implements Serializable, Comparable<Token> {
	private static final long serialVersionUID = 5160172131539910739L;
	private String value;
	private TextComponentName name;
	private TextComponentType type;

	public Token() {
		this.value = "";
		this.name = TextComponentName.TOKEN;
		this.type = TokenType.DEFAULT;
	}

	public Token(String value) {
		this.value = value;
		this.name = TextComponentName.TOKEN;
		this.type = TokenType.DEFAULT;
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
	public TextComponentName getName() {
		return name;
	}
	@Override
	public void setName(TextComponentName name) {
		this.name = name;
	}
	@Override
	public TextComponentType getType() {
		return type;
	}
	@Override
	public void setType(TextComponentType type) {
		this.type = type;
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
		return "Token [value=" + this.value + ", type=" + type + "]";
	}

	@Override
	public String toOriginalString() {
		return value;
	}
	@Override
	public Iterator<?> createIterator(){
		return new NullIterator();
	}

	@Override
	public Iterator<TextComponent> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<TextComponent>(){

			@Override
			public boolean hasNext() {
				return false;
			}

			@Override
			public TextComponent next() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
				
			}
			
		};
	}
}
