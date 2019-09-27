package InfoNeeded;

import Support.Activity;
import data.JsoupDemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;


//represent a course (not a specific section)
public class Course implements Iterable<Section> {

    //--------- fields ------------------------------------------------------
    private String title;

    // sections available
    private ArrayList<Section> sections;

    //what activities are required to register (e.g. Lecture and Lab)
    private ArrayList<Activity> requiredActivities;

    //JSoupDemo field
    private JsoupDemo demo;


    //-----------------------------------------------------------------------

    public Course(String title){
        sections = new ArrayList<>();
        requiredActivities = new ArrayList<>();
        this.title = title;
    }



    public void addSection( Section section){
        sections.add(section);
        if (section.getCourse() != this){
            section.setCourse(this);
        }
    }

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
    public String findCourseURL(String courseName, String courseNum){
        return "https://courses.students.ubc.ca/cs/courseschedule?pname=subjarea&tname=subj-course&dept="
                +courseName+"&course="
                +courseNum;
    }

    @Override
    public Iterator<Section> iterator() {
        return sections.iterator();
    }
}
