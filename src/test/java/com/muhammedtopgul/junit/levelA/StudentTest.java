package com.muhammedtopgul.junit.levelA;

import com.muhammedtopgul.model.LecturerCourseRecord;
import com.muhammedtopgul.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author muhammed-topgul
 * @since 11/09/2022 16:38
 */
@DisplayName("Level A (Beginner) Student Tests")
public class StudentTest {
    private Student student001;
    private Student student002;
    private Student student003;

    @BeforeEach
    void setUp() {
        student001 = Student.builder()
                .id("1")
                .name("Muhammed")
                .surname("Topgul")
                .build();

        student002 = Student.builder()
                .id("2")
                .name("John")
                .surname("Doe")
                .build();

        student003 = student002;
    }

    @Test
    @DisplayName("Test every student must have an id, name and surname.")
    void shouldCreateStudentWithIdNameAndSurname() {
        assertEquals("Muhammed", student001.getName());
        assertEquals("Muhammed", student001.getName(), "Student's name");
        assertNotEquals("Micheal", student001.getName(), "Student's name");

        assertTrue(student001.getName().startsWith("M"));
        assertTrue(student001.getName().startsWith("Mu"), "Student's name starts with 'Mu'");
        assertFalse(student002.getName().endsWith("M"), "Student's name ends with M");


        Object[] studentsNameArray = Stream.of(student001, student002)
                .map(Student::getName)
                .toArray();

        assertArrayEquals(new String[]{student001.getName(), student002.getName()},
                studentsNameArray);

        assertSame(student003, student002);
        assertNotSame(student001, student002);
    }

    @Test
    @DisplayName("Test every student must have an id, name and surname with grouped assertions.")
    void shouldCreateStudentWithIdNameAndSurnameWithGroupedAssertions() {
        assertAll("Student's id, name and surname check",
                () -> assertEquals("Muhammed", student001.getName()),
                () -> assertEquals("Topgul", student001.getSurname(), "Student's surname"),
                () -> assertNotEquals("2", student001.getId(), "Student's id")
        );

        assertAll("Student's name character check",
                () -> assertTrue(student001.getName().startsWith("M")),
                () -> assertTrue(student001.getName().startsWith("Muh")),
                () -> assertFalse(student002.getName().startsWith("Muh"))
        );

        assertAll("Dependent assertions",
                () -> {
                    Object[] studentsNameArray = Stream.of(student001, student002)
                            .map(Student::getName)
                            .toArray();

                    assertArrayEquals(new String[]{student001.getName(), student002.getName()},
                            studentsNameArray);
                },
                () -> {
                    assertSame(student003, student002);
                    assertNotSame(student001, student002);
                }
        );
    }

    @Test
    @DisplayName("Got an exception when add a null lecturer course record to student.")
    void throwsExceptionWhenAddToNullCourseToStudent() {
        assertThrows(IllegalArgumentException.class, () -> student001.addCourse(null));

        assertThrows(
                IllegalArgumentException.class,
                () -> student001.addCourse(null),
                "Throws IllegalArgumentException");

        IllegalArgumentException illegalArgumentException = assertThrows(
                IllegalArgumentException.class,
                () -> student001.addCourse(null)
        );
        assertEquals("Can't add course with null lecturer course record", illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("Add course to a student lecturer course record timeout.")
    void addCourseToStudentWithATimeConstraint() {
        assertTimeout(Duration.ofMillis(10), () -> {
            // Nothing will be done and this code run under 10ms
        });

        final String result = assertTimeout(Duration.ofMillis(10), () -> {
            // Return a string and this code run under 10ms
            return "Some staring result";
        });
        assertEquals("Some staring result", result);

        LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord();
        assertTimeout(Duration.ofMillis(6), () -> student001.addCourse(lecturerCourseRecord));

        assertTimeoutPreemptively(Duration.ofMillis(6), () -> student001.addCourse(lecturerCourseRecord));
    }
}
