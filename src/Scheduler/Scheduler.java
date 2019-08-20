package Scheduler;

import InfoNeeded.CourseActivity;
import InfoNeeded.Section;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.variables.IntVar;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private ArrayList<CourseActivity> courseActivities;
    private int size;

    public Scheduler(ArrayList<CourseActivity> courseActivities){
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
        for (int i = 0; i<size-1; i++){
            for (int j = i+1;j<size; j++){
                Constraint c = new Constraint("NoTimeConflict", new NoTimeConflict(cas[i],cas[j],courseActivities));
                model.post(c);
            }
        }

        List<Solution> solutions = model.getSolver().findAllSolutions();
        long solutionCount = model.getSolver().getSolutionCount();
        if (solutionCount != 0){
            System.out.println(solutionCount + " Solutions Found");
            for (Solution s : solutions) {
                System.out.println(" ---------------------------- ");
                for (int i = 0; i<cas.length; i++){
                    System.out.println(s.getIntVal(cas[i]));
                }
            }
        } else {
            System.out.println("No Possible Schedule exists");
        }



        //TODO: return Sections

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
