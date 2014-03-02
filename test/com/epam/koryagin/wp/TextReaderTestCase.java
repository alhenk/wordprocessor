package com.epam.koryagin.wp;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

public class TextReaderTestCase extends TestCase {
	public TextReaderTestCase() {
		super();
	}
	
	protected File getFile(String filename) throws IOException{
		String fullname =
				this.getClass().getResource(filename).getFile();
		File file = new File(fullname);
		return file;
	}

}
