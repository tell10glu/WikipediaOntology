package com.wiki.pages;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Fetcher {
	public static void fetch(Page page) throws IOException{
		//JSOUP BURADA KULLANILACAK.
		Document doc = fetchDoc(page.getUrl());
		String pageName = doc.select("h1#firstHeading").html();
		Element contentDiv = doc.select("div#mw-content-text").first();
		page.name = pageName;
		Elements prs = contentDiv.select("p");
		StringBuilder bl = new StringBuilder();
		for(Element e :prs){
			bl.append(e.text());
			Elements links = e.select("a");
			for(Element link : links){
				System.out.print(link.attr("href")+" ");
				System.out.print(link.html()+"\n");
			}
		}
		System.out.println(bl.toString());
	}
	private static Document fetchDoc(String url) throws IOException{
		Connection.Response response = Jsoup.connect(url).maxBodySize(0).execute();
		return response.parse();
	}
	
	
}
