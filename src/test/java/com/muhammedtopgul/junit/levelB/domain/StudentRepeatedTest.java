package com.muhammedtopgul.junit.levelB.domain;

import com.muhammedtopgul.model.Course;
import com.muhammedtopgul.model.LecturerCourseRecord;
import com.muhammedtopgul.model.Semester;
import com.muhammedtopgul.model.Student;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author muhammed-topgul
 * @since 18/09/2022 01:10
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentRepeatedTest implements TestLifeCycleReporter {
    private Student student;

    @BeforeAll
    void setUp() {
        student = new Student("1", "Muhammed", "Topgul");
    }

    @DisplayName("Add Course to Student")
    @RepeatedTest(value = 5, name = "{displayName} -> add one course to student and student has {currentRepetition} courses.")
    void addCourseToStudent(RepetitionInfo repetitionInfo) {
        LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(
                new Course(repetitionInfo.getCurrentRepetition()),
                new Semester()
        );
        student.addCourse(lecturerCourseRecord);
        assertEquals(repetitionInfo.getCurrentRepetition(), student.getStudentCourseRecords().size());
    }
}
