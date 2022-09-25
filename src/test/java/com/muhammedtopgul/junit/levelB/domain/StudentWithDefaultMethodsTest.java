package com.muhammedtopgul.junit.levelB.domain;

import com.muhammedtopgul.model.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author muhammed-topgul
 * @since 18/09/2022 00:53
 */
@DisplayName("Level B (Intermediate Level) Student with Default Methods Test")
public class StudentWithDefaultMethodsTest implements CreateDomain<Student>, TestLifeCycleReporter {
    @Override
    public Student createDomain() {
        return new Student("1", "Muhammed", "Topgul");
    }

    @Test
    void createStudent() {
        Student student = createDomain();
        assertAll("Student Tests",
                () -> assertEquals("1", student.getId()),
                () -> assertEquals("Muhammed", student.getName()),
                () -> assertEquals("Topgul", student.getSurname())
        );
    }
}


