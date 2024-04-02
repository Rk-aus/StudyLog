package model;

import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDateTime;

// inspired by JsonSerializationDemo
// This class represents the studied material (X) that is saved in the StudyLog (Y)
public class StudiedMaterial implements Writable {
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

    // MODIFIES: this
    // EFFECTS: sets the Study Time and log it to the EventLog
    public void setStudyTime(long studyTime) {
        this.studyTime = studyTime;
        EventLog.getInstance().logEvent(new Event("Set Study Time for the StudiedMaterial."));
    }

    // MODIFIES: this
    // EFFECTS: sets the Study Start Date Time and log it to the EventLog
    public void setStudyStartDateTime(LocalDateTime studyStartDateTime) {
        this.studyStartDateTime = studyStartDateTime;
        EventLog.getInstance().logEvent(new Event("Set Study Start Time for the StudiedMaterial."));
    }

    // MODIFIES: this
    // EFFECTS: sets the Study End Date Time and log it to the EventLog
    public void setStudyEndDateTime(LocalDateTime studyEndDateTime) {
        this.studyEndDateTime = studyEndDateTime;
        EventLog.getInstance().logEvent(new Event("Set Study End Time for the StudiedMaterial."));
    }

    // MODIFIES: this
    // EFFECTS: sets the Study Content and log it to the EventLog
    public void setStudyContent(String studyContent) {
        this.studyContent = studyContent;
        EventLog.getInstance().logEvent(new Event("Set Study Content for the StudiedMaterial."));
    }

    // MODIFIES: this
    // EFFECTS: sets the Study Subject and log it to the EventLog
    public void setStudySubject(StudySubject studySubject) {
        this.studySubject = studySubject;
        EventLog.getInstance().logEvent(new Event("Set Study Subject for the StudiedMaterial."));
    }

    // EFFECTS: converts the studyTime to String
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

    // EFFECTS: converts second into minutes and seconds
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

    // EFFECTS: converts second into hours, minutes, and seconds
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

    // inspired by JsonSerializationDemo
    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("studyTime", this.studyTime);
        json.put("studyStartDateTime", this.studyStartDateTime);
        json.put("studyEndDateTime", this.studyEndDateTime);
        json.put("studySubject", this.studySubject.toJson());
        json.put("studyContent", this.studyContent);
        return json;
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
