package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class StudiedMaterial {
    private long studyTime;
    private LocalDateTime studyStartDateTime;
    private LocalDateTime studyEndDateTime;
    private StudySubject studySubject;
    private String studyContent;
    private ArrayList<StudiedMaterial> studyLog;


    // EFFECTS: constructs a StudiedMaterial object with studyTime 0
    public StudiedMaterial() {
        this.studyTime = 0;
        this.studyStartDateTime = null;
        this.studyEndDateTime = null;
        this.studyContent = null;
        this.studySubject = null;
        this.studyLog = new ArrayList<>();
    }

    // getters
    public long getStudyTime() {
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

    public ArrayList<StudiedMaterial> getStudyLog() {
        return this.studyLog;
    }

    // setters
    public void setStudyTime(long studyTime) {
        this.studyTime = studyTime;
    }

    public void setStudyStartDateTime(LocalDateTime studyStartDateTime) {
        this.studyStartDateTime = studyStartDateTime;
    }

    public void setStudyEndDateTime(LocalDateTime studyEndDateTime) {
        this.studyEndDateTime = studyEndDateTime;
    }

    public void setStudyContent(String studyContent) {
        this.studyContent = studyContent;
    }

    public void setStudySubject(StudySubject studySubject) {
        this.studySubject = studySubject;
    }

    // MODIFIES: this
    // EFFECTS: adds a StudyTask to the StudyLog
    public void addStudyTask(StudiedMaterial studiedMaterial) {
        this.studyLog.add(studiedMaterial);
    }

    // EFFECTS: converts the studyTime to int
    public String convertStudyTime() {
        int second = (int) this.getStudyTime() / 1000;
        String seconds = String.valueOf(second);
        int minute = second / 60;
        String minutes = setMinutes(second, minute);
        int hour = second / 3600;
        String hours = setHours(second, minute, hour);

        if (hour >= 1) {
            return hours;
        } else if (minute >= 1) {
            return minutes;
        } else {
            return seconds;
        }
    }

    // EFFECTS: converts seconds into minutes
    public String setMinutes(int second, int minute) {
        int remainderSecond0 = second - 60 * minute;
        String remainderSecond0String;
        if (String.valueOf(remainderSecond0).length() == 1) {
            remainderSecond0String = "0" + remainderSecond0;
        } else {
            remainderSecond0String = String.valueOf(remainderSecond0);
        }
        return minute + ":" + remainderSecond0String;
    }

    // EFFECTS: converts seconds and minutes into hours
    public String setHours(int second, int minute, int hour) {
        int remainderMinute = minute - 60 * hour;
        int remainderSecond1 = second - 3600 * hour - 60 * remainderMinute;
        String remainderMinuteString;
        if (String.valueOf(remainderMinute).length() == 1) {
            remainderMinuteString = "0" + remainderMinute;
        } else {
            remainderMinuteString = String.valueOf(remainderMinute);
        }
        String remainderSecond1String;
        if (String.valueOf(remainderSecond1).length() == 1) {
            remainderSecond1String = "0" + remainderSecond1;
        } else {
            remainderSecond1String = String.valueOf(remainderSecond1);
        }
        return hour + ":" + remainderMinuteString + ":" + remainderSecond1String;
    }

    //    // MODIFIES: this
//    // EFFECTS: deletes a StudyTask from the StudyLog
//    public void deleteStudyTask(StudiedMaterial studiedMaterial) {
//        this.studyList.remove(studiedMaterial);
//    }
//    // EFFECTS: modifies the StudyTask manually
//    public void modifyStudyTask() {
//
//    }
//
//    // EFFECTS: starts the study stop watch
//    public void startStudy() {
//
//    }
//
//    // EFFECTS: pauses the study stop watch
//    public void pauseStudy() {
//
//    }
}
