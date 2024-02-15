package ui;

import exceptions.InvalidInputException;
import exceptions.NoSuchNameException;
import model.StudiedMaterial;
import model.StudyLog;
import model.StudySubject;
import model.StudySubjectList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

// Taken and modified from TellerApp

public class StudyLogDisplay {

    private StudyLog studyLog;
    private StudySubjectList subjectList;
    private StudiedMaterial studyingMaterial;

    private Scanner input;


    // Taken and modified from TellerApp
    // EFFECTS: runs the StudyLogDisplay
    public StudyLogDisplay() {
        runDisplay();
    }

    // Taken and modified from TellerApp
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runDisplay() {
        studyLog = new StudyLog();
        subjectList = new StudySubjectList();

        boolean running = true;
        String command = null;

        input = new Scanner(System.in);

        while (running) {
            displayAction();
            command = input.next();
            command = command.toLowerCase();
            if (command == "leave") {
                running = false;
            } else {
                processCommand(command);
            }
        }
    }

    // Taken and modified from TellerApp
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

    // Taken and modified from TellerApp
    // EFFECTS: displays menu of actions to user
    private void displayAction() {
        System.out.println("\nSelect from:");
        System.out.println("\tadd -> Add Subject");
        System.out.println("\tstart -> Start Study");
        System.out.println("\tsubjects -> View Subject List");
        System.out.println("\tlog -> View Study Log\n");
    }

    // Taken and modified from TellerApp
    // EFFECTS: adds StudySubject to StudySubjectList
    private void addSubject() {
        System.out.print("\nPlease enter the Subject name: ");
        String name = input.next();

        StudySubject newSubject = new StudySubject();
        newSubject.setSubject(name);
        this.subjectList.addSubject(newSubject);

        System.out.println("Successfully added " + newSubject.getSubject() + " to Subjects!\n");
    }

    // Taken and modified from TellerApp
    // EFFECTS: starts the study and saves it to StudySubjectList
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

    private void finishStudy(long startTime) {
        studyingMaterial.setStudyTime(System.currentTimeMillis() - startTime);
        studyingMaterial.setStudyEndDateTime(LocalDateTime.now());
        fillStudyContent();
        this.studyLog.addStudyTask(studyingMaterial);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Study Subject: " + studyingMaterial.getStudySubject().getSubject());
        System.out.println("Study Content: " + studyingMaterial.getStudyContent());
        System.out.println("Started from: " + formatter.format(studyingMaterial.getStudyStartDateTime()));
        System.out.println("Ended at:" + formatter.format(studyingMaterial.getStudyEndDateTime()));
        System.out.println("Total Study Time: " + studyingMaterial.convertStudyTime());
    }

    private void fillStudyContent() {
        System.out.println("Please enter what you studied: ");
        String content = input.next();

        studyingMaterial.setStudyContent(content);
        System.out.println("Great job!");
    }


    private void viewSubjectList() {
        System.out.println("\nCurrent Subjects are:");
        for (StudySubject s : this.subjectList.getSubjectList()) {
            System.out.println("\t" + s.getSubject());
        }
    }

    private void viewStudyLog() {
        List<StudiedMaterial> studyList = this.studyLog.getStudyList();
        for (StudiedMaterial m: studyList) {
            System.out.println();
        }
    }
}
