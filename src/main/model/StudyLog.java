package model;

import exceptions.NoSuchNameException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// inspired by JsonSerializationDemo
// Represents a list (Y) of StudiedMaterial with each containing information about the material the user has
// studied in the past (X)
public class StudyLog implements Writable {
    private List<StudiedMaterial> studyList;
    private List<StudySubject> subjectList;


    // EFFECTS: constructs a StudyLog object
    public StudyLog() {
        studyList = new ArrayList<>();
        subjectList = new ArrayList<>();
    }

    // getters
    public List<StudiedMaterial> getStudyList() {
        return this.studyList;
    }

    public List<StudySubject> getStudySubjectList() {
        return this.subjectList;
    }

    // MODIFIES: this
    // EFFECTS: adds a StudyTask to the StudyLog and log it to the EventLog
    public void addStudyTask(StudiedMaterial studiedMaterial) {
        this.studyList.add(studiedMaterial);
        EventLog.getInstance().logEvent(new Event("Added Study Task to the StudyLog."));
    }

    // MODIFIES: this
    // EFFECTS: adds the given StudySubject to the list and log it to the EventLog
    public void addStudySubjectList(StudySubject studySubject) {
        this.subjectList.add(studySubject);
        EventLog.getInstance().logEvent(new Event("Added Subject to the SubjectList."));
    }

    // EFFECTS: finds the StudySubject with the given subject name and returns the StudySubject and log it to the
    //          EventLog
    //          if the StudySubject is not found, throw a NoSuchNameException
    public StudySubject findSubject(String name) throws NoSuchNameException {
        EventLog.getInstance().logEvent(new Event("Searched for the Subject in the SubjectList."));
        for (StudySubject subject: this.subjectList) {
            if (subject.getSubject().equals(name)) {
                return subject;
            }
        }
        throw new NoSuchNameException();
    }

    // inspired by JsonSerializationDemo
    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("studyList", studyListToJson());
        json.put("subjectList", subjectListToJson());
        return json;
    }

    // inspired by JsonSerializationDemo
    // EFFECTS: returns StudiedMaterial in this StudyLog as a JSON array
    private JSONArray studyListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (StudiedMaterial sm : this.studyList) {
            jsonArray.put(sm.toJson());
        }

        return jsonArray;
    }

    // inspired by JsonSerializationDemo
    // EFFECTS: returns StudySubjectList in this StudyLog as a JSON array
    private JSONArray subjectListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (StudySubject ss : this.subjectList) {
            jsonArray.put(ss.getSubject());
        }

        return jsonArray;
    }

//    // MODIFIES: this
//    // EFFECTS: deletes a StudyTask from the StudyLog
//    public void deleteStudyTask(StudiedMaterial studiedMaterial) {
//        this.studyList.remove(studiedMaterial);
//    }
}