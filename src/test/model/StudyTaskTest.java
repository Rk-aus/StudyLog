package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StudyTaskTest {

    StudyTask testStudyTask;
    StopWatch testStopWatch;

    @BeforeEach
    void setup(){
        testStudyTask = new StudyTask();
        testStopWatch = new StopWatch();
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
        long studyTime = testStopWatch.getStudyEndTime() - testStopWatch.getStudyStartTime();
        assertEquals(0, testStudyTask.getStudyTime());
        testStudyTask.startStudy();
        assertEquals(studyTime, testStudyTask.getStudyTime());
        testStudyTask.pauseStudy();
        assertEquals(studyTime, testStudyTask.getStudyTime());
    }

    @Test
    void testSaveStudy() {
        long studyTime = testStopWatch.getStudyEndTime() - testStopWatch.getStudyStartTime();
        testStudyTask.startStudy();
        testStudyTask.pauseStudy();
        assertEquals(studyTime, testStudyTask.getStudyTime());
    }


}
