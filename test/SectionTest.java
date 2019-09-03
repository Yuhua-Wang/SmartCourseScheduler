import InfoNeeded.Section;
import Support.ClassTime;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static Support.Activity.LECTURE;
import static Support.Term.TERM_1;
import static Support.Term.TERM_2;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.TUESDAY;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SectionTest {

    @Test
    public void hasTimeConflictTest_sameTime(){
        Section s1 = new Section();
        s1.setTitle("Test 101");
        s1.setTerm(TERM_1);
        s1.setProf("Dr. Tester");
        s1.setActType(LECTURE);
        LocalTime st1 = LocalTime.of(9,0);
        LocalTime et1 = LocalTime.of(10,0);
        ClassTime c1 = new ClassTime();
        c1.setDayOfWeek(MONDAY);
        c1.setStartTime(st1);
        c1.setEndTime(et1);
        s1.addClassTime(c1);

        Section s2 = new Section();
        s2.setTitle("Test 102");
        s2.setTerm(TERM_1);
        s2.setProf("Dr. Tester");
        s2.setActType(LECTURE);
        LocalTime st2 = LocalTime.of(9,0);
        LocalTime et2 = LocalTime.of(10,0);
        ClassTime c2 = new ClassTime();
        c2.setDayOfWeek(MONDAY);
        c2.setStartTime(st2);
        c2.setEndTime(et2);
        s2.addClassTime(c2);

        assertTrue(s1.hasTimeConflict(s2));
    }

    @Test
    public void hasTimeConflictTest_overlap(){
        Section s1 = new Section();
        s1.setTerm(TERM_1);
        LocalTime st1 = LocalTime.of(9,0);
        LocalTime et1 = LocalTime.of(10,0);
        ClassTime c1 = new ClassTime();
        c1.setDayOfWeek(MONDAY);
        c1.setStartTime(st1);
        c1.setEndTime(et1);
        s1.addClassTime(c1);

        Section s2 = new Section();
        s2.setTerm(TERM_1);
        LocalTime st2 = LocalTime.of(9,30);
        LocalTime et2 = LocalTime.of(10,30);
        ClassTime c2 = new ClassTime();
        c2.setDayOfWeek(MONDAY);
        c2.setStartTime(st2);
        c2.setEndTime(et2);
        s2.addClassTime(c2);
        assertTrue(s1.hasTimeConflict(s2));

        Section s3 = new Section();
        s3.setTerm(TERM_1);
        LocalTime st3 = LocalTime.of(8,30);
        LocalTime et3 = LocalTime.of(9,30);
        ClassTime c3 = new ClassTime();
        c3.setDayOfWeek(MONDAY);
        c3.setStartTime(st3);
        c3.setEndTime(et3);
        s3.addClassTime(c3);
        assertTrue(s1.hasTimeConflict(s3));

        Section s4 = new Section();
        s4.setTerm(TERM_1);
        LocalTime st4 = LocalTime.of(9,30);
        LocalTime et4 = LocalTime.of(10,30);
        ClassTime c4 = new ClassTime();
        c4.setDayOfWeek(MONDAY);
        c4.setStartTime(st4);
        c4.setEndTime(et4);
        s4.addClassTime(c4);
        assertTrue(s1.hasTimeConflict(s4));

    }

    @Test
    public void hasTimeConflictTest_noOverlap(){
        Section s1 = new Section();
        s1.setTerm(TERM_1);
        LocalTime st1 = LocalTime.of(9,0);
        LocalTime et1 = LocalTime.of(10,0);
        ClassTime c1 = new ClassTime();
        c1.setDayOfWeek(MONDAY);
        c1.setStartTime(st1);
        c1.setEndTime(et1);
        s1.addClassTime(c1);

        Section s2 = new Section();
        s2.setTerm(TERM_2);
        LocalTime st2 = LocalTime.of(9,0);
        LocalTime et2 = LocalTime.of(10,0);
        ClassTime c2 = new ClassTime();
        c2.setDayOfWeek(MONDAY);
        c2.setStartTime(st2);
        c2.setEndTime(et2);
        s2.addClassTime(c2);
        assertFalse(s1.hasTimeConflict(s2));

        s2 = new Section();
        s2.setTerm(TERM_1);
        st2 = LocalTime.of(9,0);
        et2 = LocalTime.of(10,0);
        c2 = new ClassTime();
        c2.setDayOfWeek(TUESDAY);
        c2.setStartTime(st2);
        c2.setEndTime(et2);
        s2.addClassTime(c2);
        assertFalse(s1.hasTimeConflict(s2));

        s2 = new Section();
        s2.setTerm(TERM_1);
        st2 = LocalTime.of(10,0);
        et2 = LocalTime.of(11,0);
        c2 = new ClassTime();
        c2.setDayOfWeek(MONDAY);
        c2.setStartTime(st2);
        c2.setEndTime(et2);
        LocalTime st3 = LocalTime.of(8,0);
        LocalTime et3 = LocalTime.of(9,0);
        ClassTime c3 = new ClassTime();
        c3.setDayOfWeek(MONDAY);
        c3.setStartTime(st3);
        c3.setEndTime(et3);
        s2.addClassTime(c2);
        s2.addClassTime(c3);
        assertFalse(s1.hasTimeConflict(s2));
    }

    @Test
    public void hasTimeConflictTest_someOverlap(){
        Section s1 = new Section();
        s1.setTerm(TERM_1);
        LocalTime st1 = LocalTime.of(9,0);
        LocalTime et1 = LocalTime.of(10,0);
        ClassTime c1 = new ClassTime();
        c1.setDayOfWeek(MONDAY);
        c1.setStartTime(st1);
        c1.setEndTime(et1);
        s1.addClassTime(c1);

        Section s2 = new Section();
        s2.setTerm(TERM_1);
        LocalTime st2 = LocalTime.of(9,0);
        LocalTime et2 = LocalTime.of(10,0);
        ClassTime c2 = new ClassTime();
        c2.setDayOfWeek(MONDAY);
        c2.setStartTime(st2);
        c2.setEndTime(et2);
        LocalTime st3 = LocalTime.of(8,0);
        LocalTime et3 = LocalTime.of(9,0);
        ClassTime c3 = new ClassTime();
        c3.setDayOfWeek(MONDAY);
        c3.setStartTime(st3);
        c3.setEndTime(et3);
        s2.addClassTime(c2);
        s2.addClassTime(c3);
        assertTrue(s1.hasTimeConflict(s2));
    }
}
