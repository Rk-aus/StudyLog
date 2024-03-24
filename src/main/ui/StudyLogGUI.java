package ui;

import exceptions.NoSuchNameException;
import model.StudiedMaterial;
import model.StudyLog;
import model.StudySubject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

// Inspired by SmartHome
public class StudyLogGUI extends JFrame implements ActionListener {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private StudyLog studyLog;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JTextArea textField;
    private JTextArea label;
    private JPanel redPanel;
    private JPanel nextPanel;
    private int number = 0;
    private long startTime;
    private StudiedMaterial studyingMaterial;

    public static void main(String[] args)  {
        new StudyLogGUI();
//        StopWatchLanterna swl = new StopWatchLanterna();
//        swl.run();
//        new StudyLogDisplay();
    }

    // Inspired by SmartHome
    //MODIFIES: this
    //EFFECTS: creates StudyLogGUI
    private StudyLogGUI() {
        super("StudyLog Console");
        setSize(WIDTH, HEIGHT);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        studyLog = new StudyLog();


        nextPanel = new JPanel();
        redPanel = new JPanel();
        redPanel.setBounds(0,HEIGHT / 3 + 30, WIDTH, HEIGHT - (HEIGHT / 3 + 30));
        nextPanel.setBounds(0,HEIGHT / 3, WIDTH, 30);
//        redPanel.setBackground(Color.BLACK);
        nextPanel.setBackground(Color.BLUE);
        nextPanel.setVisible(true);
        redPanel.setVisible(true);
        this.add(redPanel);
        this.add(nextPanel);
//        label = new JLabel();
        label = new JTextArea();
        label.setVisible(true);
//        textField1.setVisible(true);
//        label.add(textField1);
        label.setPreferredSize(new Dimension(WIDTH, 500));

        redPanel.add(label);

        placeHomeButtons();
        textField = new JTextArea();
        nextPanel.add(textField);
        textField.setPreferredSize(new Dimension(100, 20));

        setVisible(true);
    }

    // Inspired by SmartHome
    //EFFECTS: creates button options available
    private void placeHomeButtons() {
        b1 = new JButton("Add Subject");
        b2 = new JButton("Start/End Studying");
        b3 = new JButton("View Subject List");
        b4 = new JButton("View Study Log");
        b5 = new JButton("Save Progress");
        b6 = new JButton("Load Progress");

        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.add(b2);
        buttonRow.add(b3);
        buttonRow.add(b4);
        buttonRow.add(b5);
        buttonRow.add(b6);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

//        methodOne();

//        b2.addActionListener(e -> {
//            // do the method 2
//        });

//        methodThree();
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);

        buttonRow.setBounds(0,0, WIDTH, HEIGHT / 3);
        buttonRow.setVisible(true);
        this.add(buttonRow);
    }

//    private void methodThree() {
//        b3.addActionListener(e -> {
//            for (StudySubject s : this.studyLog.getStudySubjectList()) {
//                JLabel label = new JLabel();
//                label.setText("\t" + s.getSubject());
//                label.setVisible(true);
//                redPanel.add(label);
//            }
//        });
//    }

//    private void methodOne() {
//        b1.addActionListener(e -> {
//            JLabel label = new JLabel();
//            label.setText("Nobody");
//            label.setVisible(true);
//            redPanel.add(label);
//
//            if (e.getSource() == b1) {
//                StudySubject newSubject = new StudySubject();
//                newSubject.setSubject(textField.getText());
//                this.studyLog.addStudySubjectList(newSubject);
//            }
//        });
//    }

    // Inspired by SmartHome
    //EFFECTS: creates and returns row with button included
    public JPanel formatButtonRow(JButton b) {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(b);

        return p;
    }

    // EFFECTS: prints the given StudiedMaterial
    private void printStudiedMaterial() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String s0 = "Study Subject: " + studyingMaterial.getStudySubject().getSubject();
        String s1 = "\nStudy Content: " + studyingMaterial.getStudyContent();
        String s2 = "\nStarted from: " + formatter.format(studyingMaterial.getStudyStartDateTime());
        String s3 = "\nEnded at:" + formatter.format(studyingMaterial.getStudyEndDateTime());
        String s4 = "\nTotal Study Time: " + studyingMaterial.convertStudyTime();
        label.setText("\n" + s0 + "\n" + s1 + "\n" + s2 + "\n" + s3 + "\n" + s4 + "\n");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            StudySubject newSubject = new StudySubject();
            newSubject.setSubject(textField.getText());
            this.studyLog.addStudySubjectList(newSubject);
            label.setText("Successfully added " + newSubject.getSubject());
            label.setVisible(true);
        } else if (e.getSource() == b2) {
            if (number % 4 == 0) {
                label.setText("\nPlease enter the name of the Subject you will study then click \"Start/End Studying\": ");
                label.setVisible(true);
            } else if (number % 4 == 1) {
                studyingMaterial = new StudiedMaterial();
                try {
                    StudySubject subject = this.studyLog.findSubject(textField.getText());
                    studyingMaterial.setStudySubject(subject);
                    label.setText("Let's start studying and click \"Start/End Studying\" when finished!");
                    label.setVisible(true);
                    startTime = System.currentTimeMillis();
                    studyingMaterial.setStudyStartDateTime(LocalDateTime.now());
                } catch (NoSuchNameException ex) {
                    number = 0;
                    label.setText("Cannot find such Subject name...");
                }
            } else if (number % 4 == 2) {
                studyingMaterial.setStudyTime(System.currentTimeMillis() - startTime);
                studyingMaterial.setStudyEndDateTime(LocalDateTime.now());
                label.setText("Please enter what you studied then click \"Start/End Studying\": ");
                label.setVisible(true);
                label.setVisible(true);
            } else if (number % 4 == 3) {
                studyingMaterial.setStudyContent(textField.getText());
                this.studyLog.addStudyTask(studyingMaterial);
                printStudiedMaterial();
            }
            number++;
        } else if (e.getSource() == b3) {
            String string = "";
            for (StudySubject s : this.studyLog.getStudySubjectList()) {
                label.setText(string + "\n" + "-" + s.getSubject());
                string += label.getText();
//                if (!label.getText().contains("Successfully added") & !label.getText().contains("Started from")) {
//                    label.setText(string + "\n" + "-" + s.getSubject());
//                    string += label.getText();
//                } else {
//                    label.setText("\n" + "-" + s.getSubject());
//                }
                label.setVisible(true);
            }
        } else if (e.getSource() == b4) {
            List<StudiedMaterial> studyList = this.studyLog.getStudyList();
            String s = "";
            for (StudiedMaterial studiedMaterial: studyList) {
                studyingMaterial = studiedMaterial;
                printStudiedMaterial();
                s += label.getText();
            }
            label.setText(s);
            label.setVisible(true);
        }
    }
}
