package com.muhammedtopgul.junit.levelB.domain;

import com.muhammedtopgul.model.Student;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author muhammed-topgul
 * @since 18/09/2022 00:31
 */
@DisplayName("Level B (Intermediate Level) Student Test with TestInfo and TestReporter Parameters")
public class StudentParameterizedTest {
    private Student student;

    public StudentParameterizedTest(TestInfo testInfo) {
        System.out.println("Display Name: " + testInfo.getDisplayName());
        System.out.println("Tags: " + testInfo.getTags());
        System.out.println("Test Class: " + testInfo.getTestClass());
        System.out.println("Test Method: " + testInfo.getTestMethod());
    }

    @BeforeEach
    void setUp(TestInfo testInfo) {
        if (testInfo.getTags().contains("create")) {
            student = new Student("1", "Muhammed", "Topgul");
        } else {
            student = new Student("1", "John", "Doe");
        }
    }

    @Test
    @DisplayName("Create Student")
    @Tag("create")
    void createStudent(TestInfo testInfo) {
        assertTrue(testInfo.getTags().contains("create"));
        assertEquals("Muhammed", student.getName());
    }

    @Test
    @DisplayName("Add Course to Student")
    @Tag("addCourse")
    void addCourseToStudent(TestReporter testReporter, TestInfo testInfo) {
        assertTrue(testInfo.getTags().contains("addCourse"));
        testReporter.publishEntry("Start Time: ", LocalDateTime.now().toString());
        assertEquals("John", student.getName());
        testReporter.publishEntry("End Time: ", LocalDateTime.now().toString());
    }
}
