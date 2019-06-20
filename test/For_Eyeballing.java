import Support.ClassTime;

import java.io.IOException;
import java.time.*;
import static java.time.DayOfWeek.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;


public class For_Eyeballing {
    public static void main(String[] args) {
        String url = "https://slacknotes.com/grades/CPSC210?Professor=baniassad,%20elisa;eiselt,%20kurt";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements averageBlock = doc.getElementsByClass("class-header-info center");
            System.out.println(averageBlock);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

