package com.epam.koryagin.wp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class TextDocument {
	private List<String> content;
	
	public TextDocument(){
	}

	public TextDocument(File file) {
		BufferedReader br = null;
		content = new LinkedList<String>();
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		if (br != null) {
			try {
				String line = br.readLine();
				while (line != null) {
					content.add(line);
					line = br.readLine();
				}
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

	public String getLine(int i) {
		String line = "";
		if (content != null){
			line = content.get(i);
		}
		return line;
	}
	
	// Getters & Setters
	public List<String> getContent() {
		return content;
	}

	public void setContent(List<String> content) {
		this.content = content;
	}

	/**
	 * remove all extra whitespaces
	 * and replace it with one space
	 * @param line
	 * @return
	 */
	public String purgeWhitespace(String line) {
		line = line.trim();
		line = line.replaceAll("\\s+\\.$", ".");
		line = line.replaceAll("\\s+", " ");
		return line;
	}

}

