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
        Object rowData[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3" },
                { "Row2-Column1", "Row2-Column2", "Row2-Column3" } };
        Object columnNames[] = { "Column One", "Column Two", "Column Three" };
        JTable table = new JTable(rowData, columnNames);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);
        setLocation(frame, scrollPane, 0.5, 0.5, 0.5, 0.5);
    }

    protected void initializeButton() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
