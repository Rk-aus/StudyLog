package model;

import exceptions.NoSuchNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StudySubjectListTest {
    StudySubjectList testStudySubjectList;
    StudySubject testStudySubject0;
    StudySubject testStudySubject1;

    @BeforeEach
    void setup(){
        testStudySubjectList = new StudySubjectList();
        testStudySubject0 = new StudySubject();
        testStudySubject1 = new StudySubject();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testStudySubjectList.getSubjectList().size());
    }

    @Test
    void testAddSubject() {
        testStudySubjectList.addSubject(testStudySubject0);
        assertEquals(1, testStudySubjectList.getSubjectList().size());
        assertEquals(testStudySubject0, testStudySubjectList.getSubjectList().get(0));
    }

    @Test
    void testAddMultipleSubjects() {
        testStudySubjectList.addSubject(testStudySubject0);
        testStudySubjectList.addSubject(testStudySubject1);
        assertEquals(2, testStudySubjectList.getSubjectList().size());
        assertEquals(testStudySubject0, testStudySubjectList.getSubjectList().get(0));
        assertEquals(testStudySubject1, testStudySubjectList.getSubjectList().get(1));
    }

    @Test
    void testFindSubjectEmptyList() {
        try {
            testStudySubjectList.findSubject("test");
            fail("Expected not to run");
        } catch (NoSuchNameException e) {
            // expected
        }
    }

    @Test
    void testFindSubjectNotFound() {
        testStudySubjectList.addSubject(testStudySubject0);
        testStudySubjectList.addSubject(testStudySubject1);
        try {
            testStudySubjectList.findSubject("test");
            fail("Expected not to run");
        } catch (NoSuchNameException e) {
            // expected
        }
        assertEquals(2, testStudySubjectList.getSubjectList().size());
    }

    @Test
    void testFindSubjectFound() {
        testStudySubjectList.addSubject(testStudySubject0);
        testStudySubjectList.addSubject(testStudySubject1);
        testStudySubject0.setSubject("Physics");
        testStudySubject1.setSubject("Math");
        try {
            StudySubject subject = testStudySubjectList.findSubject("Math");
            assertEquals(testStudySubject1, subject);
            // expected
        } catch (NoSuchNameException e) {
            fail("Expected not to run");
        }
        assertEquals(2, testStudySubjectList.getSubjectList().size());
    }
}
