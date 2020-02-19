package Scheduler;

import InfoNeeded.CourseActivity;
import InfoNeeded.Section;
import Exceptions.NoScheduleException;
import Support.Activity;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.variables.IntVar;

import java.util.*;

public class Scheduler {
    private ArrayList<CourseActivity> courseActivities;
    private HashMap<String, ArrayList<Integer>> coursesAndTheirActivities;
    private int size;

    public Scheduler(ArrayList<CourseActivity> courseActivities){
        coursesAndTheirActivities = new HashMap<>();
        // remove duplicate sections
        this.courseActivities = courseActivities;
        for (CourseActivity ca : this.courseActivities){
            ca.removeDuplicateSections();
        }
        size = courseActivities.size();
    }

    // input: nothing, output: a list of sections chosen representing a solution
    // values of each variable represent sections (each is an index for Sections field in CourseActivities)
    public Hashtable<HashSet<Section>, ArrayList<ArrayList<Section>>> generateSchedule() throws NoScheduleException {
        Model model = new Model("Scheduler");

        //generate a variable for each CourseActivity
        IntVar[] caVars = new IntVar[size];
        for (int i = 0; i<size; i++){
            caVars[i] = model.intVar("CA_"+i,0,courseActivities.get(i).getSections().size()-1);

            // build a hashmap of <Course Title, list of course activities (index) belongs to it>
            coursesAndTheirActivities.putIfAbsent(courseActivities.get(i).getTitle(), new ArrayList<Integer>());
            coursesAndTheirActivities.get(courseActivities.get(i).getTitle()).add(i);
        }

        // Add Constraints: there should be no time conflict between each course activity
        for (int i = 0; i<size-1; i++){
            for (int j = i+1;j<size; j++){
                Constraint c = new Constraint("NoTimeConflict", new NoTimeConflict(caVars[i],caVars[j],courseActivities));
                model.post(c);
            }
        }

        // Add Constraints: All course activities of a course should be in the same term
        // get a list of CourseActivities (index) organized by Course
        ArrayList<ArrayList<Integer>> caByCourse = new ArrayList<>(coursesAndTheirActivities.values());
        for (ArrayList<Integer> caInOneCourse: caByCourse){
            for (int j=0; j<caInOneCourse.size()-1; j++){
                for (int k = j+1;k<caInOneCourse.size(); k++){
                    int ca1 = caInOneCourse.get(j);
                    int ca2 = caInOneCourse.get(k);
                    Constraint c = new Constraint("SameTerm", new SameTerm(caVars[ca1],caVars[ca2],courseActivities));
                    model.post(c);
                }
            }
        }

//        // generate solutions
////        ArrayList<ArrayList<Section>> timetables = new ArrayList<>();
////        List<Solution> solutions = model.getSolver().findAllSolutions();
////        long solutionCount = model.getSolver().getSolutionCount();
////        if (solutionCount != 0){
////            System.out.println(solutionCount + " Solutions Found");
////            for (Solution s : solutions) {
////                ArrayList<Section> timetable = new ArrayList<>();
////                for (int i = 0; i<caVars.length; i++){
////                    timetable.add(courseActivities.get(i).getSections().get(s.getIntVal(caVars[i])));
////                    //System.out.println(s.getIntVal(caVars[i]));
////                    //System.out.println(courseActivities.get(i).getSections().get(s.getIntVal(caVars[i])));
////                }
////
////                timetables.add(timetable);

        // generate solutions
        //         Set of Lectures, List of timetables
        Hashtable<HashSet<Section>, ArrayList<ArrayList<Section>>> groupedTimetables = new Hashtable<>();
        List<Solution> solutions = model.getSolver().findAllSolutions();
        long solutionCount = model.getSolver().getSolutionCount();
        if (solutionCount != 0){
            System.out.println(solutionCount + " Solutions Found");

            for (Solution s : solutions) {
                // timetable contains a list of all sections in this solution(schedule)
                ArrayList<Section> timetable = new ArrayList<>();
                // lectures contains a set of all lecture sections in this solution(schedule)
                HashSet<Section> lectures = new HashSet<>();
                for (int i = 0; i<caVars.length; i++){
                    Section temp = courseActivities.get(i).getSections().get(s.getIntVal(caVars[i]));
                    if (temp.getActType() == Activity.LECTURE){
                        lectures.add(temp);
                    }
                    timetable.add(temp);
                }

                if (! groupedTimetables.containsKey(lectures)){
                    groupedTimetables.put(lectures, new ArrayList<>());
                }
                groupedTimetables.get(lectures).add(timetable);
            }

        } else {
            throw new NoScheduleException("No Possible Schedule exists");
        }

        return groupedTimetables;
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
