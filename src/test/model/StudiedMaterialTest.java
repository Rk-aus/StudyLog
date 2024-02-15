package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StudiedMaterialTest {

    StudiedMaterial testStudiedMaterial;

    @BeforeEach
    void setup(){
        testStudiedMaterial = new StudiedMaterial();
    }

    @Test
    void testStudiedMaterial() {
        assertEquals(0, testStudiedMaterial.getStudyTime());
        assertNull(testStudiedMaterial.getStudyStartDateTime());
        assertNull(testStudiedMaterial.getStudyEndDateTime());
        assertNull(testStudiedMaterial.getStudyContent());
        assertNull(testStudiedMaterial.getStudySubject());
    }

    @Test
    void testConvertStudyTimeSeconds() {
        testStudiedMaterial.setStudyTime(10000);
        assertEquals("10", testStudiedMaterial.convertStudyTime());
    }

    @Test
    void testConvertStudyTimeMinutes() {
        testStudiedMaterial.setStudyTime(60000);
        assertEquals("1:00", testStudiedMaterial.convertStudyTime());
        testStudiedMaterial.setStudyTime(100000);
        assertEquals("1:40", testStudiedMaterial.convertStudyTime());
        testStudiedMaterial.setStudyTime(3599000);
        assertEquals("59:59", testStudiedMaterial.convertStudyTime());
    }

    @Test
    void testConvertStudyTimeHours() {
        testStudiedMaterial.setStudyTime(3600000);
        assertEquals("1:00:00", testStudiedMaterial.convertStudyTime());
        testStudiedMaterial.setStudyTime(3660000);
        assertEquals("1:01:00", testStudiedMaterial.convertStudyTime());
        testStudiedMaterial.setStudyTime(3659000);
        assertEquals("1:00:59", testStudiedMaterial.convertStudyTime());
        testStudiedMaterial.setStudyTime(3659000);
        assertEquals("1:00:59", testStudiedMaterial.convertStudyTime());
        testStudiedMaterial.setStudyTime(4500000);
        assertEquals("1:15:00", testStudiedMaterial.convertStudyTime());
        testStudiedMaterial.setStudyTime(7266000);
        assertEquals("2:01:06", testStudiedMaterial.convertStudyTime());
        testStudiedMaterial.setStudyTime(7266111);
        assertEquals("2:01:06", testStudiedMaterial.convertStudyTime());
    }



//    @Test
//    void testStartStudy() {
//        long studyTime = testStopWatch.getStudyEndTime() - testStopWatch.getStudyStartTime();
//        assertEquals(0, testStudiedMaterial.getStudyTime());
//        testStudiedMaterial.startStudy();
//        assertEquals(studyTime, testStudiedMaterial.getStudyTime());
//        testStudiedMaterial.pauseStudy();
//        assertEquals(studyTime, testStudiedMaterial.getStudyTime());
//    }
//
//    @Test
//    void testSaveStudy() {
//        long studyTime = testStopWatch.getStudyEndTime() - testStopWatch.getStudyStartTime();
//        testStudiedMaterial.startStudy();
//        testStudiedMaterial.pauseStudy();
//        assertEquals(studyTime, testStudiedMaterial.getStudyTime());
//    }
}
