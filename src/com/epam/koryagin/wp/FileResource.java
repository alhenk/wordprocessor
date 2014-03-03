package com.epam.koryagin.wp;

import java.io.File;
import java.io.IOException;

public class FileResource {
	
	public FileResource(){
	}

	protected File getFile(String fileName) throws IOException{
		String fullName =
				this.getClass().getResource(fileName).getFile();
		File file = new File(fullName);
		return file;
	}
}
