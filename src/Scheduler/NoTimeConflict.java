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
    int index_x;
    int index_y;

    public NoTimeConflict(IntVar x, IntVar y, ArrayList<CourseActivity> cas){
        super(new IntVar[]{x,y}, PropagatorPriority.BINARY, false );
        courseActivities = cas;
        //obtain the index of the variable in courseActivities from its name (n in "CA_n")
        index_x = Integer.parseInt(vars[0].getName().split("_")[1]);
        index_y = Integer.parseInt(vars[1].getName().split("_")[1]);
    }

    @Override
    //this function removes from its variables domain, values that cannot belong to any solutions
    // all values are possible to exist in some solutions
    public void propagate(int i) throws ContradictionException {

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
