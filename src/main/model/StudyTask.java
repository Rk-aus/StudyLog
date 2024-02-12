package model;

import java.time.LocalTime;
import java.time.LocalDateTime;

public class StudyTask {
    private int studyTime;

    private LocalDateTime studyStartDateTime;
    private LocalDateTime studyEndDateTime;

    private String studyContent;
    private StudySubject studySubject;

    private StudyLog studyLog;

    // EFFECTS: constructs a StudyTask object with studyTime 0
    public StudyTask() {
        studyTime = 0;
        studyStartDateTime = LocalDateTime.now();
        studyEndDateTime = LocalDateTime.now();
        studyContent = null;
        studySubject = null;
    }

    // getters
    public int getStudyTime() {
        return this.studyTime;
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
        studyLog.addStudyTask(this);
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
