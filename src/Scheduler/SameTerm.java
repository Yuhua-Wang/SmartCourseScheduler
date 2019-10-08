package Scheduler;

import InfoNeeded.CourseActivity;
import org.chocosolver.solver.constraints.Propagator;
import org.chocosolver.solver.constraints.PropagatorPriority;
import org.chocosolver.solver.exception.ContradictionException;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.ESat;

import java.util.ArrayList;

public class SameTerm extends Propagator<IntVar> {
    private ArrayList<CourseActivity> courseActivities;
    // index of CourseActivity represented by the variables in courseActivities
    private int index_x;
    private int index_y;

    public SameTerm(IntVar x, IntVar y, ArrayList<CourseActivity> cas){
        super(new IntVar[]{x,y}, PropagatorPriority.LINEAR, false );
        courseActivities = cas;
        //obtain the index of the variable in courseActivities from its name (n in "CA_n")
        index_x = Integer.parseInt(vars[0].getName().split("_")[1]);
        index_y = Integer.parseInt(vars[1].getName().split("_")[1]);
    }
    @Override
    public void propagate(int i) throws ContradictionException {

    }

    @Override
    public ESat isEntailed() {
        return null;
    }
}
