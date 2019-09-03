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
        testCourse = new Course("test");
        testSection = new Section();
        testURL = testCourse.findCourseURL("COMM", "293");
        jsoupDemo.dataScraping(testURL);
        testList = jsoupDemo.getSections();
    }


    @Test
    public void testPrereqs() throws IOException {
        String url = testURL;
        Document dc = Jsoup.connect(url).get();
        Elements body = dc.select("div.content.expand p");
        try{
            Elements b = body.select("a");
            //print out all prereqs
//            System.out.println(b.text());
//            for (int i=0; i<b.size(); i++){
//                System.out.println(b.text());
//            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //    @Test
  //  public void testSection() {
////        assertTrue(testList.size() == 17);
////        for (int i = 0; i < testList.size(); i++) {
////            System.out.println(testList.get(i).getTitle());
////        }
   // }

    //

    }
////            System.out.println(testList.get(i).getProf());
////        }
//        //assertEquals("101", testList.get(0).getTitle());
//    }
//}
//    @Test
//    public void findProfTest() {
//        for (int i = 0; i < testList.size(); i++) {
//            System.out.println(testList.get(i).getProf());
//        }
//    }
//}

