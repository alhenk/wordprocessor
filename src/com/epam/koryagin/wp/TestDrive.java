package com.epam.koryagin.wp;

import com.epam.koryagin.wp.components.TextComponent;
import com.epam.koryagin.wp.components.TextComponent.TextComponentName;
import com.epam.koryagin.wp.components.text.CompositeText;
import com.epam.koryagin.wp.components.text.Token;

public class TestDrive {
	public static void main(String[] args) {
		CompositeText document = CompositeText.create(TextComponentName.DOCUMENT);
		CompositeText paragraph = CompositeText.create(TextComponentName.PARAGRPAPH);
		CompositeText sentence = CompositeText.create(TextComponentName.SENTENCE);
		Token token = new Token("Zorro");

		paragraph.add(token);
		document.add(paragraph);
		
		paragraph = CompositeText.create(TextComponentName.PARAGRPAPH);
		sentence.add(new Token("A"));
		sentence.add(new Token(" "));
		sentence.add(new Token("f"));
		sentence.add(new Token("o"));
		sentence.add(new Token("x"));
		sentence.add(new Token(" "));
		sentence.add(new Token("i"));
		sentence.add(new Token("s"));
		sentence.add(new Token(" "));
		sentence.add(new Token("i"));
		sentence.add(new Token("n"));
		sentence.add(new Token(" "));
		sentence.add(new Token("t"));
		sentence.add(new Token("h"));
		sentence.add(new Token("e"));
		sentence.add(new Token(" "));
		sentence.add(new Token("b"));
		sentence.add(new Token("o"));
		sentence.add(new Token("x"));
		sentence.add(new Token("."));
		
		paragraph.add(sentence);
		paragraph.add(token);
		paragraph.add(sentence);
		document.add(paragraph);
		//document.add(token);
		
		
		//System.out.println(document.toOriginalString());
		
//		Iterator<?> iterator = document.createIterator();
//		System.out.println(iterator.hasNext());
//		
//		while (iterator.hasNext()) {
//		TextComponent component = (TextComponent)iterator.next();
//			try {
//					//System.out.println(textComponent.toString());
//				String name = component.getName().toString();
//				String type = component.getName().toString();
//				System.out.print("<"+ name +" type="+type+">");
//				System.out.print("  " + component.getValue());
//				System.out.print("</"+ name +">\n");
//			} catch (UnsupportedOperationException e) {
//			}
//		}
		
		for(TextComponent par :document){
			System.out.println(par.toString());
			for (TextComponent sen : par){
				System.out.println(sen.toString());
				for(TextComponent tok : sen){
					System.out.print(tok.getValue());
				}
				System.out.println();
			}
		}
//		TaskLogic task = new TaskLogic(document);
//		System.out.println(task.print());

	}
}
