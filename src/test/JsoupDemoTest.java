package test;

import InfoNeeded.Course;
import InfoNeeded.Section;
import data.JsoupDemo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static Support.Term.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsoupDemoTest {
    private Section testSection;
    private Course testCourse;
    private JsoupDemo jsoupDemo;
    private String testURL;
    private ArrayList<Section> testList;


    //put a UBC Course URL, scrap data from the given website
    @BeforeEach
    public void runBefore() {
        jsoupDemo = new JsoupDemo();
        testCourse = new Course();
        testSection = new Section();
        testURL = testCourse.findCourseURL("COMM",394);
                //"https://courses.students.ubc.ca/cs/courseschedule?pname=subjarea&tname=subj-course&dept=COMM&course=394";
        jsoupDemo.dataScraping(testURL);
        testList = jsoupDemo.getSections();
    }

    @Test
    public void testSection() {
        assertTrue(testList.size() == 17);
        for (int i = 0; i < testList.size(); i++) {
            System.out.println(testList.get(i));
        }
    }

    @Test
    public void generalTest() throws IOException {
        Document doc = Jsoup.connect(testURL).get();
        Elements temp1;
        Elements temp2;
        temp1 = doc.select(".section1");
        temp2 = doc.select(".section2");
        for (int i=0; i<temp2.size(); i++)
        {
            temp1.add(temp2.get(i));
        }
        ArrayList<Section> my_list = new ArrayList<>();
        for (int i = 0; i < temp1.size(); i++) {
            Section mySection = new Section();
            String curString = temp1.get(i).child(1).text();
            System.out.println(curString);
            mySection.setTitle((curString+" ").split(" ")[2]);
            String curTerm = temp1.get(i).child(3).text();
            System.out.println(curTerm);
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
        for(int k=0; k<my_list.size(); k++){
            System.out.println(my_list.get(k).getTerm());
        }
        System.out.println(my_list.get(0));
        assertEquals(TERM_1,my_list.get(0).getTerm());
        assertEquals("101",my_list.get(0).getTitle());
    }
}
