package com.muhammedtopgul.junit.levelB;

import com.muhammedtopgul.model.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author muhammed-topgul
 * @since 18/09/2022 00:53
 */
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


