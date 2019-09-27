package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;

public class MenuWindow extends UI {
    private int frameWidth = 800;
    private int frameHeight = 600;

    public MenuWindow() throws IOException {
        super();
        initializeLabel();
        frame.setSize(frameWidth,frameHeight);
    }

    @Override
    protected void initialize() {
        initializeFrame();
        initializeButton();
        initializeDialog();
    }


    protected void initializeButton() {
        JButton start = createButton("Start",0.5, 0.4, 0.5,  0.2);
        JButton exit = createButton("Exit",0.5,0.7, 0.5, 0.2);

    }

    protected void initializeLabel(){
        JLabel welcome = createLabel("SmartScheluler For UBC", 0.55, 0.1, 0.5, 0.1, 30);
        //JTextField welcome = createTextField("SmartScheluler For UBC", 0.5, 0.1, 0.5, 0.1, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Start")){
            try {
                new InputWindow();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            frame.dispose();
        }
        else  if(e.getActionCommand().equals("Exit")){
            confirmExit();
        }
    }
}
