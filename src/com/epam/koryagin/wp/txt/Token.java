package com.epam.koryagin.wp.txt;

public class Token {
	public static enum Type{
		WORD, PUNCTUATION, UNDEFINED;
	}
	private String value;
	private Type type;
	
	public Token(){
		this.value= "";
		this.type = Type.UNDEFINED;
	}
	
	public Token(String value){
		this.value = value;
		this.type = Type.UNDEFINED;
	}

	public String getToken() {
		return value;
	}

	public void setToken(String value) {
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
}
