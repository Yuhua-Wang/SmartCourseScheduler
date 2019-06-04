package InfoNeeded;

import java.util.ArrayList;

import InfoNeeded.Support.Activity;
import InfoNeeded.Support.Activity.*;


//represent a course (not a specific section)
public class Course {

    //--------- fields ------------------------------------------------------
    private String title;

    // sections available
    private ArrayList<Section> sections;

    //what activities are required to register (e.g. Lecture and Lab)
    private ArrayList<Activity> requiredActivities;


    //-----------------------------------------------------------------------




    public String getTitle() {
        return title;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public ArrayList<Activity> getRequiredActivities() {
        return requiredActivities;
    }
}
