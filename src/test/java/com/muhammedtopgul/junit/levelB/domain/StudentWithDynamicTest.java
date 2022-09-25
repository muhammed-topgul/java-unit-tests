package com.muhammedtopgul.junit.levelB.domain;

import com.muhammedtopgul.enumeration.CourseType;
import com.muhammedtopgul.model.Course;
import com.muhammedtopgul.model.LecturerCourseRecord;
import com.muhammedtopgul.model.Semester;
import com.muhammedtopgul.model.Student;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * @author muhammed-topgul
 * @since 19/09/2022 12:15
 */
public class StudentWithDynamicTest {
    @TestFactory
    Stream<DynamicNode> addCourseToStudentWithCourseCodeAndCourseType() {
        final Student student = new Student("1", "Muhammed", "Topgul");
        return Stream.of("101", "102", "103")
                .map(courseCode -> dynamicContainer("Add course(" + courseCode + ") to student",
                        Stream.of(CourseType.MANDATORY, CourseType.ELECTIVE)
                                .map(courseType -> dynamicTest("Add course(" + courseType + ") to student",
                                        () -> {
                                            Course course = Course.builder().code(courseCode).courseType(courseType).build();
                                            LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
                                            student.addCourse(lecturerCourseRecord);
                                            assertTrue(student.isTakeCourse(course));
                                        }))
                ));
    }
}
