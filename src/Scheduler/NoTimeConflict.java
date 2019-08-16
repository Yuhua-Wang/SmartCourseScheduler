package Scheduler;

import InfoNeeded.CourseActivity;
import InfoNeeded.Section;
import org.chocosolver.solver.constraints.Propagator;
import org.chocosolver.solver.constraints.PropagatorPriority;
import org.chocosolver.solver.exception.ContradictionException;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.ESat;

import java.util.ArrayList;

// a constraint requiring no time conflict between the 2 variables
public class NoTimeConflict extends Propagator<IntVar> {
    private ArrayList<CourseActivity> courseActivities;
    // index of CourseActivity represented by the variables in courseActivities
    private int index_x;
    private int index_y;

    public NoTimeConflict(IntVar x, IntVar y, ArrayList<CourseActivity> cas){
        super(new IntVar[]{x,y}, PropagatorPriority.BINARY, false );
        courseActivities = cas;
        //obtain the index of the variable in courseActivities from its name (n in "CA_n")
        index_x = Integer.parseInt(vars[0].getName().split("_")[1]);
        index_y = Integer.parseInt(vars[1].getName().split("_")[1]);
    }

    @Override
    //this function removes from its variables domain, values that cannot belong to any solutions
    // remove a section which conflict which all sections of the other variable
    public void propagate(int i) throws ContradictionException {

        for(int a = 0; a < courseActivities.get(0).getSections().size(); a++){
            // first check if a is in the domain of the first variable
            if (vars[0].contains(a)){
                // if it is, get the corresponding section represented by a
                Section s1 = courseActivities.get(0).getSections().get(a);
                boolean hasPossibleSolution = false;
                // check if it conflicts with each section of the second variable
                for (Section s2 : courseActivities.get(1)){
                    if (!s1.hasTimeConflict(s2)){
                        hasPossibleSolution = true;
                    }
                }
                // remove this section/value from the domain if it conflicts with each section of the second variable
                if (!hasPossibleSolution){
                    vars[0].removeValue(a, this);
                }
            }
        }

        // do the same for the second variable
        for(int b = 0; b < courseActivities.get(1).getSections().size(); b++){
            if (vars[1].contains(b)){
                Section s2 = courseActivities.get(1).getSections().get(b);
                boolean hasPossibleSolution = false;
                for (Section s1 : courseActivities.get(0)){
                    if (!s2.hasTimeConflict(s2)){
                        hasPossibleSolution = true;
                    }
                }
                if (!hasPossibleSolution){
                    vars[1].removeValue(b, this);
                }
            }
        }
    }

    @Override
    public ESat isEntailed() {
        boolean hasConflicts = false;
        boolean hasNoConflicts = false;

        //check if each combination has time conflict or not
        for (Section s1 : courseActivities.get(index_x)) {
            for (Section s2: courseActivities.get(index_y)) {
                if (s1.hasTimeConflict(s2)){
                    hasConflicts = true;
                } else {
                    hasNoConflicts = true;
                }
            }
        }

        if (hasConflicts && hasNoConflicts){
            return ESat.UNDEFINED;
        } else if (hasConflicts){
            return ESat.FALSE;
        }
        return ESat.TRUE;
    }
}
