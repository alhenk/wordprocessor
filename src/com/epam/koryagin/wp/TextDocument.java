package com.epam.koryagin.wp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextDocument {
	private String content;
	
	public TextDocument(){}

	public TextDocument(File file) {
		BufferedReader br = null;
		content="";
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		if (br != null) {
			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();
				while (line != null) {
					sb.append(line);
					sb.append(System.lineSeparator());
					line = br.readLine();
				}
				content = sb.toString();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getFirstLine() {
		String[] lines = content.split(System.getProperty("line.separator"));
		return lines[0];
	}
	
	// Getters & Setters
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}

