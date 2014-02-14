package com.epam.koryagin.wp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class TxtDocument {
	private List<String> content;

	public TxtDocument() {
	}

	public TxtDocument(File file) {
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
		if (content != null) {
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
	 * Remove all extra whitespace and replace it with one space
	 * @param line
	 * @return line
	 */
	public String purge(String line) {
		line = line.trim();
		line = line.replaceAll("\\s+\\.$", ".");
		line = line.replaceAll("\\s+,$", ",");
		line = line.replaceAll("\\s+", " ");
		return line;
	}

	public List<String> purge(LinkedList<String> content) {
		String line;
		for (int i = 0; i < content.size(); i++) {
			line = purge(content.get(i));
			content.set(i, line);
		}
		return content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
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
		TxtDocument other = (TxtDocument) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		return true;
	}

}
