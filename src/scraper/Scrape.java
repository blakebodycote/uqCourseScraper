package scraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import course.Course;

public class Scrape {
	
	public static void main(String[] args) throws IOException{
		getCourses(getLinksToCourses(getLinksToCoursePages()));
	}
	
	private static Set<String> getLinksToCourses(Set<String> inputLinks) throws IOException {
		Set<String> coursePages = new HashSet<String>();
		for(String link : inputLinks){
			Document doc = Jsoup.connect(link).get();
			for(Element e: doc.getElementsByClass("courselist")){
				Elements courseLinks = e.select("a[href]");
				for(Element f : courseLinks){
					coursePages.add("https://www.uq.edu.au" + f.attr("href"));
				}
			}
		}
		return coursePages;
	}
	
	private static Set<String> getCourses(Set<String> coursePageLinks) throws IOException{
		Set<String> result = new HashSet<String>();
		for(String link : coursePageLinks){
			Document doc = Jsoup.connect(link).get();
			Element courseProfile = doc.getElementById("course-offering-1-sem");
			result.add("https://www.uq.edu.au" + courseProfile.attr("href"));
		}
		return result;
	}
	
	private static Set<String> getLinksToCoursePages() throws IOException{
		Set<String> linksToPages = getRequiredCoursePageLinks();
		
		Set<String> planPages = new HashSet<String>();
		String[] text = new String[1];
		text[0]="course list";
		int times = 3;
		for(String link : linksToPages){
			if(times < 0){
				break;
			}
			long startTime = System.currentTimeMillis();
			Document linkPage = Jsoup.connect(link).get();
			Elements hyperLinks = linkPage.select("a[href]");
			for(Element e : hyperLinks){
				if(e.text().equals(text[0])){
					planPages.add("https://www.uq.edu.au" + e.toString().substring(9,e.toString().length()-18));
					times--;
					break;
				}
			}
		}
		return planPages;
	}
	
	private static Set<String> getRequiredCoursePageLinks() throws IOException{
		Document doc = Jsoup.connect("https://www.uq.edu.au/study/browse.html?level=ugpg").get();
		Set<Element> elements = new HashSet<Element>();
		for(Element e: doc.getElementsByClass("plan")){
			if((e.toString().contains("acad_plan"))){
				elements.add(e);
			}
		}
		Set<String> links = new HashSet<String>();
		for(Element e: elements){
			int index = e.toString().indexOf("/study/plan");
			int endIndex = e.toString().substring(index).indexOf("\"");
			links.add("https://www.uq.edu.au"+ e.toString().substring(index,index+endIndex));
		}
		
		return links;
	}
}
