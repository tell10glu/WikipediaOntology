package com.wiki;

import java.io.IOException;

import com.wiki.pages.Fetcher;
import com.wiki.pages.Page;

public class Main {

	public static void main(String[] args) throws IOException {
		Fetcher.fetch(new Page("https://tr.wikipedia.org/wiki/Hamsi"));
	}

}
