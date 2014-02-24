package com.epam.koryagin.wp.txt;

import java.util.LinkedList;
import java.util.List;

public class Sentence {
	public static enum Type{
		DECLARATIVE, INTERROGATIVE,  EXCLAMATORY, HEADER, QUOTATION, UNDEFINED;
	}
	public static Sentence create(List<Token> tokens){
		return new Sentence(tokens);
	}
	private List<Token> sentence;
	private Type type;
	
	public Sentence(){
		this.sentence = new LinkedList<Token>();
		this.type = Sentence.Type.UNDEFINED;
	}
	private Sentence(List<Token> tokens){
		this.sentence = tokens;
		this.type = Sentence.Type.UNDEFINED;
	}

	public List<Token> getSentence() {
		return sentence;
	}

	public void setSentence(List<Token> sentence) {
		this.sentence = sentence;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sentence == null) ? 0 : sentence.hashCode());
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
		if (sentence == null) {
			if (other.sentence != null)
				return false;
		} else if (!sentence.equals(other.sentence))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Sentence [sentence=" + sentence + "]";
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	

}
