package InfoNeeded;

import Support.Activity;
import Support.Term;
import Support.ClassTime;

import java.util.ArrayList;


//represents a specific section of a course
public class Section {
    private Course course;
    private String title;
    private Activity actType;
    private String prof;
    private Term term;
    private ArrayList<ClassTime> classTimes;
    private String url;
    private String courseName;

//Constructor
    public Section(){
        classTimes = new ArrayList<>();
    }

    public Section(String title, Activity actType, String prof, Term term, String url, String courseName) {
        this.title = title;
        this.actType = actType;
        this.prof = prof;
        this.term = term;
        this.url = url;
        this.classTimes = new ArrayList<>();
        this.courseName = courseName;
    }

    // return true if the 2 section has time conflict
    public boolean hasTimeConflict(Section theOther){
        if (term != theOther.term){
            return false;
        }
        for (ClassTime ct : classTimes){
            for (ClassTime tct : theOther.classTimes){
                if (ct.hasTimeConflict(tct)){
                    return true;
                }
            }
        }
        return false;
    }

    public void setCourse(Course course){
        this.course = course;
        if (!course.getSections().contains(this)){
            course.addSection(this);
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCourseName(String name) {
        courseName = name;
    }

    public void addClassTime(ClassTime ct){
        classTimes.add(ct);
    }

    public void setActType(Activity actType) {
        this.actType = actType;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public void setProfURL(String url){
        this.url = url + title;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public String getTitle() {
        return title;
    }

    public Activity getActType() {
        return actType;
    }

    public String getCourseName(){
        return courseName;
    }

    public ArrayList<ClassTime> getClassTime(){
        return classTimes;
    }

    public String getProf() {
        return prof;
    }

    public String getProfURL(){
        return url;
    }

    public Term getTerm() {
        return term;
    }

    public Course  getCourse(){ return  course; }

    @Override
    public String toString() {
        return title;
    }
    
}
