package Scheduler;

import InfoNeeded.CourseActivity;
import InfoNeeded.Section;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.variables.IntVar;

import java.util.ArrayList;

public class Scheduler {
    private ArrayList<CourseActivity> courseActivities;
    private int size;

    public void initialize(ArrayList<CourseActivity> courseActivities){
        this.courseActivities = courseActivities;
        size = courseActivities.size();
    }

    // input: nothing, output: a list of sections chosen representing a solution
    // values of each variable represent sections (each is an index for Sections field in CourseActivities)
    public ArrayList<Section> generateSchedule(){
        Model model = new Model("Scheduler");

        //generate a variable for each CourseActivity
        IntVar[] cas = new IntVar[size];
        for (int i = 0; i<size; i++){
            cas[i] = model.intVar("CA_"+i,0,courseActivities.get(i).getSections().size()-1);
        }

        // add the constraint between each pair of variable
        for (int i = 0; i<size; i++){
            for (int j = i;j<size; j++){
                Constraint c = new Constraint("NoTimeConflict", new NoTimeConflict(cas[i],cas[j],courseActivities));
                c.post();
            }
        }

        //TODO:  model.solve   print variable to test,  in NTC.propagate( ): remove a section which conflict with all sections of the other variable


        return null;
    }

    public ArrayList<CourseActivity> getCourseActivities() {
        return courseActivities;
    }

    public void setCourseActivities(ArrayList<CourseActivity> courseActivities) {
        this.courseActivities = courseActivities;
    }

    public int getSize() {
        return size;
    }
}
