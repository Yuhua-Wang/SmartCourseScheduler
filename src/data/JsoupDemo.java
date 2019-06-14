package data;

import InfoNeeded.Section;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import static Support.Term.*;

public class JsoupDemo {

    private String myURL;
    private String profURL;
    private String type;
    private String day;
    private int startingTime;
    private int endingTime;
    private String comments;
    private Elements temp1;
    private Elements temp2;
    Elements result;

    public void dataScraping(String url) {
        myURL = url;
        profURL = splitURL(myURL);
        try {
            Document doc = Jsoup.connect(myURL).get();
            //print title
            String title = doc.title();
            System.out.println(title);

            temp1 = doc.select(".section1");
            temp2 = doc.select(".section2");

            // interleaving two sections from online html
            Iterator<Element> l1 = temp1.iterator();
            Iterator<Element> l2 = temp2.iterator();
            result = new Elements();
            while (l1.hasNext() || l2.hasNext()) {
                if (l1.hasNext()) {
                    result.add(l1.next());
                }
                if (l2.hasNext()) {
                    result.add(l2.next());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getURL() {
        return myURL;
    }

    public String getProfURL() {
        return profURL;
    }

    //return a list of sections
    public ArrayList getSections() {
        ArrayList<Section> my_list = new ArrayList<>();
        if (result.get(1).child(1).text().isEmpty()) {
            fullYearCourse(my_list);
        } else {
            termCourse(my_list);
        }
        return my_list;
    }

    public ArrayList fullYearCourse(ArrayList<Section> list) {
        for (int k = 0; k < result.size(); k += 2) {
            Section mySection = null;
            try {
                mySection = setSection(k);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mySection.setTerm(YEAR_TERM);
            list.add(mySection);
        }
        return list;
    }

    public ArrayList termCourse(ArrayList<Section> list) {
        for (int i = 0; i < result.size(); i++) {
            try {
                Section mySection = setSection(i);

            String curTerm = result.get(i).child(3).text();
            if (Integer.parseInt(curTerm) == 1) {
                mySection.setTerm(TERM_1);
            } else {
                mySection.setTerm(TERM_2);
            }
            list.add(mySection);
            }catch (Exception e){
                i--;
            }
        }
        return list;
    }

    public Section setSection(int index) throws Exception {
        String profName;
        Section mySection = new Section();
        String curString = result.get(index).child(1).text();
        mySection.setTitle((curString + " ").split(" ")[2]);
        mySection.setProfURL(profURL);
        //try {
            profName = findProf(profURL, mySection.getTitle());
            mySection.setProf(profName);
        //} catch (IOException e) {
           // e.printStackTrace();
        //}
        return mySection;
    }

    public String splitURL(String thatURL) {
        String[] split0 = thatURL.split("-");
        String[] split1 = split0[1].split("&");
        return (split0[0] + "-section&" + split1[1] + "&" + split1[2] + "&section=");
    }

    public String findProf(String url, String sectionNum) throws IOException {
        String name;
        String theURL = url + sectionNum;
        System.out.println(theURL);
        Document dc = Jsoup.connect(theURL).get();
        Elements body = dc.select("table tr td");
        try{
            Elements a = body.select("a");
            System.out.println(a.text());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        //System.out.println(body.text());
        //String section = body.get(2).child(0).getElementsByTag("a").text();
//        if (section.isEmpty()||section=="TBA") {
//            name = "Prof currently not available";
//            }
//        else {
//            name = section;
//        }
//        return name;
        return "";
    }
}
