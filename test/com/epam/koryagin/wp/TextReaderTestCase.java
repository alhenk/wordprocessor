package com.epam.koryagin.wp;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

public class TextReaderTestCase extends TestCase {
	public TextReaderTestCase() {
		super();
	}
	
	protected File getFile(String fileName) throws IOException{
		String fullName =
				this.getClass().getResource(fileName).getFile();
		File file = new File(fullName);
		return file;
	}

}
