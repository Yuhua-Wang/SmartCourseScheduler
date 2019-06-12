package InfoNeeded;

import Support.Activity;
import Support.Term;
import Support.ClassTime;

import java.util.ArrayList;


//represents a specific section of a course
public class Section {
    private String title;
    private Activity actType;
    private String prof;
    private Term term;
    private ArrayList<ClassTime> classTimes;
    private String url;



    public void setTitle(String title) {
        this.title = title;
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

    public String getProf() {
        return prof;
    }

    public String getProfURL(){
        return url;
    }

    public Term getTerm() {
        return term;
    }

}
