package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Vector;

public class TimeTableWindow extends UI {


    public TimeTableWindow() throws IOException {
        super();
    }

    @Override
    protected void initialize() {
        initializeTable();
    }

    private void initializeTable(){
        newTable();
    }

    private JScrollPane newTable(){
        String[] columnNames = { " ", "Mon", "Tue", "Wed", "Thu", "Fri" };
        final DefaultTableModel model = new DefaultTableModel(new String[][]{}, columnNames);
        
        for (int i=0; i<30; i++){
            String time;
            if (i%2==0 && i<6){
                time = "0"+ (7+i/2) + ":" + "00";
            } else if (i%2==0){
                time = (7+i/2) + ":" + "00";
            } else if (i<6){
                time = "0" + (7+i/2) + ":" + "30";
            } else {
                time = (7+i/2) + ":" + "30";
            }
            String[] rowData = { time, "", "", "", "", "" };
            model.addRow(rowData);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);
        setLocation(frame, scrollPane, 0.5, 0.5, 0.8, 0.63);

        return scrollPane;
    }

    protected void initializeButton() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
