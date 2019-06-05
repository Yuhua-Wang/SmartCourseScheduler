package test;

import InfoNeeded.Course;
import InfoNeeded.Section;
import data.JsoupDemo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsoupDemoTest {
    private Section testSection;
    private Course testCourse;
    private JsoupDemo jsoupDemo;
    private String testURL;
    private ArrayList<Section> testList;


    //put a UBC Course URL, scrap data from the given website
    @BeforeEach
    public void runBefore(){
        jsoupDemo = new JsoupDemo();
        testCourse = new Course();
        testSection = new Section();
        testURL = "https://courses.students.ubc.ca/cs/courseschedule?pname=subjarea&tname=subj-course&dept=CPSC&course=319";
        jsoupDemo.dataScraping(testURL);
        testList = jsoupDemo.getSections();
    }

    @Test
    public void testSection(){
        assertTrue(testList.size()==4);
        for (int i = 0; i < testList.size(); i++) {
            System.out.println(testList.get(i));
        }
        System.out.println(testSection.getTerm());
    }

}
