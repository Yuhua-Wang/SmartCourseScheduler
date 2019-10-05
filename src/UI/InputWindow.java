package UI;

import InfoNeeded.Course;
import InfoNeeded.CourseActivity;
import InfoNeeded.Section;
import Scheduler.Scheduler;
import data.SSCData;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

public class InputWindow extends UI {
    private ArrayList<Pair<String,String>> pairArrayList;
    private double y = 0.3;
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
        initializeTextfield();
        initializeLabel();
    }


    protected void initializeButton() {
        JButton add = createButton("+",0.7, 0.25, 0.1,  0.05);
        JButton remove = createButton("-", 0.8,0.25,0.1,0.05);
        JButton nextPage = createButton("->",0.8,0.8,0.1,0.05);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("+")) {
            listText.add(createTextField(" ", 0.2, y, 0.2, 0.05, 20));
            listText.add(createTextField(" ", 0.4, y, 0.2, 0.05, 20));
            frame.setSize(frame.getSize());
            y += 0.05;
        }
        if (e.getActionCommand().equals("-")) {
            if (!listText.isEmpty()) {
                JTextField remove = listText.get(listText.size() - 1);
                JTextField remove1 = listText.get(listText.size() - 2);
                remove.setVisible(false);
                remove1.setVisible(false);
                y -= 0.05;
                listText.remove(remove);
                listText.remove(remove1);
            }else {
                return;
                }
            }
            if (e.getActionCommand().equals("->")) {
                try {
                    SSCData sscData = new SSCData();

                    System.out.println(getCourse().size());

                    ArrayList<CourseActivity> request = sscData.allInfo(getCourse());
                    Scheduler scheduler = new Scheduler(request);
                    new TimeTableWindow(scheduler.generateSchedule());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                frame.setVisible(false);
            }
    }

    public ArrayList<Pair<String,String>> getCourse (){
        for (int i = 0; i < listText.size()-1; i+=2){
            for (int j = 1; j < listText.size()-1; j+=2){
                Pair<String,String> pair = new Pair<>(listText.get(i).getText(),listText.get(j).getText());
                pairArrayList.add(pair);
            }
        }
        return pairArrayList;
    }

    protected void initializeTextfield(){
        JTextField courseDepartment = createTextField("CPSC",0.2,0.25,0.2,0.05,20);
        JTextField courseNumber = createTextField("317",0.4,0.25,0.2,0.05,20);
        Pair<String,String> pair = new Pair<>(courseDepartment.getText(),courseNumber.getText());
        pairArrayList.add(pair);

    }

    protected void initializeLabel(){
        JLabel topLabel = createLabel("Please enter your courses: ", 0.55, 0.1, 0.5, 0.1, 30);
        JLabel subjectLabel = createLabel("Subject Area", 0.35, 0.2, 0.5, 0.1, 20);
        JLabel courseLabel = createLabel("Course Number", 0.55, 0.2, 0.5, 0.1, 20);


    }

}
