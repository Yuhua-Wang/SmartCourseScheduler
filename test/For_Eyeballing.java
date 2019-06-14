import Support.ClassTime;

import java.io.IOException;
import java.time.*;
import static java.time.DayOfWeek.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;


public class For_Eyeballing {
    public static void main(String[] args) {
        String url = "https://courses.students.ubc.ca/cs/courseschedule?pname=subjarea&tname=subj-course&dept=ASIA&course=371";
        try {
            Document doc = Jsoup.connect(url).get();
            String title = doc.title();
            System.out.println(title);
            Elements temp1 = doc.select(".section1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

