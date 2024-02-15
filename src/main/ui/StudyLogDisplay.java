package ui;

import exceptions.NoSuchNameException;
import model.StudiedMaterial;
import model.StudyLog;
import model.StudySubject;
import model.StudySubjectList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

// Inspired by TellerApp

public class StudyLogDisplay {

    private StudyLog studyLog;
    private StudiedMaterial studyingMaterial;
    private StudySubjectList subjectList;

    private Scanner input;


    // Inspired by TellerApp
    // EFFECTS: runs the StudyLogDisplay
    public StudyLogDisplay() {
        runDisplay();
    }

    // Inspired by TellerApp
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runDisplay() {
        studyLog = new StudyLog();
        subjectList = new StudySubjectList();

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

    // Inspired by TellerApp
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
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // Inspired by TellerApp
    // EFFECTS: displays menu of actions to user
    private void displayAction() {
        System.out.println("\nSelect from:");
        System.out.println("\tadd -> Add Subject");
        System.out.println("\tstart -> Start Study");
        System.out.println("\tsubjects -> View Subject List");
        System.out.println("\tlog -> View Study Log\n");
    }

    // Inspired by TellerApp
    // MODIFIES: this
    // EFFECTS: adds StudySubject to StudySubjectList
    private void addSubject() {
        System.out.print("\nPlease enter the Subject name: ");
        String name = input.next();

        StudySubject newSubject = new StudySubject();
        newSubject.setSubject(name);
        this.subjectList.addSubject(newSubject);

        System.out.println("Successfully added " + newSubject.getSubject() + " to Subjects!\n");
    }

    // Inspired by TellerApp
    // MODIFIES: this
    // EFFECTS: starts the study and saves it to StudyLog
    private void startStudy() throws NoSuchNameException {
        System.out.print("\nPlease enter the name of the Subject you will study: ");
        studyingMaterial = new StudiedMaterial();
        String name = input.next();

        StudySubject subject = this.subjectList.findSubject(name);
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
        System.out.println("Study Subject: " + studyingMaterial.getStudySubject().getSubject());
        System.out.println("Study Content: " + studyingMaterial.getStudyContent());
        System.out.println("Started from: " + formatter.format(studyingMaterial.getStudyStartDateTime()));
        System.out.println("Ended at:" + formatter.format(studyingMaterial.getStudyEndDateTime()));
        System.out.println("Total Study Time: " + studyingMaterial.convertStudyTime() + "\n");
    }

    // EFFECTS: prints the StudySubjectList
    private void viewSubjectList() {
        System.out.println("\nCurrent Subjects are:");
        for (StudySubject s : this.subjectList.getSubjectList()) {
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
}
