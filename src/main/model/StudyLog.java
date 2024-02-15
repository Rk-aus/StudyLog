package model;

import java.util.ArrayList;
import java.util.List;

public class StudyLog {

    private List<StudiedMaterial> studyList;

    // EFFECTS: constructs a StudyLog object
    public StudyLog() {
        this.studyList = new ArrayList<>();
    }

    // getters
    public List<StudiedMaterial> getStudyList() {
        return this.studyList;
    }

    // MODIFIES: this
    // EFFECTS: adds a StudyTask to the StudyLog
    public void addStudyTask(StudiedMaterial studiedMaterial) {
        this.studyList.add(studiedMaterial);
    }

    // EFFECTS: print all the saved StudiedMaterial
    public void viewStudyLog() {

    }

    // MODIFIES: this
    // EFFECTS: deletes a StudyTask from the StudyLog
    public void deleteStudyTask(StudiedMaterial studiedMaterial) {
        this.studyList.remove(studiedMaterial);
    }
}
