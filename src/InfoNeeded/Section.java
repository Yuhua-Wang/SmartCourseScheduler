package InfoNeeded;

import Support.Activity;
import Support.Term;


//represents a specific section of a course
public class Section {
    private String title;

    //the section's activity type (e.g. LECTURE, LAB)
    private Activity actType;

    private String prof;
    private Term term;



    public void setTitle(String title) {
        this.title = title;
    }

    public void setActType(Activity actType) {
        this.actType = actType;
    }

    public void setProf(String prof) {
        this.prof = prof;
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

    public Term getTerm() {
        return term;
    }
}