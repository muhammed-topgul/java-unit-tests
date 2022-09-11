package com.muhammedtopgul.junit.levelA.domain;

import com.muhammedtopgul.annotation.TestOnWindowsWithJre11;
import com.muhammedtopgul.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author muhammed-topgul
 * @since 11/09/2022 18:31
 */
@EnabledOnOs(OS.WINDOWS)
public class StudentConditionalTest {
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
    @EnabledOnOs(OS.WINDOWS)
    void shouldCreateStudentOnlyOnWindows() {
        assertAll("Student information",
                () -> assertEquals("1", student001.getId()),
                () -> assertEquals("Muhammed", student001.getName()),
                () -> assertEquals("Topgul", student001.getSurname())
        );
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    void shouldCreateStudentDisabledOnWindows() {
        assertAll("Student information",
                () -> assertEquals("1", student001.getId()),
                () -> assertEquals("Muhammed", student001.getName()),
                () -> assertEquals("Topgul", student001.getSurname())
        );
    }

    @Test
    @EnabledOnJre(JRE.JAVA_11)
    void shouldCreateStudentOnlyOnJRE17() {
        assertAll("Student information",
                () -> assertEquals("1", student001.getId()),
                () -> assertEquals("Muhammed", student001.getName()),
                () -> assertEquals("Topgul", student001.getSurname())
        );
    }

    @Test
    @DisabledOnJre({JRE.JAVA_8, JRE.JAVA_9, JRE.JAVA_10})
    void shouldCreateStudentDisabledOnJRE8AndJRE9AndJRE10() {
        assertAll("Student information",
                () -> assertEquals("1", student001.getId()),
                () -> assertEquals("Muhammed", student001.getName()),
                () -> assertEquals("Topgul", student001.getSurname())
        );
    }

    @Test
    @EnabledIfSystemProperty(named = "ENV", matches = "dev")
    void shouldCreateStudentOnSystemProperty() {
        assertAll("Student information",
                () -> assertEquals("1", student001.getId()),
                () -> assertEquals("Muhammed", student001.getName()),
                () -> assertEquals("Topgul", student001.getSurname())
        );
    }

    @Test
    @TestOnWindowsWithJre11
    void shouldCreateStudentOnMyAnnotation() {
        assertAll("Student information",
                () -> assertEquals("1", student001.getId()),
                () -> assertEquals("Muhammed", student001.getName()),
                () -> assertEquals("Topgul", student001.getSurname())
        );
    }
}
