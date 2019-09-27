package UI;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;

public abstract class UI implements ActionListener {
    protected JFrame frame;
    private  JTextArea textArea;
    private int frameHeight = 800;
    private int frameWidth = 800;
    private int buttonHeight = 30;
    private int buttonWidth = 400;

    public UI() throws IOException {
        initializeFrame();
        initializeDialog();
        initialize();
    }

    public UI(int width, int height, int x, int y) throws IOException {
        initializeFrame(width, height, x, y);
        initializeDialog();
        initialize();
    }

    protected abstract void  initialize();


    protected  void initializeFrame(int width, int height, int x, int y) {
        frame = new JFrame("Simple Todo List");
        frame.setSize(width,height);
        frame.setLocation(x,y);
        frame.getContentPane().setBackground(Color.WHITE );
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmExit();
            }
        });
    }

    protected  void initializeFrame() {
        frame = new JFrame("Simple Todo List");
        frame.setSize(frameWidth,frameHeight);
        frame.setLocation(500,500);
        frame.getContentPane().setBackground(Color.WHITE );
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmExit();
            }
        });
    }

    protected void initializeTextArea(){
        textArea = new JTextArea(10, 40);
        textArea.setFont(new Font("Serif",Font.PLAIN,24));
        frame.add(textArea);
        //textArea.setBounds(frame.getInsets().left,frame.getInsets().top,800,400);

        JScrollPane scroll = new JScrollPane (textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        frame.add(scroll);
        scroll.setBounds(frame.getInsets().left,frame.getInsets().top,750,400);
    }

    protected void initializeDialog (){
        UIManager.put("OptionPane.minimumSize", new Dimension(600, 150));
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 18)));
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("Arial",Font.PLAIN,30)));
        UIManager.put("OptionPane.background", Color.white);
        UIManager.put("Button.background", Color.white);
    }

    //x_pos, y_pos, width, height should be a double from 0 to 1
    // They are NOT coordinates. They mean which part on the frame should the center of the button be at.
    // e.g. x_pos = 0.5, y_pos = 0.5 means the button should be at the center of the screen
    protected JButton createButton(String buttonLable,Double x_pos,Double y_pos, Double width, Double height){
        JButton button = new JButton(buttonLable);
        button.setFont(new Font("Arial", Font.PLAIN, 24));
        button.setActionCommand(buttonLable);
        button.addActionListener(this);
        button.setBackground(Color.WHITE);
        button.setOpaque(true);
        frame.add(button);
        setLocation(frame, button, x_pos, y_pos, width, height);
        //allowResize(frame, button, x_pos, y_pos, width, height);

        return button;
    }

    // determines the position on the frame of the component
    // x_pos, y_pos, width, height should be a double from 0 to 1
    // x_pos, y_pos are NOT coordinates. They mean which part on the frame should the center of the component be at.
    // width and height are NOT the component's actual width and height. They mean what portion of the frame's x y should the component occupy
    // e.g. x_pos = 0.5, y_pos = 0.5 means the button should be at the center of the screen
    protected void setLocation(JFrame frame, JComponent component, Double x_pos, Double y_pos, Double width, Double height){
        int[] dimension = determineDimention(frame, component, x_pos, y_pos, width, height);
        component.setBounds(dimension[0], dimension[1], dimension[2], dimension[3]);
        frame.addComponentListener( new ComponentAdapter() {
            @Override
            public void componentResized( ComponentEvent e ) {
                int[] dimension = determineDimention(frame, component, x_pos, y_pos, width, height);
                component.setBounds(dimension[0], dimension[1], dimension[2], dimension[3]);
            }
        } );
    }

    // dimension[0] = x coordinate, dimension[1] = y coordinate, [2] =width, [3] = height
    protected int[] determineDimention(JFrame frame, JComponent component, Double x_pos, Double y_pos, Double width, Double height) {
     int[] dimension = new int[4];
     int frameWidth = (int) Math.round(frame.getBounds().getWidth());
     int frameHeight = (int) Math.round(frame.getBounds().getHeight());
     dimension[2] = (int) Math.round(frameWidth * width);
     dimension[3] = (int) Math.round(frameHeight * height);
     dimension[0] = (int) Math.round(frameWidth*x_pos) - dimension[2]/2;
     dimension[1] = (int) Math.round(frameHeight*y_pos) - dimension[3]/2;
     return dimension;
    }

    protected JLabel createLabel(String label, Double x, Double y,Double width, Double height, int size){
        JLabel jLabel = new JLabel(label);
        jLabel.setFont(new Font("Serif",Font.PLAIN,size));
        frame.add(jLabel);
        setLocation(frame, jLabel, x, y,width,height);

        return jLabel;
    }

    protected JTextField createTextField(String text, Double x, Double y,Double width, Double height, int size){
        JTextField textField = new JTextField(text);
        textField.setFont(new Font("Serif",Font.PLAIN,size));
        frame.add(textField);
        setLocation(frame, textField, x, y,width,height);

        return textField;
    }

    protected void confirmExit(){
        //TODO: remove the line below after testing
        System.exit(0);
        if (JOptionPane.showConfirmDialog(frame, "Do you ready want to close the program?\n" + " Unsaved schedule will be lost",
                "Exit?", YES_NO_OPTION, QUESTION_MESSAGE) == 0){
            System.exit(0);
        }
    }

    //protected abstract void initializeButton();
}