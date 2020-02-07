package data;

import InfoNeeded.Course;
import InfoNeeded.CourseActivity;
import InfoNeeded.Section;
import Support.Activity;
import Support.ClassTime;
import javafx.util.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import static Support.Term.*;

public class SSCData {

    private String myURL;
    private String profURL;
    private String type;
    private String day;
    private String courseTitle;
    private ArrayList<ClassTime> classTime;
    private String comments;
    private Elements temp1;
    private Elements temp2;
    Elements result;
    private LocalTime startTime;
    private LocalTime endTime;
    private DayOfWeek dayOfWeek;
    private ArrayList<CourseActivity> courseActivities;
    private ArrayList<CourseActivity> totalCourseActivities;
    private int max_retry = 5;

    public ArrayList allInfo(ArrayList<Pair<String, String>> courseInput){
        totalCourseActivities = new ArrayList<>();
            for (int a = 0; a < courseInput.size(); a++) {
                String name = courseInput.get(a).getKey();
                String number = courseInput.get(a).getValue();
                Course courseN = new Course(name + " " + number);
                dataScraping(courseN.findCourseURL(name,number));
                getSections();
                for (int i=0; i<courseActivities.size(); i++){
                    totalCourseActivities.add(courseActivities.get(i));
                }
            }
        return totalCourseActivities;
    }


    public void dataScraping(String url) {
        myURL = url;
        profURL = splitURL(myURL);
        try {
            Document doc = Jsoup.connect(myURL).get();
            //get course title
            courseTitle = doc.title().split("-")[0];

            // interleaving two sections from online,
            // after this step all sections of a course would be listed in order
            temp1 = doc.select(".section1");
            temp2 = doc.select(".section2");
            Iterator<Element> l1 = temp1.iterator();
            Iterator<Element> l2 = temp2.iterator();
            result = new Elements();
            while (l1.hasNext() || l2.hasNext()) {
                if (l1.hasNext()) {
                    result.add(l1.next());
                }
                if (l2.hasNext()) {
                    result.add(l2.next());
                }
            }
        } catch (IOException e) {
            if (max_retry > 0){
                System.out.println("retry......");
                dataScraping(url);
            } else {
                e.printStackTrace();
            }
        }
    }

    public String getURL() {
        return myURL;
    }

    public String getProfURL() {
        return profURL;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    //return a list of sections
    public ArrayList getSections() {
        ArrayList<Section> my_list = new ArrayList<>();
        //format of full year course
        if (result.get(0).child(3).text().length() > 1 || result.get(1).child(1).text().isEmpty()) {
            fullYearCourse(my_list);
        } else {
            termCourse(my_list);
        }
        return my_list;
    }

    public ArrayList fullYearCourse(ArrayList<Section> list) {
        for (int k = 0; k < result.size(); k += 2) {
            Section mySection = null;
            try {
                mySection = setSection(k);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mySection.setTerm(YEAR_TERM);
            setActivity(mySection,k);
            list.add(mySection);
        }
        return list;
    }

    public ArrayList termCourse(ArrayList<Section> list) {
        classTime = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            try {
                Section mySection = setSection(i);
                String curTerm = result.get(i).child(3).text();
                if (Integer.parseInt(curTerm) == 1) {
                    mySection.setTerm(TERM_1);
                } else {
                    mySection.setTerm(TERM_2);
                }
                setActivity(mySection,i);

                //TODO:
                // wildcard characters such as APSC182, two labs should have no time conflicts,
                // need to come back for that

                String dayOfWeek = result.get(i).child(5).text();
                String[] weekDays = dayOfWeek.split(" ");
                for(int k=0; k<weekDays.length; k++) {
                    String[] startHr = result.get(i).child(6).text().split(":");
                    String[] endHr = result.get(i).child(7).text().split(":");
                    startTime = LocalTime.of(Integer.parseInt(startHr[0]), Integer.parseInt(startHr[1]));
                    endTime = LocalTime.of(Integer.parseInt(endHr[0]), Integer.parseInt(endHr[1]));
                    mySection.addClassTime(createClassTime(weekDays[k],startTime,endTime));
                }
               // created a list of sections of a course, with different properties
                if (mySection.getActType() != Activity.WAITINGLIST) {
                    list.add(mySection);
                }
            } catch (Exception e) {
            i--;
        }
        }
        courseActivities = findNumActType(list);
        //System.out.println(courseActivities.get(0).getActivity());
        return courseActivities;
    }

    public Section setSection(int index) throws Exception {
        String profName;
        Section mySection = new Section();
        String curString = result.get(index).child(1).text();
        mySection.setTitle((curString + " ").split(" ")[2]);
        mySection.setCourseName((curString + " ").split(" ")[0]+" "+(curString + " ").split(" ")[1]);
        //System.out.println(mySection.getCourseName());
        mySection.setProfURL(profURL);
        day = findDay(mySection.getTitle());
        return mySection;
    }

    public ArrayList<CourseActivity> findNumActType(ArrayList<Section> list){
        ArrayList<CourseActivity> courseActivities = new ArrayList<>();
        String name = list.get(0).getCourseName();
        int numSections = list.size();
        HashSet set = new HashSet();
        for (int i=0; i<numSections; i++){
            set.add(list.get(i).getActType());
        }
        CourseActivity courseActivity1 = new CourseActivity(name,list.get(0).getActType());
        courseActivity1.addSection(list.get(0));
        courseActivities.add(courseActivity1);
        set.remove(list.get(0).getActType());
        for (int a=1; a<numSections; a++){
            if (!set.contains(list.get(a).getActType())){
                for (int b=0; b<courseActivities.size(); b++){
                    if (courseActivities.get(b).getActivity()==list.get(a).getActType()){
                        courseActivities.get(b).addSection(list.get(a));
                    }
                }
            }
            else{
                CourseActivity courseActivity2 = new CourseActivity(name,list.get(a).getActType());
                courseActivity2.addSection(list.get(a));
                courseActivities.add(courseActivity2);
                set.remove(list.get(a).getActType());
            }
        }
        return courseActivities;
    }

    public String splitURL(String thatURL) {
        String[] split0 = thatURL.split("-");
        String[] split1 = split0[1].split("&");
        return (split0[0] + "-section&" + split1[1] + "&" + split1[2] + "&section=");
    }

    public String findDay(String sectionNum) throws IOException {
        Document dc = Jsoup.connect(profURL + sectionNum).get();
        Elements body = dc.select(".table.table-striped tr");
        String thisDay = body.get(1).child(1).text();
        return thisDay;
    }

    public void setActivity(Section section, int i){
        String activityType = result.get(i).child(2).text().toUpperCase().replaceAll("\\s+","");
        section.setActType(Activity.valueOf(activityType));
      //  System.out.println(section.getActType());
    }

    public ClassTime createClassTime(String day, LocalTime start, LocalTime end){
        if (day.contains("M")){
            return new ClassTime(DayOfWeek.MONDAY,start,end);
        } else if (day.contains("T")&&day.contains("e")){
            return new ClassTime(DayOfWeek.TUESDAY,start,end);
        } else if (day.contains("W")){
            return new ClassTime(DayOfWeek.WEDNESDAY,start,end);
        } else if (day.contains("T")&&day.contains("h")){
            return new ClassTime(DayOfWeek.THURSDAY,start,end);
        } else if (day.contains("F")){
            return new ClassTime(DayOfWeek.FRIDAY,start,end);
        } else if (day.contains("S")&&day.contains("a")){
            return new ClassTime(DayOfWeek.SATURDAY,start,end);
        } else {
            return new ClassTime(DayOfWeek.SUNDAY,start,end);
        }
    }

//    public String findProf(String url, String sectionNum) throws IOException {
//        String name = "";
//        String theURL = url + sectionNum;
//
//        Document dc = Jsoup.connect(theURL).get();
//        Elements body = dc.select("table tr td");
//        try {
//            //ignore the first "numbers" to extract full prof name
//            //for more than 1 prof, currently put their names in one string, need to split for later use
//            Elements b = body.select("a");
//            //if there is no prof element: 1. no room number/prof 2. there is only room number,
//            //then the number is less than 4 char long
//            if (b.size()==0||b.last().text().length()<4) {
//                name = "Ooooops, prof is unavailable";
//            } else {
//                Element a = b.last();
//                String[] splitLastName = a.text().split(",");
//                // when there are more than one ",", there must be more than one prof
//                if (splitLastName.length > 2) {
//                    name = moreThanOneProf();
//                } else {
//                        name = oneProf(splitLastName[0])+oneProf(splitLastName[1]);
//                    }
//            }
////            System.out.println(name);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return name;
//    }
//
//    public String moreThanOneProf() {
//        return "";
//    }
//
//    // when there is a single prof, turn his/her name into the form of last_name+first_name
//    public String oneProf(String name) {
//        String profName = "";
//        String[] splitName = name.split(" ");
//        for (int i = 0; i < splitName.length; i++) {
//            if (i == splitName.length - 1) {
//                profName += splitName[i];
//            } else {
//                profName += splitName[i] + "+";
//            }
//        }
//        return profName;
//    }
}
