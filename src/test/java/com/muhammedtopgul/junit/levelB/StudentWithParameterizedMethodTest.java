package com.muhammedtopgul.junit.levelB;

import com.muhammedtopgul.model.Course;
import com.muhammedtopgul.model.LecturerCourseRecord;
import com.muhammedtopgul.model.Semester;
import com.muhammedtopgul.model.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author muhammed-topgul
 * @since 18/09/2022 01:23
 */
public class StudentWithParameterizedMethodTest {
    private Student student;

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class ValueSourceTest {
        private int studentCourseSize = 0;

        @BeforeAll
        void setUp() {
            student = new Student("1", "Muhammed", "Topgul");
        }

        @ParameterizedTest(name = "{index}. New Course ({arguments}) Record Added with Code")
        @ValueSource(strings = {"101", "102", "103"})
        void addCourseToStudent(String courseCode) {
            LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course(courseCode), new Semester());
            student.addCourse(lecturerCourseRecord);
            studentCourseSize++;
            assertEquals(studentCourseSize, student.getStudentCourseRecords().size());
            assertTrue(student.isTakeCourse(new Course(courseCode)));
        }
    }
}
