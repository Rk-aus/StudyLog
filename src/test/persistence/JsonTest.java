package persistence;

import model.StudiedMaterial;

import static org.junit.jupiter.api.Assertions.assertEquals;

// inspired by JsonSerializationDemo
public class JsonTest {
    protected void checkStudyLog(String sc, long time, StudiedMaterial sm) {
        assertEquals(sc, sm.getStudyContent());
        assertEquals(time, sm.getStudyTime());
    }
}