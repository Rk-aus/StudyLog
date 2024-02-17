package model;

import exceptions.NoSuchNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StudySubjectTest {
    StudySubject testStudySubject;
    StudySubject testStudySubject0;
    StudySubject testStudySubject1;

    @BeforeEach
    void setup(){
        testStudySubject = new StudySubject();
        testStudySubject0 = new StudySubject();
        testStudySubject1 = new StudySubject();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testStudySubject.getSubjectList().size());
    }

    @Test
    void testAddSubject() {
        testStudySubject.addSubject(testStudySubject0);
        assertEquals(1, testStudySubject.getSubjectList().size());
        assertEquals(testStudySubject0, testStudySubject.getSubjectList().get(0));
    }

    @Test
    void testAddMultipleSubjects() {
        testStudySubject.addSubject(testStudySubject0);
        testStudySubject.addSubject(testStudySubject1);
        assertEquals(2, testStudySubject.getSubjectList().size());
        assertEquals(testStudySubject0, testStudySubject.getSubjectList().get(0));
        assertEquals(testStudySubject1, testStudySubject.getSubjectList().get(1));
    }

    @Test
    void testFindSubjectEmptyList() {
        try {
            testStudySubject.findSubject("test");
            fail("Expected not to run");
        } catch (NoSuchNameException e) {
            // expected
        }
    }

    @Test
    void testFindSubjectNotFound() {
        testStudySubject.addSubject(testStudySubject0);
        testStudySubject.addSubject(testStudySubject1);
        testStudySubject0.setSubject("Physics");
        testStudySubject1.setSubject("Math");
        try {
            testStudySubject.findSubject("test");
            fail("Expected not to run");
        } catch (NoSuchNameException e) {
            // expected
        }
        assertEquals(2, testStudySubject.getSubjectList().size());
    }

    @Test
    void testFindSubjectFound() {
        testStudySubject.addSubject(testStudySubject0);
        testStudySubject.addSubject(testStudySubject1);
        testStudySubject0.setSubject("Physics");
        testStudySubject1.setSubject("Math");
        try {
            StudySubject subject = testStudySubject.findSubject("Math");
            assertEquals(testStudySubject1, subject);
            // expected
        } catch (NoSuchNameException e) {
            fail("Expected not to run");
        }
        assertEquals(2, testStudySubject.getSubjectList().size());
    }
}
