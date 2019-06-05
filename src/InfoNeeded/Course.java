package InfoNeeded;

import Support.Activity;
import data.JsoupDemo;

import java.util.ArrayList;


//represent a course (not a specific section)
public class Course {

    //--------- fields ------------------------------------------------------
    private String title;

    // sections available
    private ArrayList<Section> sections;

    //what activities are required to register (e.g. Lecture and Lab)
    private ArrayList<Activity> requiredActivities;

    //JSoupDemo field
    private JsoupDemo demo;


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

    //start data scraping with the given user input (course name, course number)
    public String findCourseURL(String courseName, int courseNum){
        String num = Integer.toString(courseNum);
        return "https://courses.students.ubc.ca/cs/courseschedule?pname=subjarea&tname=subj-course&dept="
                +courseName+"&course="
                +num;
    }
}
