import InfoNeeded.Section;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import static InfoNeeded.Support.Term.*;

public class JsoupDemo {

    private String myURL;
    private String section;
    private String type;
    private int term;
    private String day;
    private int startingTime;
    private int endingTime;
    private String comments;
    private Elements temp1;
    private Elements temp2;

   public void dataScraping(String url){
         myURL = url;
        try {
            Document doc = Jsoup.connect(myURL).get();
            //print title
            String title = doc.title();
            System.out.println(title);

            temp1 = doc.select(".section1");
            temp2 = doc.select(".section2");
            for (int i=0; i<temp2.size(); i++)
            {
                temp1.add(temp2.get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getURL(){
       return myURL;
    }

    public ArrayList getSections() {
        ArrayList<Section> my_list = new ArrayList<>();
        for (int i = 0; i < temp1.size(); i++) {
            Section mySection = new Section();
            String curString = temp1.get(i).child(1).text();
            mySection.setTitle((curString+" ").split(" ")[2]);
            String curTerm = temp1.get(i).child(3).text();
            if(Integer.parseInt(curTerm) == 1){
                mySection.setTerm(TERM_1);
            }
            else if(Integer.parseInt(curTerm) == 2){
                mySection.setTerm(TERM_2);
            }
            else{
                mySection.setTerm(YEAR_TERM);
            }
            my_list.add(mySection);
        }
        return my_list;
    }
}

