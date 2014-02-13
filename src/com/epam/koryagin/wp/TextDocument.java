package com.epam.koryagin.wp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextDocument {
	private String text;

	public TextDocument(File file) {
		BufferedReader br = null;
		setText("");
		try {
			br = new BufferedReader(new FileReader("test.txt"));
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
				text = sb.toString();
				System.out.println();
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
		String[] lines = text.split(System.getProperty("line.separator"));
		System.out.println(lines[0]);
		return lines[0];
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
