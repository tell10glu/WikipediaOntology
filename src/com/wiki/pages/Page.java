package com.wiki.pages;

import java.util.Arrays;

public class Page {
	protected String name;
	protected String content;
	protected Link[] links;
	private String url;
	public Page(String url){
		this.url = url;
	}
	public String getUrl(){
		return url;
	}
	public String getName() {
		return name;
	}

	public String getContent() {
		return content;
	}

	public Link[] getLinks() {
		return links;
	}
	@Override
	public String toString() {
		return "Page [name=" + name + ", content=" + content + ", links=" + Arrays.toString(links) + ", url=" + url
				+ "]";
	}
	
	
}
