package com.wiki.pages;

public class Link {
	private String href;
	private String text;
	private boolean active;
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Link(String href, String text) {
		this.href = href;
		this.text = text;
	}
	public Link(){
		
	}
	@Override
	public String toString() {
		return "Link [href=" + href + ", text=" + text +"]";
	}
	
}
