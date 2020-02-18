package rmp;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

// Not Used for now. Under Development.
public class rmpScrapper {

    //Get the Name, University, and Faculty of the professor
    public String getProfInfo() throws IOException {
        Document dc = Jsoup.connect("https://www.ratemyprofessors.com/search.jsp?query="+"Gateman+Robert").get();
        Elements body = dc.getElementsByClass("listing PROFESSOR");
        Elements link = body.select("a[href]"); //Link of the professor
        System.out.println(link.first().text()); //Print the information of the first professor (the most relevant)
        return link.attr("href");
    }

    //Get the overall quality and level of difficulty of the professor
    public void getProfScore() throws IOException {
        Document dc = Jsoup.connect("https://www.ratemyprofessors.com"+ getProfInfo()).get();
        Elements overallQuality = dc.getElementsByClass("breakdown-container quality");
        Elements levelDifficulty = dc.getElementsByClass("breakdown-section difficulty");
        System.out.println(overallQuality.text());
        System.out.println(levelDifficulty.text());
    }


}
