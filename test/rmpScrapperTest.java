import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

public class rmpScrapperTest {
    @Test
    public void scrap () throws IOException {
        Document dc = Jsoup.connect("https://www.ratemyprofessors.com/search.jsp?query=zamar+ruben").get();
        Elements body = dc.getElementsByClass("listing PROFESSOR");
        Elements link = body.select("a[href]");
        System.out.println(link.text());
        System.out.println(link.attr("href"));

    }

    @Test
    public void scrap1() throws IOException {
        Document dc = Jsoup.connect("https://www.ratemyprofessors.com"+"/ShowRatings.jsp?tid=26388").get();
        Elements overallQuality = dc.getElementsByClass("breakdown-container quality");
        Elements levelDifficulty = dc.getElementsByClass("breakdown-section difficulty");
        System.out.println(overallQuality.text());
        System.out.println(levelDifficulty.text()+"\n");
    }


}
