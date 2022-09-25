package com.muhammedtopgul.junit.levelB.domain;

import com.muhammedtopgul.model.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author muhammed-topgul
 * @since 15/09/2022 01:23
 */
@DisplayName("Level B (Intermediate Level) Student with Test Life Cycle Tests")
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentWithTestLifeCycleTest {
    private Student student001 = new Student("1", "Muhammed", "Topgul");

    @BeforeAll
    static void beforeAll() {
        System.out.println("This is before all.");
    }


    @Test
    @DisplayName("State cannot change when life cycle is per class")
    void stateCanChangeWhenLifeCycleIsPerClass() {
        assertEquals("Muhammed", student001.getName());
        changeStudentObject();
    }

    @Test
    @DisplayName("State cannot change when life cycle is per method")
    void stateCannotChangeWhenLifeCycleIsPerMethod() {
        assertEquals("Muhammed", student001.getName());
        changeStudentObject();
    }

    private void changeStudentObject() {
        student001 = new Student("2", "John", "Doe");
    }
}
