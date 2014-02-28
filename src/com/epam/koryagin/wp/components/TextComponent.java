package com.epam.koryagin.wp.components;

import java.util.Iterator;

public abstract class TextComponent{

	public void add(TextComponent component){
		throw new UnsupportedOperationException();
	}
	
	public abstract boolean equals(Object obj);
	
	public TextComponent getComponent(int index){
		throw new UnsupportedOperationException();
	}
	
	public String getType(){
		throw new UnsupportedOperationException();
	}
	
	public abstract int hashCode();
	
	public void remove(TextComponent component){
		throw new UnsupportedOperationException();
	}
	public String toOriginalString(){
		throw new UnsupportedOperationException();
	}
	
	public Iterator<?> createIterator(){
		throw new UnsupportedOperationException();
	}
	
}
