package com.epam.koryagin.wp.txt;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Sentence consist of tokens
 * 
 * @author Koryagin
 * 
 */
public class Sentence implements Serializable, Comparable<Sentence> {
	private static final long serialVersionUID = -1022924506845032096L;

	public static enum Type {
		DECLARATIVE, INTERROGATIVE, EXCLAMATORY, HEADER, QUOTATION, UNDEFINED;
	}

	public static Sentence create(List<Token> tokens) {
		return new Sentence(tokens);
	}

	private List<Token> tokens;
	private Type type;

	public Sentence() {
		this.tokens = new LinkedList<Token>();
		this.type = Sentence.Type.UNDEFINED;
	}

	private Sentence(List<Token> tokens) {
		this.tokens = tokens;
		this.type = Sentence.Type.UNDEFINED;
	}

	public List<Token> getTokens() {
		return tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
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
		result = prime * result + ((tokens == null) ? 0 : tokens.hashCode());
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
		Sentence other = (Sentence) obj;
		if (tokens == null) {
			if (other.tokens != null)
				return false;
		} else if (!tokens.equals(other.tokens))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Sentence [ ").append(tokens.size()).append(" - tokens]\n");
		return sb.toString();
	}

	@Override
	public int compareTo(Sentence o) {
		return (this.hashCode() < o.hashCode()) ? -1 : ((this.hashCode() == o
				.hashCode()) ? 0 : 1);
	}
}
