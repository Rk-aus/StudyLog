package ui;

import exceptions.NoSuchNameException;
import model.Event;
import model.EventLog;
import model.StudiedMaterial;
import model.StudyLog;
import model.StudySubject;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

// Inspired by SmartHome
// This class displays the StudyLogGUI application
public class StudyLogGUI extends JFrame implements ActionListener, WindowListener {
    private static final String JSON_STORE = "./data/studylog.json";
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton b7;
    private JTextArea editableTextArea;
    private JTextArea uneditableTextArea;
    private JPanel panel;
    private JLabel image;
    private JScrollPane scrollPane;

    private StudyLog studyLog;
    private StudiedMaterial studyingMaterial;
    private int caseNumber = 0;
    private long startTime;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public static void main(String[] args)  {
        new StudyLogGUI();
//        StopWatchLanterna swl = new StopWatchLanterna();
//        swl.run();
//        new StudyLogDisplay();
    }

    // Inspired by SmartHome
    // MODIFIES: this
    // EFFECTS: creates StudyLogGUI
    private StudyLogGUI() {
        super("StudyLog Console");
        setSize(WIDTH, HEIGHT);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        studyLog = new StudyLog();

        instantiatePanel();
        instantiateUneditableTextArea();
        instantiateScrollPane();
        instantiateButtons();
        instantiateEditableTextArea();
        addWindowListener(this);
        windowClosed(new WindowEvent(this, 202));

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: instantiates the panel
    private void instantiatePanel() {
        panel = new JPanel();
        panel.setBounds(0,HEIGHT / 3, WIDTH, 30);
        panel.setBackground(Color.GRAY);
        panel.setVisible(true);
        this.add(panel);
    }

    // MODIFIES: this
    // EFFECTS: instantiates the uneditable text box
    private void instantiateUneditableTextArea() {
        uneditableTextArea = new JTextArea();
        uneditableTextArea.setVisible(true);
        uneditableTextArea.setEditable(false);
        uneditableTextArea.setPreferredSize(new Dimension(WIDTH, Integer.MAX_VALUE));
    }

    // MODIFIES: this
    // EFFECTS: instantiates the scroll bar
    private void instantiateScrollPane() {
        scrollPane = new JScrollPane(uneditableTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0,HEIGHT / 3 + 30, WIDTH, HEIGHT - (HEIGHT / 3 + 30));
        scrollPane.setPreferredSize(new Dimension(WIDTH, Integer.MAX_VALUE));
        getContentPane().setLayout(null);
        getContentPane().add(scrollPane);
    }

    // Inspired by SmartHome
    // MODIFIES: this
    // EFFECTS: instantiate the buttons
    private void instantiateButtons() {
        b1 = new JButton("Add Subject");
        b2 = new JButton("Start/End Studying");
        b3 = new JButton("View Subject List");
        b4 = new JButton("View Study Log");
        b5 = new JButton("Filter Study Log");
        b6 = new JButton("Save");
        b7 = new JButton("Load");

        JPanel buttonRow = new JPanel();
        instantiateButtonRow(buttonRow);
        instantiateImage(buttonRow);

        buttonRow.setVisible(true);
    }

    // Inspired by SmartHome
    // MODIFIES: this
    // EFFECTS: instantiate the button row
    private void instantiateButtonRow(JPanel buttonRow) {
        buttonRow.add(b1);
        buttonRow.add(b2);
        buttonRow.add(b3);
        buttonRow.add(b4);
        buttonRow.add(b5);
        buttonRow.add(b6);
        buttonRow.add(b7);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        buttonRow.setSize(WIDTH, HEIGHT / 6);
        buttonRow.setBounds(0,0, WIDTH, HEIGHT / 3);
        this.add(buttonRow);
    }

    // MODIFIES: this
    // EFFECTS: instantiate the image
    private void instantiateImage(JPanel buttonRow) {
        ImageIcon icon = new ImageIcon("data/GoodJob.jpeg");
        image = new JLabel();
        image.setIcon(icon);
        image.setPreferredSize(new Dimension(150, 120));
        image.setVisible(false);
        image.setHorizontalAlignment(JLabel.CENTER);
        image.setVerticalAlignment(JLabel.CENTER);
        buttonRow.add(image);
    }

    // MODIFIES: this
    // EFFECTS: instantiate the editable text box
    private void instantiateEditableTextArea() {
        editableTextArea = new JTextArea();
        editableTextArea.setPreferredSize(new Dimension(100, 20));
        panel.add(editableTextArea);
    }

    // EFFECTS: execute action when a certain button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            addSubject();
        } else if (e.getSource() == b2) {
            startStudy();
        } else if (e.getSource() == b3) {
            viewSubjectList();
        } else if (e.getSource() == b4) {
            viewStudyLog();
        } else if (e.getSource() == b5) {
            viewFilteredStudyLog();
        } else if (e.getSource() == b6) {
            saveStudyLog();
        } else if (e.getSource() == b7) {
            loadStudyLog();
        }
    }

    // MODIFIES: this
    // EFFECTS: executes action in order when b2 is pressed multiple times continuously
    private void startStudy() {
        if (caseNumber % 4 == 0) {
            showInstruction();
        } else if (caseNumber % 4 == 1) {
            startTimer();
        } else if (caseNumber % 4 == 2) {
            finishStudy();
        } else if (caseNumber % 4 == 3) {
            fillStudyContent();
        }
        caseNumber++;
    }

    // MODIFIES: this
    // EFFECTS: shows the instructions on the uneditable text box
    private void showInstruction() {
        image.setVisible(false);
        String s = "Please enter the name of the Subject you will study then click \"Start/End Studying\": ";
        uneditableTextArea.setText(s);
        uneditableTextArea.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds a subject to the StudyLog
    private void addSubject() {
        image.setVisible(false);
        StudySubject newSubject = new StudySubject();
        newSubject.setSubject(editableTextArea.getText());
        studyLog.addStudySubjectList(newSubject);
        uneditableTextArea.setText("Successfully added " + newSubject.getSubject());
        uneditableTextArea.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: starts the study timer
    private void startTimer() {
        studyingMaterial = new StudiedMaterial();
        try {
            StudySubject subject = studyLog.findSubject(editableTextArea.getText());
            studyingMaterial.setStudySubject(subject);
            uneditableTextArea.setText("Let's start studying and click \"Start/End Studying\" when finished!");
            uneditableTextArea.setVisible(true);
            startTime = System.currentTimeMillis();
            studyingMaterial.setStudyStartDateTime(LocalDateTime.now());
        } catch (NoSuchNameException ex) {
            caseNumber = 0;
            uneditableTextArea.setText("Cannot find such Subject name...");
        }
    }

    // MODIFIES: this
    // EFFECTS: stops the study timer and finishes the study
    private void finishStudy() {
        studyingMaterial.setStudyTime(System.currentTimeMillis() - startTime);
        studyingMaterial.setStudyEndDateTime(LocalDateTime.now());
        uneditableTextArea.setText("Please enter what you have studied then click \"Start/End Studying\": ");
        uneditableTextArea.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: fill in the study content of the study material
    private void fillStudyContent() {
        studyingMaterial.setStudyContent(editableTextArea.getText());
        studyLog.addStudyTask(studyingMaterial);
        printStudiedMaterial();
        image.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: shows all the added subjects
    private void viewSubjectList() {
        image.setVisible(false);
        String string = "";
        Set<String> stringSet = new HashSet<>();
        for (StudySubject s : studyLog.getStudySubjectList()) {
            String subject = string + "-" + s.getSubject() + "\n";
            stringSet.add(subject);
        }
        for (String s: stringSet) {
            string += s;
        }
        uneditableTextArea.setText(string);
        uneditableTextArea.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: shows all the studied material
    private void viewStudyLog() {
        image.setVisible(false);
        String string = "";
        Set<String> stringSet = new HashSet<>();
        for (StudiedMaterial studiedMaterial: studyLog.getStudyList()) {
            studyingMaterial = studiedMaterial;
            printStudiedMaterial();
            stringSet.add(uneditableTextArea.getText());
        }
        for (String s: stringSet) {
            string += s;
        }
        uneditableTextArea.setText(string);
        uneditableTextArea.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: shows all the studied material with the subject that is specified in the editable text box
    private void viewFilteredStudyLog() {
        image.setVisible(false);
        String string = "";
        Set<String> stringSet = new HashSet<>();
        for (StudiedMaterial studiedMaterial: studyLog.getStudyList()) {
            if (studiedMaterial.getStudySubject().getSubject().equals(editableTextArea.getText())) {
                studyingMaterial = studiedMaterial;
                printStudiedMaterial();
                stringSet.add(uneditableTextArea.getText());
            }
        }
        for (String s: stringSet) {
            string += s;
        }
        uneditableTextArea.setText(string);
        uneditableTextArea.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: saves the StudyLog
    private void saveStudyLog() {
        image.setVisible(false);
        try {
            jsonWriter.open();
            jsonWriter.write(studyLog);
            jsonWriter.close();
            uneditableTextArea.setText("Saved StudyLog to " + JSON_STORE);
            uneditableTextArea.setVisible(true);
        } catch (FileNotFoundException exception) {
            uneditableTextArea.setText("Unable to write to file: " + JSON_STORE);
            uneditableTextArea.setVisible(true);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the StudyLog
    private void loadStudyLog() {
        image.setVisible(false);
        try {
            studyLog = jsonReader.read();
            uneditableTextArea.setText("Loaded StudyLog from " + JSON_STORE);
            uneditableTextArea.setVisible(true);
        } catch (IOException exception) {
            uneditableTextArea.setText("Unable to read from file: " + JSON_STORE);
            uneditableTextArea.setVisible(true);
        }
    }

    // MODIFIES: this
    // EFFECTS: prints the given StudiedMaterial
    private void printStudiedMaterial() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String s0 = " Study Subject: " + studyingMaterial.getStudySubject().getSubject();
        String s1 = "\nStudy Content: " + studyingMaterial.getStudyContent();
        String s2 = "\nStarted from: " + formatter.format(studyingMaterial.getStudyStartDateTime());
        String s3 = "\nEnded at: " + formatter.format(studyingMaterial.getStudyEndDateTime());
        String s4 = "\nTotal Study Time: " + studyingMaterial.convertStudyTime();
        uneditableTextArea.setText("\n" + s0 + "\n" + s1 + "\n" + s2 + "\n" + s3 + "\n" + s4 + "\n");
    }

    @Override
    public void windowOpened(WindowEvent e) {
        for (Event event: EventLog.getInstance()) {
            System.out.println(event.getDate());
            System.out.println(event.getDescription());
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        for (Event event: EventLog.getInstance()) {
            System.out.println(event.getDate());
            System.out.println(event.getDescription() + "\n");
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
