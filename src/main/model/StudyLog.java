package model;

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
        this.studyList = new ArrayList<>();
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
    // EFFECTS: adds a StudyTask to the StudyLog
    public void addStudyTask(StudiedMaterial studiedMaterial) {
        this.studyList.add(studiedMaterial);
    }

    // MODIFIES: this
    // EFFECTS: adds the given StudySubject to the list
    public void addStudySubjectList(StudySubject studySubject) {
        this.subjectList.add(studySubject);
    }

    // inspired by JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("studyList", studyListToJson());
        json.put("subjectList", subjectListToJson());
        return json;
    }

    // inspired by JsonSerializationDemo
    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray studyListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (StudiedMaterial sm : this.studyList) {
            jsonArray.put(sm.toJson());
        }

        return jsonArray;
    }

    // inspired by JsonSerializationDemo
    // EFFECTS: returns things in this workroom as a JSON array
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