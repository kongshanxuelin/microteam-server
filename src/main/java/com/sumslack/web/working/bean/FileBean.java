package com.sumslack.web.working.bean;

public class FileBean {
	public static enum FileType{
		IMAGE,SOUND,WORD,EXCEL,PPT,PDF,OTHER
	}
	public FileBean(FileType fileType,String url,String surl,String name){
		this.fileType = fileType;
		this.url = url;
		this.surl = surl;
		this.name = name;
	}
	public FileBean(String name,String url,String surl){
		this.fileType = FileType.IMAGE;
		this.name = name;
		this.url = url;
		this.surl = surl;
		if (name.toLowerCase().endsWith(".png")
				|| name.toLowerCase().endsWith(".jpg")
				|| name.toLowerCase().endsWith(".jpeg")) {
			this.fileType = FileType.IMAGE;
		}else if (name.toLowerCase().endsWith(".amr")) {
			this.fileType = FileType.SOUND;
		}else if (name.toLowerCase().endsWith(".doc")
				|| name.toLowerCase().endsWith(".docx")) {
			this.fileType = FileType.WORD;
		}else if (name.toLowerCase().endsWith(".xls")
				|| name.toLowerCase().endsWith(".xlsx")) {
			this.fileType = FileType.EXCEL;
		}else if (name.toLowerCase().endsWith(".ppt")
				|| name.toLowerCase().endsWith(".pptx")) {
			this.fileType = FileType.PPT;
		}else if (name.toLowerCase().endsWith(".pdf")) {
			this.fileType = FileType.PDF;
		}else {
			this.fileType = FileType.OTHER;
		}
	}
	public FileBean(String name,String url){
		this.fileType = FileType.SOUND;
		this.name = name;
		this.url = url;
		if (name.toLowerCase().endsWith(".png")
				|| name.toLowerCase().endsWith(".jpg")
				|| name.toLowerCase().endsWith(".jpeg")) {
			this.fileType = FileType.IMAGE;
		}else if (name.toLowerCase().endsWith(".amr")) {
			this.fileType = FileType.SOUND;
		}else if (name.toLowerCase().endsWith(".doc")
				|| name.toLowerCase().endsWith(".docx")) {
			this.fileType = FileType.WORD;
		}else if (name.toLowerCase().endsWith(".xls")
				|| name.toLowerCase().endsWith(".xlsx")) {
			this.fileType = FileType.EXCEL;
		}else if (name.toLowerCase().endsWith(".ppt")
				|| name.toLowerCase().endsWith(".pptx")) {
			this.fileType = FileType.PPT;
		}else if (name.toLowerCase().endsWith(".pdf")) {
			this.fileType = FileType.PDF;
		}else {
			this.fileType = FileType.OTHER;
		}
	}
	
	private FileType fileType;
	private String url;
	private String surl;
	private String name;
	public FileType getFileType() {
		return fileType;
	}
	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSurl() {
		return surl;
	}
	public void setSurl(String surl) {
		this.surl = surl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
