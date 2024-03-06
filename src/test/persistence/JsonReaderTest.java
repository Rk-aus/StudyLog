package persistence;

import model.StudyLog;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyStudyLog() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyStudyLog.json");
        try {
            StudyLog sl = reader.read();
            assertEquals(0, sl.getStudyList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralStudyLog() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralStudyLog.json");
        try {
            StudyLog sl = reader.read();
            assertEquals(2, sl.getStudyList().size());
            checkStudyLog("CLP", 10, sl.getStudyList().get(0));
            checkStudyLog("Project", 100, sl.getStudyList().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}