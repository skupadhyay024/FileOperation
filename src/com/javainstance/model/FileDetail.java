package com.javainstance.model;

import java.sql.Blob;

/**
 * @author javainstance
 *
 */
public class FileDetail {
	
	
	private int fileId;
	private String fileName;
	private Blob fileContent;
	
	public FileDetail() {
		super();
	
	}
	
	public FileDetail(int fileId, String fileName, Blob fileContent) {
		super();
		this.fileId = fileId;
		this.fileName = fileName;
		this.fileContent = fileContent;
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Blob getFileContent() {
		return fileContent;
	}
	public void setFileContent(Blob fileContent) {
		this.fileContent = fileContent;
	}
	
	
	

}
