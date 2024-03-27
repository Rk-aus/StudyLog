package ui;

import exceptions.NoSuchNameException;
import model.StudiedMaterial;
import model.StudyLog;
import model.StudySubject;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

// Inspired by SmartHome
public class StudyLogGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/studylog.json";
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private StudyLog studyLog;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton b7;
    private JTextArea textField;
    private JTextArea label;
    private JPanel redPanel;
    private JPanel nextPanel;
    private JLabel label0;
    private JScrollPane scrollPane;
    private int number = 0;
    private long startTime;
    private StudiedMaterial studyingMaterial;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

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
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        studyLog = new StudyLog();

        nextPanel = new JPanel();
        redPanel = new JPanel();
        redPanel.setBounds(0,HEIGHT / 3 + 30, WIDTH, HEIGHT - (HEIGHT / 3 + 30));
        nextPanel.setBounds(0,HEIGHT / 3, WIDTH, 30);
        redPanel.setBackground(Color.WHITE);
        nextPanel.setBackground(Color.GRAY);
        nextPanel.setVisible(true);
        redPanel.setVisible(true);
        this.add(redPanel);
        this.add(nextPanel);
        label = new JTextArea();
        label.setVisible(true);
        label.setEditable(false);
        scrollPane = new JScrollPane(label);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0,HEIGHT / 3 + 30, WIDTH, HEIGHT - (HEIGHT / 3 + 30));
        scrollPane.setPreferredSize(new Dimension(WIDTH, 1000));

        label.setPreferredSize(new Dimension(WIDTH, 1000));

        getContentPane().setLayout(null);
        getContentPane().add(scrollPane);

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
        b5 = new JButton("Filter Study Log");
        b6 = new JButton("Save");
        b7 = new JButton("Load");

//        JPanel buttonRow = formatButtonRow(b1);
        JPanel buttonRow = new JPanel();
        buttonRow.add(b1);
        buttonRow.add(b2);
        buttonRow.add(b3);
        buttonRow.add(b4);
        buttonRow.add(b5);
        buttonRow.add(b6);
        buttonRow.add(b7);
        ImageIcon icon = new ImageIcon("data/GoodJob.jpeg");
        label0 = new JLabel();
        label0.setIcon(icon);
        label0.setPreferredSize(new Dimension(150, 120));
        label0.setVisible(false);
        buttonRow.add(label0);
        label0.setHorizontalAlignment(JLabel.CENTER);
        label0.setVerticalAlignment(JLabel.CENTER);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);

        buttonRow.setBounds(0,0, WIDTH, HEIGHT / 3);
        buttonRow.setVisible(true);
        this.add(buttonRow);
    }

    // EFFECTS: prints the given StudiedMaterial
    private void printStudiedMaterial() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String s0 = "Study Subject: " + studyingMaterial.getStudySubject().getSubject();
        String s1 = "\nStudy Content: " + studyingMaterial.getStudyContent();
        String s2 = "\nStarted from: " + formatter.format(studyingMaterial.getStudyStartDateTime());
        String s3 = "\nEnded at: " + formatter.format(studyingMaterial.getStudyEndDateTime());
        String s4 = "\nTotal Study Time: " + studyingMaterial.convertStudyTime();
        label.setText("\n" + s0 + "\n" + s1 + "\n" + s2 + "\n" + s3 + "\n" + s4 + "\n");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            label0.setVisible(false);
            StudySubject newSubject = new StudySubject();
            newSubject.setSubject(textField.getText());
            this.studyLog.addStudySubjectList(newSubject);
            label.setText("Successfully added " + newSubject.getSubject());
            label.setVisible(true);
        } else if (e.getSource() == b2) {
            if (number % 4 == 0) {
                label0.setVisible(false);
                label.setText("Please enter the name of the Subject you will study then click \"Start/End Studying\": ");
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
                label.setText("Please enter what you have studied then click \"Start/End Studying\": ");
                label.setVisible(true);
            } else if (number % 4 == 3) {
                studyingMaterial.setStudyContent(textField.getText());
                this.studyLog.addStudyTask(studyingMaterial);
                printStudiedMaterial();
                label0.setVisible(true);
            }
            number++;
        } else if (e.getSource() == b3) {
            label0.setVisible(false);
            String string = "";
            Set<String> stringSet = new HashSet<>();
            for (StudySubject s : this.studyLog.getStudySubjectList()) {
                String subject = string + "\n" + "-" + s.getSubject();
                stringSet.add(subject);
            }
            for (String s: stringSet) {
                string += s;
            }
            label.setText(string);
            label.setVisible(true);
        } else if (e.getSource() == b4) {
            label0.setVisible(false);
            String string = "";
            Set<String> stringSet = new HashSet<>();
            for (StudiedMaterial studiedMaterial: this.studyLog.getStudyList()) {
                studyingMaterial = studiedMaterial;
                printStudiedMaterial();
                stringSet.add(label.getText());
            }
            for (String s: stringSet) {
                string += s;
            }
            label.setText(string);
            label.setVisible(true);
        } else if (e.getSource() == b5) {
            label0.setVisible(false);
            String string = "";
            Set<String> stringSet = new HashSet<>();
            for (StudiedMaterial studiedMaterial: this.studyLog.getStudyList()) {
                if (studiedMaterial.getStudySubject().getSubject().equals(textField.getText())) {
                    studyingMaterial = studiedMaterial;
                    printStudiedMaterial();
                    stringSet.add(label.getText());
                }
            }

            for (String s: stringSet) {
                string += s;
            }
            label.setText(string);
            label.setVisible(true);
        } else if (e.getSource() == b6) {
            try {
                jsonWriter.open();
                jsonWriter.write(studyLog);
                jsonWriter.close();
                label.setText("Saved StudyLog to " + JSON_STORE);
                label.setVisible(true);
            } catch (FileNotFoundException exception) {
                label.setText("Unable to write to file: " + JSON_STORE);
                label.setVisible(true);
            }
        } else if (e.getSource() == b7) {
            try {
                this.studyLog = jsonReader.read();
                label.setText("Loaded StudyLog from " + JSON_STORE);
                label.setVisible(true);
            } catch (IOException exception) {
                label.setText("Unable to read from file: " + JSON_STORE);
                label.setVisible(true);
            }
        }
    }
}
