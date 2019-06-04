import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupDemo {

        public static void main(String[] args) {


            String url = "https://courses.students.ubc.ca/cs/courseschedule?pname=subjarea&tname=subj-course&dept=CPSC&course=304";
            try {
                Document doc = Jsoup.connect(url).get();
                String title = doc.title();
                System.out.println(title);
                Elements temp1 = doc.select(".section1");
                Elements temp2 = doc.select(".section2");
                for (int i=0; i < temp1.size(); i++){
                    System.out.println(temp1.get(i).text());
                }
                for (int j=0; j < temp2.size(); j++){
                    System.out.println(temp2.get(j).text());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
}}
