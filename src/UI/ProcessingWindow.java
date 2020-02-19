package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ProcessingWindow extends UI {
    private JProgressBar progressBar;

    public  ProcessingWindow(){
        super(600, 200, 500, 500);
        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        frame.add(progressBar);
        setLocation(frame, progressBar, 0.5, 0.38, 0.8, 0.15);
    }

    @Override
    protected void initialize() {
        createLabel("Processing: " + "this may take up to 2 minutes", 0.5, 0.1, 0.8, 0.15, 22);
        frame.setTitle("Processing");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void dispose(){
        frame.dispose();
    }
}
