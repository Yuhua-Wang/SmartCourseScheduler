package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Time;

public class InputWindow extends UI {
    private double y = 0.3;
    //private JPanel panel = new JPanel();
    public InputWindow() throws IOException {
        initialize();
        //frame.add(panel);

    }

    @Override
    protected void initialize() {
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
            JTextField def = createTextField(" ", 0.2, y, 0.2, 0.05, 20);
            JTextField abc = createTextField(" ", 0.4, y, 0.2, 0.05, 20);
            frame.setSize(frame.getSize());
            y += 0.05;
            }
        if (e.getActionCommand().equals("-")){

        }
        if (e.getActionCommand().equals("->")){
            try {
                new TimeTableWindow();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            frame.dispose();
        }
        }

    protected void initializeTextfield(){
        JTextField courseDepartment = createTextField("eg. CPSC",0.2,0.25,0.2,0.05,20);
        JTextField courseNumber = createTextField("eg. 304",0.4,0.25,0.2,0.05,20);

    }

    protected void initializeLabel(){
        JLabel topLabel = createLabel("Please enter your courses: ", 0.55, 0.1, 0.5, 0.1, 30);
        JLabel subjectLabel = createLabel("Subject Area", 0.35, 0.2, 0.5, 0.1, 20);
        JLabel courseLabel = createLabel("Course Number", 0.55, 0.2, 0.5, 0.1, 20);

    }

}
