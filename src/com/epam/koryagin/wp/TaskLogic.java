package com.epam.koryagin.wp;

import java.util.Iterator;

import com.epam.koryagin.wp.components.TextComponent;
import com.epam.koryagin.wp.components.text.TextDocument;

public class TaskLogic {
	TextDocument document;
	
	public TaskLogic(TextDocument document){
		this.document = document;
	}
	
	public String print(){
		Iterator<?> iterator= document.createIterator();
		StringBuilder sb = new StringBuilder("qu qu");
		while(iterator.hasNext()){
			TextComponent component = (TextComponent)iterator.next();
			sb.append(component.toOriginalString());
		}
		return sb.toString();
	}
}
