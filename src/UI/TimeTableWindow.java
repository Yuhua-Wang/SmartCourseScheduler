package UI;

import InfoNeeded.Section;
import Support.ClassTime;
import javafx.util.Pair;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import static Support.Term.*;

public class TimeTableWindow extends UI {
    private ArrayList<Pair<JScrollPane, JScrollPane>> tables;
    private JLabel label;
    private int counter;

    public TimeTableWindow() throws IOException {
        super(1600, 800, 100, 100);
    }

    @Override
    protected void initialize() {
        counter = 0;
        //initializeTable(testing);
        //displayTable();
        //initializeButton();
        initializeLabels();

    }

    //TODO: change all functions below to private

    public void displayTable(){
        tables.get(counter).getKey().setVisible(true);
        tables.get(counter).getValue().setVisible(true);
    }

    public void undisplayTable(){
        tables.get(counter).getKey().setVisible(false);
        tables.get(counter).getValue().setVisible(false);
    }

    public void initializeTable( ArrayList<ArrayList<Section>> courseCombinations){
        tables = new ArrayList<>();
        for (ArrayList<Section> sections: courseCombinations){
            tables.add(newTable(sections));
        }
    }

    public Pair<JScrollPane, JScrollPane> newTable(ArrayList<Section> sections){

        final DefaultTableModel term1 = creatModelForTable();
        final DefaultTableModel term2 = creatModelForTable();

        setCourseOnModle(term1, term2, sections);


        JTable table1 = new JTable(term1){
            public Component prepareRenderer (TableCellRenderer renderer, int rowIndex, int columnIndex){
                Component component = super.prepareRenderer(renderer, rowIndex, columnIndex);
                if(columnIndex==0 || getValueAt(rowIndex,columnIndex) == null) {
                    component.setBackground(Color.WHITE);
                } else {
                    component.setBackground(new Color(0x00E12C));
                }
                return component;
            }
        };
        JTable table2 = new JTable(term2){
            public Component prepareRenderer (TableCellRenderer renderer, int rowIndex, int columnIndex){
                Component component = super.prepareRenderer(renderer, rowIndex, columnIndex);
                if(columnIndex==0 || getValueAt(rowIndex,columnIndex) == null) {
                    component.setBackground(Color.WHITE);
                } else {
                    component.setBackground(new Color(0x00E12C));
                }
                return component;
            }
        };


        table1.setEnabled(false);
        table2.setEnabled(false);

        //model.setValueAt("aa",1,2);
        JScrollPane scrollPane1 = new JScrollPane(table1);
        JScrollPane scrollPane2 = new JScrollPane(table2);
        scrollPane1.setVisible(false);
        scrollPane2.setVisible(false);
        frame.add(scrollPane1);
        frame.add(scrollPane2);
        setLocation(frame, scrollPane1, 0.25, 0.5, 0.4, 0.63);
        setLocation(frame, scrollPane2, 0.75, 0.5, 0.4, 0.63);
        Pair<JScrollPane, JScrollPane> pair = new Pair<>(scrollPane1, scrollPane2);

        return pair;
    }

    private void setCourseOnModle(DefaultTableModel term1, DefaultTableModel term2, ArrayList<Section> sections){
        for (Section s : sections) {
            for (ClassTime c : s.getClassTime()) {
                int col = c.getDayOfWeek().getValue();
                int s_row;
                int e_row;
                int s_hour = c.getStartTime().getHour();
                int s_min = c.getStartTime().getMinute();
                int e_hour = c.getEndTime().getHour();
                int e_min = c.getEndTime().getMinute();

                if (s_min != 0){
                    s_row = (s_hour - 7)*2 + 1;
                } else {
                    s_row = (s_hour - 7)*2;
                }

                if (e_min != 0){
                    e_row = (e_hour - 7)*2 + 1;
                } else {
                    e_row = (e_hour - 7)*2;
                }

                if (s.getTerm() == TERM_1){
                    setValue(term1, s, s_row, col, e_row);
                } else if (s.getTerm() == TERM_2){
                    setValue(term2, s, s_row, col, e_row);
                } else {
                    setValue(term1, s, s_row, col, e_row);
                    setValue(term2, s, s_row, col, e_row);
                }
            }
        }
    }

    private void setValue (DefaultTableModel model, Section s, int s_row, int col, int e_row){
        model.setValueAt(s.getCourseName()+"   "+s.getTitle(), s_row, col);
        for(int i=s_row+1; i<=e_row; i++){
            model.setValueAt("", i, col);
        }
        for(int i=s_row+1; i<=e_row; i++){
            model.setValueAt("", i, col);
        }
    }

    private DefaultTableModel creatModelForTable(){
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
            String[] rowData = { time, null, null, null, null, null};
            model.addRow(rowData);
        }

        return model;
    }

    public void initializeButton() {
        JButton previous = createButton("<- Previous",0.2, 0.05, 0.1,  0.05);
        JButton next = createButton("Next ->",0.8,0.05, 0.1, 0.05);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("<- Previous")){
            if (counter > 0){
                undisplayTable();
                counter--;
                displayTable();
                updateLabels();
            }
        }
        else  if(e.getActionCommand().equals("Next ->")){
            if (counter < tables.size()-1){
                undisplayTable();
                counter++;
                displayTable();
                updateLabels();
            }
        }
    }

    private void initializeLabels (){
        label = createLabel("Course Arrangement "+ Integer.toString(counter+1), 0.54, 0.05, 0.2, 0.05, 24);
        frame.add(label);
    }

    private void updateLabels(){
        label.setText("Course Arrangement "+ Integer.toString(counter+1));
    }

}
