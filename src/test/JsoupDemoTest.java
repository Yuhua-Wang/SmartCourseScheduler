package test;

import InfoNeeded.Course;
import InfoNeeded.Section;
import Support.Term;
import data.JsoupDemo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

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
        testURL = testCourse.findCourseURL("CPSC", "313");
        jsoupDemo.dataScraping(testURL);
        testList = jsoupDemo.getSections();
    }

    @Test
    public void testSection() {
        assertTrue(testList.size() == 17);
        for (int i = 0; i < testList.size(); i++) {
            System.out.println(testList.get(i).getTitle());
        }
    }

    @Test
    public void generalTest() throws IOException {
        assertEquals(Term.TERM_1, testList.get(0).getTerm());
        for (int i = 0; i < testList.size(); i++) {
            System.out.println(testList.get(i).getTitle());
            System.out.println(testList.get(i).getTerm());
        }
        assertEquals("101", testList.get(0).getTitle());
    }

    @Test
    public void testSettingProfURL() {
        for (int i = 0; i < testList.size(); i++) {
            System.out.println(testList.get(i).getProfURL());
        }
    }
}
