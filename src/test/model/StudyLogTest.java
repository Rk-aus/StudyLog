package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudyLogTest {

    private StudyLog testStudyLog;
    private StudyTask testStudyTask0;
    private StudyTask testStudyTask1;

    @BeforeEach
    void setup() {
        testStudyLog = new StudyLog();
        testStudyTask0 = new StudyTask();
        testStudyTask1 = new StudyTask();
    }

    @Test
    void testStudyLog() {
        assertEquals(0, testStudyLog.getStudyList().size());
    }

    @Test
    void testAddStudyTask() {
        testStudyLog.addStudyTask(testStudyTask0);
        assertEquals(1, testStudyLog.getStudyList().size());
        testStudyLog.addStudyTask(testStudyTask1);
        assertEquals(2, testStudyLog.getStudyList().size());
    }

    @Test
    void testDeleteStudyTask() {
        testStudyLog.addStudyTask(testStudyTask0);
        testStudyLog.addStudyTask(testStudyTask1);
        testStudyLog.deleteStudyTask(testStudyTask0);
        assertEquals(1, testStudyLog.getStudyList().size());
        testStudyLog.deleteStudyTask(testStudyTask1);
        assertEquals(0, testStudyLog.getStudyList().size());
    }
}