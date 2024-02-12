package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StopWatchTest {

    StopWatch testStopWatch;

    @BeforeEach
    void setup() {
        testStopWatch = new StopWatch();
    }

    @Test
    void testStopWatch() {
        assertEquals(0, testStopWatch.getStudyStartTime());
        assertEquals(0, testStopWatch.getStudyEndTime());
        assertEquals(0, testStopWatch.getIntervalStudyTime());
        assertEquals(0, testStopWatch.getTotalStudyTime());
        assertFalse(testStopWatch.isStudying());
    }

    @Test
    void testStartStopWatch() {
        testStopWatch.startStopWatch();
        assertEquals(System.currentTimeMillis(), testStopWatch.getStudyStartTime());
        assertEquals(0, testStopWatch.getStudyEndTime());
        assertEquals(0, testStopWatch.getIntervalStudyTime());
        assertEquals(0, testStopWatch.getTotalStudyTime());
        testStopWatch.startStopWatch();
        assertFalse(System.currentTimeMillis() == testStopWatch.getStudyStartTime());
    }

}
