package scraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Scrape {
	
	public static void main(String[] args) throws IOException{
		Document doc = Jsoup.connect("https://www.uq.edu.au/study/browse.html?level=ugpg").get();
		Set<Element> elements = new HashSet<Element>();
		for(Element e: doc.getElementsByClass("plan")){
			if((e.toString().contains("acad_plan"))){
				elements.add(e);
			}
		}
		for(Element e: elements){
			int index = e.toString().indexOf("/study/plan");
			System.out.println(index);
			System.out.println(e);
			
			//System.out.println(e.getElementsByTag("a"));
		}
	}
}
