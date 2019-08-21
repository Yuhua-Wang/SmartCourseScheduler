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
     void SimpleNoConflictPossible(){
        Section s1 = new Section("S1", LAB, "Bowen", TERM_1, "");
        LocalTime st1 = LocalTime.of(9,15);
        LocalTime et1 = LocalTime.of(10,15);
        ClassTime ct1 = new ClassTime(MONDAY, st1, et1);
        s1.addClassTime(ct1);
        CourseActivity ca1 = new CourseActivity("CA1", LAB);
        ca1.getSections().add(s1);
        Section s3 = new Section("S3", LECTURE, "Bowen", TERM_1, "");
        LocalTime st3 = LocalTime.of(14,15);
        LocalTime et3 = LocalTime.of(15,15);
        ClassTime ct3 = new ClassTime(MONDAY, st3, et3);
        s3.addClassTime(ct3);
        ca1.getSections().add(s3);
        cas.add(ca1);
        Section s2 = new Section("S2", LECTURE, "Bowen", TERM_1, "");
        LocalTime st2 = LocalTime.of(9,15);
        LocalTime et2 = LocalTime.of(10,15);
        ClassTime ct2 = new ClassTime(MONDAY, st2, et2);
        s2.addClassTime(ct2);
        CourseActivity ca2 = new CourseActivity("CA2", LECTURE);
        ca2.getSections().add(s2);
       cas.add(ca2);

        Scheduler scheduler = new Scheduler(cas);
        scheduler.generateSchedule();
        // should be 0, 1
    }

    @Test
     void mediumLevelImpossible(){
        CourseActivity ca1 = new CourseActivity("CA1", LAB);
        // S1: MON WED FRI 9:15-10:15 TERM 1
        Section s1 = new Section("S1", LAB, "Bowen", TERM_1, "");
        LocalTime st1 = LocalTime.of(9,15);
        LocalTime et1 = LocalTime.of(10,15);
        ClassTime ct1 = new ClassTime(MONDAY, st1, et1);
        s1.addClassTime(ct1);
        ClassTime ct1_2 = new ClassTime(WEDNESDAY, st1, et1);
        s1.addClassTime(ct1_2);
        ClassTime ct1_3 = new ClassTime(FRIDAY, st1, et1);
        s1.addClassTime(ct1_3);
        ca1.getSections().add(s1);
        // S2 : TUE FRI 9:30-10:30
        Section s2 = new Section("S2", LAB, "Bowen", TERM_1, "");
        LocalTime st2 = LocalTime.of(9,30);
        LocalTime et2 = LocalTime.of(12,30);
        ClassTime ct2 = new ClassTime(TUESDAY, st2, et2);
        s2.addClassTime(ct2);
        ClassTime ct2_2 = new ClassTime(FRIDAY, st2, et2);
        s2.addClassTime(ct2_2);
        ca1.getSections().add(s2);

        CourseActivity ca2 = new CourseActivity("CA2", LECTURE);
        // S3: MON 18.00-19.00 WED TUE 10.00-11.00
        Section s3 = new Section("S3", LECTURE, "Bowen", TERM_1, "");
        LocalTime st3 = LocalTime.of(10,0);
        LocalTime et3 = LocalTime.of(11,0);
        LocalTime st3_1 = LocalTime.of(19,0);
        LocalTime et3_1 = LocalTime.of(18,0);
        ClassTime ct3_2 = new ClassTime(MONDAY, st3_1, et3_1);
        s3.addClassTime(ct3_2);
        ClassTime ct3 = new ClassTime(TUESDAY, st3, et3);
        s3.addClassTime(ct3);
        ClassTime ct3_1 = new ClassTime(WEDNESDAY, st3, et3);
        s3.addClassTime(ct3_1);
        ca2.getSections().add(s3);
        // S4: MON TUE 10.15-12.00
        Section s4 = new Section("S4", LECTURE, "Bowen", TERM_1, "");
        LocalTime st4 = LocalTime.of(10,15);
        LocalTime et4 = LocalTime.of(12,0);
        ClassTime ct4 = new ClassTime(TUESDAY, st4, et4);
        s4.addClassTime(ct4);
        ClassTime ct4_1 = new ClassTime(MONDAY, st4, et4);
        s4.addClassTime(ct4_1);
        ca2.getSections().add(s4);

        CourseActivity ca3 = new CourseActivity("CA3", LECTURE);
        // S5: TERM 2
        Section s5 = new Section("S5", LECTURE, "Bowen", TERM_2, "");
        LocalTime st5 = LocalTime.of(9,15);
        LocalTime et5 = LocalTime.of(10,15);
        ClassTime ct5 = new ClassTime(MONDAY, st5, et5);
        s5.addClassTime(ct5);
        ClassTime ct5_2 = new ClassTime(WEDNESDAY, st5, et5);
        s5.addClassTime(ct5_2);
        ClassTime ct5_3 = new ClassTime(FRIDAY, st5, et5);
        s5.addClassTime(ct5_3);
        ca3.getSections().add(s5);

        cas.add(ca1);
        cas.add(ca2);
        cas.add(ca3);
        Scheduler scheduler = new Scheduler(cas);
        scheduler.generateSchedule();
        //0 1 0
    }

    @Test
    void multipleSolutions(){
       CourseActivity ca1 = new CourseActivity("CA1", LAB);
       // S1: MON WED FRI 9:15-10:15 TERM 1
       Section s1 = new Section("S1", LAB, "Bowen", TERM_1, "");
       LocalTime st1 = LocalTime.of(9,15);
       LocalTime et1 = LocalTime.of(10,15);
       ClassTime ct1 = new ClassTime(MONDAY, st1, et1);
       s1.addClassTime(ct1);
       ClassTime ct1_2 = new ClassTime(WEDNESDAY, st1, et1);
       s1.addClassTime(ct1_2);
       ClassTime ct1_3 = new ClassTime(FRIDAY, st1, et1);
       s1.addClassTime(ct1_3);
       ca1.getSections().add(s1);
       // S2 : TUE FRI 9:30-10:30
       Section s2 = new Section("S2", LAB, "Bowen", TERM_1, "");
       LocalTime st2 = LocalTime.of(9,30);
       LocalTime et2 = LocalTime.of(12,30);
       ClassTime ct2 = new ClassTime(TUESDAY, st2, et2);
       s2.addClassTime(ct2);
       ClassTime ct2_2 = new ClassTime(FRIDAY, st2, et2);
       s2.addClassTime(ct2_2);
       ca1.getSections().add(s2);

       CourseActivity ca2 = new CourseActivity("CA2", LECTURE);
       // S3: MON 18.00-19.00 WED TUE 10.00-11.00
       Section s3 = new Section("S3", LECTURE, "Bowen", TERM_1, "");
       LocalTime st3 = LocalTime.of(10,0);
       LocalTime et3 = LocalTime.of(11,0);
       LocalTime st3_1 = LocalTime.of(19,0);
       LocalTime et3_1 = LocalTime.of(18,0);
       ClassTime ct3_2 = new ClassTime(MONDAY, st3_1, et3_1);
       s3.addClassTime(ct3_2);
       ClassTime ct3 = new ClassTime(TUESDAY, st3, et3);
       s3.addClassTime(ct3);
       ClassTime ct3_1 = new ClassTime(WEDNESDAY, st3, et3);
       s3.addClassTime(ct3_1);
       ca2.getSections().add(s3);
       // S4: MON TUE 10.15-12.00
       Section s4 = new Section("S4", LECTURE, "Bowen", TERM_1, "");
       LocalTime st4 = LocalTime.of(10,15);
       LocalTime et4 = LocalTime.of(12,0);
       ClassTime ct4 = new ClassTime(TUESDAY, st4, et4);
       s4.addClassTime(ct4);
       ClassTime ct4_1 = new ClassTime(MONDAY, st4, et4);
       s4.addClassTime(ct4_1);
       ca2.getSections().add(s4);

       CourseActivity ca3 = new CourseActivity("CA3", LECTURE);
       // S5: TERM 2
       Section s5 = new Section("S5", LECTURE, "Bowen", TERM_2, "");
       LocalTime st5 = LocalTime.of(9,15);
       LocalTime et5 = LocalTime.of(10,15);
       ClassTime ct5 = new ClassTime(MONDAY, st5, et5);
       s5.addClassTime(ct5);
       ClassTime ct5_2 = new ClassTime(WEDNESDAY, st5, et5);
       s5.addClassTime(ct5_2);
       ClassTime ct5_3 = new ClassTime(FRIDAY, st5, et5);
       s5.addClassTime(ct5_3);
       ca3.getSections().add(s5);
       // S6: TERM 2
       Section s6 = new Section("S6", LECTURE, "Bowen", TERM_2, "");
       ClassTime ct6 = new ClassTime(MONDAY, st5, et5);
       s6.addClassTime(ct6);
       ca3.getSections().add(s6);

       cas.add(ca1);
       cas.add(ca2);
       cas.add(ca3);
       Scheduler scheduler = new Scheduler(cas);
       scheduler.generateSchedule();
       //0 1 0
       //0 1 1
    }
}
