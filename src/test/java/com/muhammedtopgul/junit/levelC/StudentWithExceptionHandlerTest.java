package com.muhammedtopgul.junit.levelC;

import com.muhammedtopgul.junit.levelC.extension.IllegalArgumentExceptionHandler;
import com.muhammedtopgul.model.Student;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author muhammed-topgul
 * @since 27/09/2022 22:17
 */
@ExtendWith(IllegalArgumentExceptionHandler.class)
@DisplayName("Level C (High Level) Student with Exception Handler Tests")
public class StudentWithExceptionHandlerTest {
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
    @DisplayName("Got an exception when add a null lecturer course record to student.")
    void throwsExceptionWhenAddToNullCourseToStudent() {
        student001.addCourse(null);
    }
}
