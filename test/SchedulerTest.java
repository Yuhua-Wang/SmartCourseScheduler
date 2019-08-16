import InfoNeeded.CourseActivity;
import InfoNeeded.Section;
import Scheduler.Scheduler;
import Support.Activity;
import Support.ClassTime;
import Support.Term;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static Support.Activity.*;
import static Support.Term.*;
import static java.time.DayOfWeek.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

 class SchedulerTest {
    private ArrayList<CourseActivity> cas;

    @BeforeEach
    void runBeforeTest(){
        cas = new ArrayList<>();
    }

    @Test
     void SimpleNoConflict2CA(){

        // 2 CourseActivity, each has one section of the same term but different time
        Section s1 = new Section("S1", LAB, "Bowen", TERM_1, "");
        LocalTime st1 = LocalTime.of(9,15);
        LocalTime et1 = LocalTime.of(10,15);
        ClassTime ct1 = new ClassTime(MONDAY, st1, et1);
        s1.addClassTime(ct1);
        CourseActivity ca1 = new CourseActivity("CA1", LAB);
        ca1.getSections().add(s1);
        cas.add(ca1);
        Section s2 = new Section("S2", LECTURE, "Bowen", TERM_1, "");
        LocalTime st2 = LocalTime.of(9,15);
        LocalTime et2 = LocalTime.of(10,15);
        ClassTime ct2 = new ClassTime(MONDAY, st2, et2);
        s2.addClassTime(ct2);
        CourseActivity ca2 = new CourseActivity("CA2", LECTURE);
        ca2.getSections().add(s2);
       Section s3 = new Section("S3", LECTURE, "Bowen", TERM_1, "");
       LocalTime st3 = LocalTime.of(14,15);
       LocalTime et3 = LocalTime.of(15,15);
       ClassTime ct3 = new ClassTime(MONDAY, st3, et3);
       s3.addClassTime(ct3);
       ca2.getSections().add(s3);
       cas.add(ca2);

        Scheduler scheduler = new Scheduler(cas);
        scheduler.generateSchedule();
    }
}
