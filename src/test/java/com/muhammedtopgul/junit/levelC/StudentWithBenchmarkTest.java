package com.muhammedtopgul.junit.levelC;

import com.muhammedtopgul.junit.levelC.extension.BenchmarkExtension;
import com.muhammedtopgul.model.LecturerCourseRecord;
import com.muhammedtopgul.model.Student;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author muhammed-topgul
 * @since 27/09/2022 22:08
 */
@ExtendWith(BenchmarkExtension.class)
@DisplayName("Level C (High Level) Student with Benchmark Tests")
public class StudentWithBenchmarkTest {
    private Student student001;

    @BeforeEach
    void setUp() {
        student001 = Student.builder()
                .id("1")
                .name("Muhammed")
                .surname("Topgul")
                .build();
    }

    @Test
    @Tags({
            @Tag("addCourse"),
            @Tag("exceptional")
    })
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
    @Tag("addCourse")
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
