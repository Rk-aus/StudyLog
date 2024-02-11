package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StudyTaskTest {

    StudyTask testStudyTask;

    @BeforeEach
    void setup(){
        testStudyTask = new StudyTask();
    }


    @Test
    void testStudyTask() {
        assertEquals(0, testStudyTask.getStudyTime());
        assertEquals(LocalDateTime.now(), testStudyTask.getStudyStartDateTime());
        assertEquals(LocalDateTime.now(), testStudyTask.getStudyEndDateTime());
        assertNull(testStudyTask.getStudyContent());
        assertNull(testStudyTask.getStudySubject());
    }

    @Test
    void testStartStudy() {
        assertEquals(0, testStudyTask.getStudyTime());
        testStudyTask.startStudy();

        testStudyTask.pauseStudy();
        assertEquals(testStudyTask.getStudyEndTime() - testStudyTask.getStudyStartTime(), testStudyTask.getStudyTime());
    }


}
