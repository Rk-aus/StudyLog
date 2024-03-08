package persistence;

import model.StudiedMaterial;
import model.StudyLog;
import model.StudySubject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

// inspired by JsonSerializationDemo
class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyStudyLog() {
        try {
            StudyLog sl = new StudyLog();
            StudySubject ss = new StudySubject();
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyStudyLog.json");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderEmptyStudyLog.json");
            sl = reader.read();
            assertEquals(0, sl.getStudyList().size());
            assertEquals(0, ss.getSubjectList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralStudyLog() {
        try {
            StudyLog sl = new StudyLog();
            StudiedMaterial sm0 = new StudiedMaterial();
            StudySubject ss0 = new StudySubject();
            ss0.setSubject("MATH");
            sm0.setStudyTime(10);
            sm0.setStudyContent("CLP");
            sm0.setStudySubject(ss0);
            LocalDateTime now = LocalDateTime.now();
            sm0.setStudyStartDateTime(now);
            sm0.setStudyEndDateTime(now);
            StudiedMaterial sm1 = new StudiedMaterial();
            StudySubject ss1 = new StudySubject();
            ss1.setSubject("CPSC");
            sm1.setStudyTime(100);
            sm1.setStudyContent("Project");
            sm1.setStudySubject(ss1);
            sm1.setStudyStartDateTime(now);
            sm1.setStudyEndDateTime(now);
            sl.addStudyTask(sm0);
            sl.addStudyTask(sm1);
            sl.addStudySubjectList(ss0);
            sl.addStudySubjectList(ss1);
            JsonWriter writer = new JsonWriter("./data/testReaderGeneralStudyLog.json");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderGeneralStudyLog.json");
            sl = reader.read();
            assertEquals(2, sl.getStudyList().size());
            checkStudyLog("CLP", 10, sl.getStudyList().get(0));
            checkStudyLog("Project", 100, sl.getStudyList().get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}