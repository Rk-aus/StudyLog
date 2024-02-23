package model;

import exceptions.NoSuchNameException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// inspired by JsonSerializationDemo
// Represents the studying subject and list of subjects
public class StudySubject implements Writable {

    String subject;
    ArrayList<StudySubject> subjectList;

    public StudySubject() {
        this.subject = null;
        this.subjectList = new ArrayList<>();
    }

    // getters
    public String getSubject() {
        return this.subject;
    }

    public ArrayList<StudySubject> getSubjectList() {
        return this.subjectList;
    }

    // setters
    public void setSubject(String subject) {
        this.subject = subject;
    }

    // MODIFIES: this
    // EFFECTS: adds the given StudySubject to the list
    public void addSubject(StudySubject subject) {
        this.subjectList.add(subject);
    }

    // EFFECTS: finds the StudySubject with the given subject name and returns the StudySubject
    //          if the StudySubject is not found, throw a NoSuchNameException
    public StudySubject findSubject(String name) throws NoSuchNameException {
        for (StudySubject subject: this.subjectList) {
            if (subject.getSubject().equals(name)) {
                return subject;
            }
        }
        throw new NoSuchNameException();
    }

    // inspired by JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("subjectList", subjectListToJson());
        return json;
    }

    // inspired by JsonSerializationDemo
    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray subjectListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (StudySubject ss : this.subjectList) {
            jsonArray.put(ss.toJson());
        }

        return jsonArray;
    }

//    // REQUIRES: the given subject is in the list
//    // MODIFIES: this
//    // EFFECTS: deletes the given subject to the list
//    public void deleteSubject(StudySubject subject) {
//        this.subjectList.remove(subject);
//    }
}
