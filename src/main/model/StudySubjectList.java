package model;


import exceptions.NoSuchNameException;

import java.util.ArrayList;
import java.util.List;

public class StudySubjectList {

    private List<StudySubject> subjectList;

    public StudySubjectList() {
        this.subjectList = new ArrayList<>();
    }

    public List<StudySubject> getSubjectList() {
        return this.subjectList;
    }

    // MODIFIES: this
    // EFFECTS: adds the given subject to the list
    public void addSubject(StudySubject subject) {
        this.subjectList.add(subject);
    }

    public StudySubject findSubject(String name) throws NoSuchNameException {
        for (StudySubject subject: this.subjectList) {
            if (subject.getSubject().equals(name)) {
                return subject;
            }
        }
        throw new NoSuchNameException();
    }

    // REQUIRES: the given subject is in the list
    // MODIFIES: this
    // EFFECTS: deletes the given subject to the list
    public void deleteSubject(StudySubject subject) {
        this.subjectList.remove(subject);
    }

    // EFFECTS: print all the saved StudySubject
    public void viewSubjectList() {

    }
}
