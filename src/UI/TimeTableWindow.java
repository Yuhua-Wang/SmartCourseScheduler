package UI;

import InfoNeeded.Section;
import Support.ClassTime;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Vector;

import static java.time.DayOfWeek.*;

public class TimeTableWindow extends UI {


    public TimeTableWindow() throws IOException {
        super();
    }

    @Override
    protected void initialize() {
        initializeTable();
    }

    private void initializeTable(){
        newTable(new ArrayList<Section>());
    }

    public JScrollPane newTable(ArrayList<Section> sections){
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

        setCourseOnTable(model, sections);
        
        JTable table = new JTable(model){
            public Component prepareRenderer (TableCellRenderer renderer, int rowIndex, int columnIndex){
                Component component = super.prepareRenderer(renderer, rowIndex, columnIndex);
                System.out.println(getValueAt(rowIndex,columnIndex));
                if(columnIndex==0 || getValueAt(rowIndex,columnIndex) == null) {
                    component.setBackground(Color.WHITE);
                } else {
                    component.setBackground(new Color(0x00E12C));
                }
                return component;
            }
        };
        table.setEnabled(false);

        //model.setValueAt("aa",1,2);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);
        setLocation(frame, scrollPane, 0.5, 0.5, 0.8, 0.63);

        return scrollPane;
    }

    private void setCourseOnTable (DefaultTableModel model, ArrayList<Section> sections){
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

                model.setValueAt(s.getCourseName()+"   "+s.getTitle(), s_row, col);
                for(int i=s_row+1; i<=e_row; i++){
                    model.setValueAt("", i, col);
                }
            }
        }

    }


    protected void initializeButton() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
