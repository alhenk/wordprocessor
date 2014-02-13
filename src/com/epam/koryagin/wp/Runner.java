package com.epam.koryagin.wp;

import java.io.File;

public class Runner {
	public static void main(String[] args) {
		System.out.println("WORDPROCESSOR");
		
		TextDocument td = new TextDocument(new File("test.txt"));
		System.out.println(td.getFirstLine());
		
	}
}
