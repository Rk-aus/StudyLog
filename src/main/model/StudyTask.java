package model;

import java.time.LocalTime;
import java.time.LocalDateTime;

public class StudyTask {
    private int studyTime;
    private int studyStartTime;
    private int studyEndTime;
    private boolean isStudying;

    private LocalDateTime studyStartDateTime;
    private LocalDateTime studyEndDateTime;

    private String studyContent;
    private StudySubject studySubject;

    // EFFECTS: constructs a StudyTask object with studyTime 0
    public StudyTask() {
        studyTime = 0;
        studyStartTime = 0;
        studyEndTime = 0;
        isStudying = false;
        studyStartDateTime = LocalDateTime.now();
        studyEndDateTime = LocalDateTime.now();
        studyContent = null;
        studySubject = null;
    }

    // getters
    public int getStudyTime() {
        return this.studyTime;
    }

    public int getStudyStartTime() {
        return this.studyStartTime;
    }

    public int getStudyEndTime() {
        return this.studyEndTime;
    }

    public LocalDateTime getStudyStartDateTime() {
        return this.studyStartDateTime;
    }

    public LocalDateTime getStudyEndDateTime() {
        return this.studyEndDateTime;
    }

    public String getStudyContent() {
        return this.studyContent;
    }

    public StudySubject getStudySubject() {
        return this.studySubject;
    }


    // EFFECTS: starts the study stop watch
    public void startStudy() {

    }

    // EFFECTS: pauses the study stop watch
    public void pauseStudy() {

    }

    // EFFECTS: saves the study to the StudyLog
    public void saveStudy() {

    }

//    // EFFECTS: saves the study to the StudyLog by adding information manually
//    public void manualSaveStudy() {
//
//    }
//
//    // EFFECTS: modifies the StudyTask manually
//    public void modifyStudyTask() {
//
//    }







}
