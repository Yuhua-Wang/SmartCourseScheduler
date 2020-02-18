package UI;

import Exceptions.PoorInternetConnectionException;
import InfoNeeded.CourseActivity;
import Scheduler.Scheduler;
import Exceptions.NoScheduleException;
import data.SSCData;
import javafx.util.Pair;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;


import static javax.swing.JOptionPane.*;

public class InputWindow extends UI {
    private ArrayList<Pair<String,String>> pairArrayList;
    private double inputText_y = 0.25;
    private int maxCourses = 12;
    private ArrayList<JTextField> listText;
    //private JPanel panel = new JPanel();
    public InputWindow() throws IOException {
        initialize();
        //frame.add(panel);
        listText = new ArrayList<>();

    }

    @Override
    protected void initialize() {
        pairArrayList = new ArrayList<>();
        initializeButton();
        //initializeTextfield();
        initializeLabel();
    }


    protected void initializeButton() {
        JButton add = createButton("Add",0.65, 0.25, 0.2,  0.05);
        JButton remove = createButton("Remove", 0.85,0.25,0.2,0.05);
        JButton nextPage = createButton("Generate",0.8,0.8,0.3,0.05);
        //JButton save = createButton("save",0.2,0.8,0.1,0.05);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add")) {
            if (maxCourses > 0){
                listText.add(createTextField(" ", 0.2, inputText_y, 0.2, 0.05, 20));
                listText.add(createTextField(" ", 0.4, inputText_y, 0.2, 0.05, 20));
                frame.setSize(frame.getSize());
                inputText_y += 0.05;
                maxCourses --;
            } else {
                JOptionPane.showMessageDialog(frame, " Maximum Number of Courses Reached!\n" + " No more than 6 courses are permitted per term",
                        "Maximum number of courses reached", DEFAULT_OPTION);
            }
        }
        if (e.getActionCommand().equals("Remove")) {
            if (!listText.isEmpty()) {
                JTextField remove = listText.get(listText.size() - 1);
                JTextField remove1 = listText.get(listText.size() - 2);
                remove.setVisible(false);
                remove1.setVisible(false);
                inputText_y -= 0.05;
                listText.remove(remove);
                listText.remove(remove1);
                maxCourses ++;
            }else {
                return;
                }
            }

        // commented out: "Generate" button would now automatically save
//        if (e.getActionCommand().equals("save")){
//            for (int i = 0; i < listText.size(); i+=2){
//                    Pair<String,String> pair = new Pair<>(listText.get(i).getText().trim().toUpperCase() ,
//                            listText.get(i+1).getText().trim().toUpperCase() );
//                    pairArrayList.add(pair);
//                }
//            System.out.println(pairArrayList.size());
//            for (int i = 0; i < pairArrayList.size(); i++){
//                System.out.println(pairArrayList.get(i));
//
//            }
//        }
        if (e.getActionCommand().equals("Generate")) {
            saveText();
            ProcessingWindow pw = new ProcessingWindow();

            // Another thread is created because sscData.allInfo is task-heavy and can block ProcessingWindow's GUI thread
            final ArrayList<Pair<String,String>> temp = pairArrayList;
            new Thread(() -> {
                try {

                    SSCData sscData = new SSCData();
                    ArrayList<CourseActivity> request = sscData.allInfo(temp);
                    Scheduler scheduler = new Scheduler(request);
                    new TimeTableWindow(scheduler.generateSchedule());
                    pw.dispose();

                } catch (NoScheduleException ex){
                    showConfirmDialog(frame, "No Course Schedule Possible\n" + "Change a course and try again!",
                            "No Course Schedule Possible", DEFAULT_OPTION, WARNING_MESSAGE);
                } catch (PoorInternetConnectionException ex){
                    showConfirmDialog(frame, "Cannot Connect to SSC Server\n" + "Check your internet connection and try again!",
                            "Cannot Connect to SSC Server", DEFAULT_OPTION, WARNING_MESSAGE);
                } catch (IndexOutOfBoundsException ex){
                    showConfirmDialog(frame, "Course Not Exits\n" + "Make sure if you have entered the right subject and course number!",
                            "Course Not Exits", DEFAULT_OPTION, WARNING_MESSAGE);
                } catch (Exception e1) {
                    showConfirmDialog(frame, "Congratulation! You Discovered a Bug!!\n" + "Report to us to earn a \" Thank You \" ! \n",
                            "Course Not Exits", DEFAULT_OPTION, WARNING_MESSAGE);
                    e1.printStackTrace();
                }

            }).start();

            // clean the pairArrayList for new
            pairArrayList = new ArrayList<>();
            //frame.dispose();
        }

    }

    // this function is not called
    protected void initializeTextfield(){
        JTextField courseDepartment = createTextField("CPSC",0.2,0.25,0.2,0.05,20);
        JTextField courseNumber = createTextField("304",0.4,0.25,0.2,0.05,20);
        Pair<String,String> pair = new Pair<>(courseDepartment.getText(),courseNumber.getText());
        pairArrayList.add(pair);
        System.out.println(pairArrayList.get(0));

    }

    protected void initializeLabel(){
        JLabel topLabel = createLabel("Please enter your courses: ", 0.55, 0.1, 0.5, 0.1, 30);
        JLabel subjectLabel = createLabel("Subject Area", 0.35, 0.2, 0.5, 0.1, 20);
        JLabel courseLabel = createLabel("Course Number", 0.55, 0.2, 0.5, 0.1, 20);


    }

    private void saveText(){
        for (int i = 0; i < listText.size(); i+=2){
            String subject = listText.get(i).getText().trim().toUpperCase();
            String number = listText.get(i+1).getText().trim().toUpperCase();
            Pair<String,String> pair = new Pair<>(subject, number);
            if(! (pairArrayList.contains(pair) || subject.isEmpty() || number.isEmpty()) ){
                pairArrayList.add(pair);
            }
        }
        System.out.println("Number of Courses: " + pairArrayList.size());
        for (int i = 0; i < pairArrayList.size(); i++){
            System.out.println(pairArrayList.get(i));
        }
    }

}
