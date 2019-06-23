import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


public class For_Eyeballing {
    public static void main(String[] args) {
        String url = "https://slacknotes.com/grades/CPSC210?Professor=baniassad,%20elisa";
        try {
            Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
            Elements averageBlock = doc.select("div.class-header-info.center");
            System.out.println(averageBlock.text());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

