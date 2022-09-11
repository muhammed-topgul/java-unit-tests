package com.muhammedtopgul.junit.levelA;

import com.muhammedtopgul.model.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author muhammed-topgul
 * @since 11/09/2022 16:38
 */
@DisplayName("Level A (Beginner) Student Tests")
public class StudentTest {
    @Test
    @DisplayName("Test every student must have an id, name and surname.")
    void shouldCreateStudentWithIdNameAndSurname() {
        Student student001 = Student.builder()
                .id("1")
                .name("Muhammed")
                .surname("Topgul")
                .build();

        Student student002 = Student.builder()
                .id("2")
                .name("John")
                .surname("Doe")
                .build();


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

        Student student003 = student002;
        assertSame(student003, student002);
        assertNotSame(student001, student002);
    }
}
