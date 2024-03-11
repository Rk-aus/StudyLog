package model;

import exceptions.NoSuchNameException;
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


    // inspired by JsonSerializationDemo
    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("subject", this.subject);
        return json;
    }

//    // REQUIRES: the given subject is in the list
//    // MODIFIES: this
//    // EFFECTS: deletes the given subject to the list
//    public void deleteSubject(StudySubject subject) {
//        this.subjectList.remove(subject);
//    }
}
