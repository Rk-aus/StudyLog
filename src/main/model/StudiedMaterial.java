package model;

import java.time.LocalDateTime;

public class StudiedMaterial {
    private long studyTime;

    private LocalDateTime studyStartDateTime;
    private LocalDateTime studyEndDateTime;
    private StudySubject studySubject;
    private String studyContent;

    // EFFECTS: constructs a StudyTask object with studyTime 0
    public StudiedMaterial() {
        studyTime = 0;
        studyStartDateTime = null;
        studyEndDateTime = null;
        studyContent = null;
        studySubject = null;
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
