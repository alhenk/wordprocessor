package com.epam.koryagin.wp.components.text;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.epam.koryagin.wp.components.CompositeIterator;
import com.epam.koryagin.wp.components.TextComponent;

/**
 * Sentence consist of tokens
 * 
 * @author Alexandr Koryagin
 * 
 */
public class Sentence extends TextComponent implements Serializable,
		Comparable<Sentence> {
	private static final long serialVersionUID = -1022924506845032096L;
	private static final String DEFAULT_TYPE = "SENTENCE";
	//composite node tokens
	private List<TextComponent> tokens;
	private String type;
	private Iterator<?> iterator = null;

	public Sentence() {
		this.tokens = new LinkedList<TextComponent>();
		this.type = DEFAULT_TYPE;
	}
	private Sentence(List<TextComponent> tokens) {
		this.tokens = tokens;
		this.type = DEFAULT_TYPE;
	}

	/**
	 * Static constructor (factory method)
	 */
	public static Sentence create(List<TextComponent> tokens) {
		Sentence sentence = new Sentence(tokens);
		sentence.type = DEFAULT_TYPE;
		return sentence;
	}

	public static Sentence create(List<TextComponent> tokens, String type) {
		Sentence sentence = new Sentence(tokens);
		sentence.type = type;
		return sentence;
	}

	
	@Override
	public void add(TextComponent token){
		tokens.add(token);
	}
	@Override
	public int compareTo(Sentence o) {
		return (this.hashCode() < o.hashCode()) ? -1 : ((this.hashCode() == o
				.hashCode()) ? 0 : 1);
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
	public TextComponent getComponent(int index){
		return (TextComponent) tokens.get(index);
	}

	public List<TextComponent> getTokens() {
		return (List<TextComponent>) Collections
				.unmodifiableCollection(tokens);
	}

	@Override
	public String getType(){
		return type;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tokens == null) ? 0 : tokens.hashCode());
		return result;
	}
	
	@Override
	public void remove(TextComponent token){
		tokens.remove(token);
	}
	public void setTokens(List<TextComponent> tokens) {
		this.tokens = tokens;
	}
	
	@Override
	public String  toOriginalString() {
		StringBuilder sb = new StringBuilder();
		Iterator<TextComponent> iterator = tokens.iterator();
		while (iterator.hasNext()) {
			TextComponent component = (TextComponent) iterator.next();
			sb.append(component.toOriginalString());
		}
		return sb.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Sentence [ ").append(tokens.size()).append(" - tokens]\n");
		return sb.toString();
	}
	@Override
	public Iterator<?> createIterator() {
		if (iterator == null) {
			iterator =  new CompositeIterator( tokens.iterator());
		}
		return iterator;
	}
}
