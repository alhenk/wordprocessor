package com.epam.koryagin.wp;

import java.io.File;
import java.util.LinkedList;

public class Runner {
	public static void main(String[] args) {
//		System.out.println("WORDPROCESSOR");
//		
//		TextDocument td = new TextDocument(new File("test.txt"));
//		System.out.println(td.getFirstLine());
//		Runner runner = new Runner();
//		System.out.println(runner.getClass().getResource("test.txt"));
		
		File file = new File("h:\\JAVALAB\\wordprocessor\\sample_content.txt");
		
		TextReader doc = new TextReader(file);
		LinkedList<String> content= (LinkedList<String>) Processor.purge(new LinkedList<String>(doc.getContent()));
		System.out.println(content);
		
	}
}
