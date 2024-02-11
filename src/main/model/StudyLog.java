package model;

import java.util.ArrayList;
import java.util.List;

public class StudyLog {

    private List<StudyTask> studyList;


    // EFFECTS: constructs a StudyLog object
    public StudyLog() {
        this.studyList = new ArrayList<>();
    }

    // getters
    public List<StudyTask> getStudyList() {
        return this.studyList;
    }

    // MODIFIES: this
    // EFFECTS: adds a StudyTask to the StudyLog
    public void addStudyTask(StudyTask studyTask) {
        this.studyList.add(studyTask);
    }

    // EFFECTS: shows the list of StudyTask
    public StudyLog viewStudyLog() {
        return null;
    }

    // MODIFIES: this
    // EFFECTS: deletes a StudyTask from the StudyLog
    public void deleteStudyTask(StudyTask studyTask) {
        this.studyList.remove(studyTask);
    }
}
