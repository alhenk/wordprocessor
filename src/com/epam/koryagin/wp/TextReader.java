package com.epam.koryagin.wp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * TextReader represents text document, a list of lines - content
 * constructor read a file line by line
 * @author Alexandr Koryagin
 */
public class TextReader {
	private static final Logger LOGGER = Logger.getLogger(Runner.class);
	private List<String> content;

	public TextReader() {
	}

	public TextReader(File file) {
		BufferedReader bufferedReader = null;
		content = new LinkedList<String>();
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			LOGGER.error("File not found" + e);
		}
		if (bufferedReader != null) {
			try {
				String line = bufferedReader.readLine();
				while (line != null) {
					content.add(line);
					line = bufferedReader.readLine();
				}
			} catch (IOException e) {
				LOGGER.error("Reading Line Error" + e);
			} finally {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					LOGGER.error("File Close Error" + e);
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
		TextReader other = (TextReader) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		return true;
	}

}
