package com.yangwu;

import java.io.BufferedReader;
import java.io.IOException;

public class FileInfo {
	private String fileName;

	private int currentNum;

	private BufferedReader reader;

	public void readNext() throws IOException {
		String data = reader.readLine();
		if (data != null) {
			this.currentNum = Integer.valueOf(data);
		} else {
			this.currentNum = -1;
		}
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getCurrentNum() {
		return currentNum;
	}

	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}
}
