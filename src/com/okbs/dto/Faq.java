package com.okbs.dto;

public class Faq {
	private String fno;
	private String category;
	private String title;
	private String content;
	@Override
	public String toString() {
		return "Faq [fno=" + fno + ", category=" + category + ", title="
				+ title + ", content=" + content + "]";
	}
	public String getFno() {
		return fno;
	}
	public void setFno(String fno) {
		this.fno = fno;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
