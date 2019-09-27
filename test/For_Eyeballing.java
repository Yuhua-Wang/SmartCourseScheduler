

import InfoNeeded.Section;
import Support.Activity;
import Support.ClassTime;
import Support.Term;
import UI.MenuWindow;
import UI.TimeTableWindow;
import javax.swing.*;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

import static Support.Activity.LABORATORY;
import static Support.Term.TERM_1;
import static java.time.DayOfWeek.*;


public class For_Eyeballing extends JFrame {
    public static void main(String[] args) throws IOException {
        Section s1 = new Section("S1", LABORATORY, "Bowen", TERM_1, "");
        LocalTime st1 = LocalTime.of(9,15);
        LocalTime et1 = LocalTime.of(10,15);
        ClassTime ct1 = new ClassTime(MONDAY, st1, et1);
        s1.addClassTime(ct1);
        ClassTime ct1_2 = new ClassTime(WEDNESDAY, st1, et1);
        s1.addClassTime(ct1_2);
        ClassTime ct1_3 = new ClassTime(FRIDAY, st1, et1);
        s1.addClassTime(ct1_3);

        ArrayList<Section> sections = new ArrayList<>();
        sections.add(s1);
        TimeTableWindow t = new TimeTableWindow();
        t.newTable(sections);
    }

}

