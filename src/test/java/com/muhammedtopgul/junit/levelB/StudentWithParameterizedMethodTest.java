package com.muhammedtopgul.junit.levelB;

import com.muhammedtopgul.enumeration.CourseType;
import com.muhammedtopgul.model.Course;
import com.muhammedtopgul.model.LecturerCourseRecord;
import com.muhammedtopgul.model.Semester;
import com.muhammedtopgul.model.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

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

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class EnumSourceTest {
        @BeforeAll
        void setUp() {
            student = new Student("1", "Muhammed", "Topgul");
        }

        @ParameterizedTest
        @EnumSource(CourseType.class)
        void addCourseToStudent(CourseType courseType) {
            Course course = Course.builder().code(String.valueOf(new Random().nextInt(200))).courseType(courseType).build();
            LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);
            assertFalse(student.getStudentCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
        }

        @ParameterizedTest
        @EnumSource(value = CourseType.class, names = "MANDATORY")
        void addMandatoryCourseToStudent(CourseType courseType) {
            Course course = Course.builder().code(String.valueOf(new Random().nextInt(200))).courseType(courseType).build();
            LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);
            assertFalse(student.getStudentCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
            assertEquals(CourseType.MANDATORY, course.getCourseType());
        }

        @ParameterizedTest
        @EnumSource(value = CourseType.class, mode = EnumSource.Mode.EXCLUDE, names = "MANDATORY")
        void addElectiveCourseToStudent(CourseType courseType) {
            Course course = Course.builder().code(String.valueOf(new Random().nextInt(200))).courseType(courseType).build();
            LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);
            assertFalse(student.getStudentCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
            assertEquals(CourseType.ELECTIVE, course.getCourseType());
        }
    }
}
