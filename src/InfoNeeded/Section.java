package InfoNeeded;

import InfoNeeded.Support.Activity;
import static InfoNeeded.Support.Activity.*;
import InfoNeeded.Support.Term;
import javafx.util.Pair;

import static InfoNeeded.Support.Term.*;



//represents a specific section of a course
public class Section {
    private String title;

    //the section's activity type (e.g. LECTURE, LAB)
    private Activity actType;

    private String prof;
    private Term term;

}
