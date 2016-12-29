package com.wiki.calculator;

import org.apache.commons.lang3.StringUtils;

import com.wiki.pages.Link;
import com.wiki.pages.Page;

public class CalculatorImp implements Calculator {
	private float alpha = 0.4f;
	private float beta = 0.6f;
	@Override
	public float calculate(Page p1, Page p2) {
		float val1 = 0.0f;
		float val2 = 0.0f;
		val1 = alpha * (getTextRate(p1.getName(), p2.getContent()) * p2.getContent().length()
				+ getTextRate(p2.getName(), p1.getContent()) * p1.getContent().length());
		
		return val1 + val2;
	}

	private float getTextRate(String header, String text) {
		return StringUtils.countMatches(text, header);
	}

	private float getLinkRates(Link[] p1Links, Link[] p2Links) {
		return 0;
	}

}
