package com.wiki.util;

public enum LanguageCode {
	Turkish("tr"),
	English("en");
	private final String langCode;
	private LanguageCode(String langCode) {
	this.langCode = langCode;
	}
	public String getLanguageCode(){
		return langCode;
	}
}
