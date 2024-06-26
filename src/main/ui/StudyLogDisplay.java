package ui;

import exceptions.NoSuchNameException;
import model.StudiedMaterial;
import model.StudyLog;
import model.StudySubject;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

// Inspired by TellerApp & JsonSerializationDemo
// This class displays the StudyLog application
public class StudyLogDisplay {
    private static final String JSON_STORE = "./data/studylog.json";
    private StudyLog studyLog;
    private StudiedMaterial studyingMaterial;
    private StudySubject subject;

    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // inspired by TellerApp
    // EFFECTS: runs the StudyLogDisplay
    public StudyLogDisplay() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        runDisplay();
    }

    // inspired by TellerApp
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runDisplay() {
        studyLog = new StudyLog();
        subject = new StudySubject();

        boolean running = true;
        String command;

        input = new Scanner(System.in);

        while (running) {
            displayAction();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("leave")) {
                running = false;
            } else {
                processCommand(command);
            }
        }
    }

    // inspired by TellerApp & JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("add")) {
            addSubject();
        } else if (command.equals("start")) {
            try {
                startStudy();
            } catch (NoSuchNameException e) {
                System.out.println("Cannot find such Subject name...\n");
            }
        } else if (command.equals("subjects")) {
            viewSubjectList();
        } else if (command.equals("log")) {
            viewStudyLog();
        } else if (command.equals("save")) {
            saveStudyLog();
        } else if (command.equals("load")) {
            loadStudyLog();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // inspired by TellerApp & JsonSerializationDemo
    // EFFECTS: displays menu of actions to user
    private void displayAction() {
        System.out.println("\nSelect from:");
        System.out.println("\tadd -> Add Subject");
        System.out.println("\tstart -> Start Study");
        System.out.println("\tsubjects -> View Subject List");
        System.out.println("\tlog -> View Study Log");
        System.out.println("\tsave -> Save Study Log to File");
        System.out.println("\tload -> Load Study Log from File");
        System.out.println("\tleave -> Quit Study Log\n");
    }

    // inspired by TellerApp
    // MODIFIES: this
    // EFFECTS: adds StudySubject to StudySubjectList
    private void addSubject() {
        System.out.print("\nPlease enter the Subject name: ");
        String name = input.next();

        StudySubject newSubject = new StudySubject();
        newSubject.setSubject(name);
        this.subject.addSubject(newSubject);
        this.studyingMaterial = new StudiedMaterial();
        this.studyLog.addStudySubjectList(newSubject);

        System.out.println("Successfully added " + newSubject.getSubject() + " to Subjects!\n");
    }

    // inspired by TellerApp
    // MODIFIES: this
    // EFFECTS: starts the study and saves it to StudyLog
    private void startStudy() throws NoSuchNameException {
        System.out.print("\nPlease enter the name of the Subject you will study: ");
        studyingMaterial = new StudiedMaterial();
        String name = input.next();

        StudySubject subject = this.studyLog.findSubject(name);
        studyingMaterial.setStudySubject(subject);

        System.out.println("Let's start studying!");
        System.out.println("And enter 'finish' to save your work!\n");
        long startTime = System.currentTimeMillis();
        studyingMaterial.setStudyStartDateTime(LocalDateTime.now());
        finish(startTime);
    }

    // MODIFIES: this
    // EFFECTS: finishes the study if the user inputs "finish" and saves it to StudyLog
    //          if the user inputs anything else, print "Please enter again"
    private void finish(long startTime) {
        boolean studying = true;
        String finish = input.next();
        while (studying) {
            if (finish.equals("finish")) {
                finishStudy(startTime);
                studying = false;
            } else {
                System.out.println("Please enter again\n");
                finish(startTime);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: finishes the study and saves it to StudyLog
    private void finishStudy(long startTime) {
        studyingMaterial.setStudyTime(System.currentTimeMillis() - startTime);
        studyingMaterial.setStudyEndDateTime(LocalDateTime.now());
        fillStudyContent();
        this.studyLog.addStudyTask(studyingMaterial);
        printStudiedMaterial(studyingMaterial);
    }

    // MODIFIES: studyingMaterial
    // MODIFIES: this
    // EFFECTS: fill in the study content using the user input
    private void fillStudyContent() {
        System.out.println("Please enter what you studied: ");
        String content = input.next();

        studyingMaterial.setStudyContent(content);
        System.out.println("Great job!");
    }

    // EFFECTS: prints the given StudiedMaterial
    private void printStudiedMaterial(StudiedMaterial studyingMaterial) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("\tStudy Subject: " + studyingMaterial.getStudySubject().getSubject());
        System.out.println("\tStudy Content: " + studyingMaterial.getStudyContent());
        System.out.println("\tStarted from: " + formatter.format(studyingMaterial.getStudyStartDateTime()));
        System.out.println("\tEnded at:" + formatter.format(studyingMaterial.getStudyEndDateTime()));
        System.out.println("\tTotal Study Time: " + studyingMaterial.convertStudyTime() + "\n");
    }

    // EFFECTS: prints the StudySubjectList
    private void viewSubjectList() {
        System.out.println("\nCurrent Subjects are:");
        for (StudySubject s : this.studyLog.getStudySubjectList()) {
            System.out.println("\t" + s.getSubject());
        }
    }

    // EFFECTS: prints the StudyLog
    private void viewStudyLog() {
        List<StudiedMaterial> studyList = this.studyLog.getStudyList();
        System.out.println("\nStudy Log: ");
        for (StudiedMaterial studiedMaterial: studyList) {
            printStudiedMaterial(studiedMaterial);
        }
    }

    // Inspired by JsonSerializationDemo
    // EFFECTS: saves the StudyLog
    private void saveStudyLog() {
        try {
            jsonWriter.open();
            jsonWriter.write(studyLog);
            jsonWriter.close();
            System.out.println("Saved StudyLog to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // Inspired by JsonSerializationDemo
    // EFFECTS: loads the StudyLog
    private void loadStudyLog() {
        try {
            this.studyLog = jsonReader.read();
            System.out.println("Loaded StudyLog from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
