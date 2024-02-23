package persistence;

import model.StudiedMaterial;
import model.StudyLog;
import model.StudySubject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.json.*;

// inspired by JsonSerializationDemo
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // inspired by JsonSerializationDemo
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // inspired by JsonSerializationDemo
    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public StudyLog read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    // inspired by JsonSerializationDemo
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // inspired by JsonSerializationDemo
    // EFFECTS: parses workroom from JSON object and returns it
    private StudyLog parseWorkRoom(JSONObject jsonObject) {
        StudyLog sl = new StudyLog();
        addStudyLog(sl, jsonObject);
        return sl;
    }

    // inspired by JsonSerializationDemo
    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addStudyLog(StudyLog sl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("studyList");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addStudiedMaterial(sl, nextThingy);
        }
    }

    // inspired by JsonSerializationDemo
    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addStudiedMaterial(StudyLog sl, JSONObject jsonObject) {
        long studyTime = Long.valueOf(jsonObject.getString("studyTime"));
//        LocalDateTime studyStartDateTime = LocalDateTime.(jsonObject.getString("studyStartDateTime"));
//        LocalDateTime studyEndDateTime = LocalDateTime.(jsonObject.getString("studyEndDateTime"));
        StudySubject studySubject = new StudySubject();
        studySubject.setSubject(jsonObject.getString("studySubject"));
        String studyContent = String.valueOf(jsonObject.getString("studyContent"));
        StudiedMaterial studiedMaterial = new StudiedMaterial();
        studiedMaterial.setStudyTime(studyTime);
//        studiedMaterial.setStudyStartDateTime(studyStartDateTime);
//        studiedMaterial.setStudyEndDateTime(studyEndDateTime);
        studiedMaterial.setStudySubject(studySubject);
        studiedMaterial.setStudyContent(studyContent);
        sl.addStudyTask(studiedMaterial);
    }
}
