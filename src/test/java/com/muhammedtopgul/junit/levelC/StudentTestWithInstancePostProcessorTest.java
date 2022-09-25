package com.muhammedtopgul.junit.levelC;

import com.muhammedtopgul.exception.NotActiveSemesterException;
import com.muhammedtopgul.junit.levelC.extension.DropCourseTestInstancePostProcessor;
import com.muhammedtopgul.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * @author muhammed-topgul
 * @since 25/09/2022 23:19
 */
@ExtendWith(DropCourseTestInstancePostProcessor.class)
@Tag("dropStudent")
@DisplayName("Level C (High Level) Student with Instance Post Processor Tests")
public class StudentTestWithInstancePostProcessorTest {
    public Student student001;
    public Semester addDropPeriodOpenSemester;
    public Semester addDropPeriodClosedSemester;
    public Semester notActiveSemester;

    @TestFactory
    Stream<DynamicTest> dropCourseFromStudentFactory() {
        return Stream.of(
                dynamicTest("throws illegal argument exception for null lecturer course record", () -> {
                    assertThrows(IllegalArgumentException.class, () -> student001.dropCourse(null));
                }),
                dynamicTest("throws illegal argument exception if the student001 did't register course before", () -> {
                    final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), new Semester());
                    assertThrows(IllegalArgumentException.class, () -> student001.dropCourse(lecturerCourseRecord));
                }),
                dynamicTest("throws not active semester exception if the semester is not active", () -> {
                    assumeTrue(!notActiveSemester.isActive());
                    final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), notActiveSemester);
                    Student student002 = new Student("1", "Muhammed", "Topgul", Set.of(new StudentCourseRecord(lecturerCourseRecord)));
                    assertThrows(NotActiveSemesterException.class, () -> student002.dropCourse(lecturerCourseRecord));
                }),
                dynamicTest("throws not active semester exception if the add drop period is closed for the semester", () -> {
                    assumeTrue(!addDropPeriodClosedSemester.isAddDropAllowed());
                    final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), addDropPeriodClosedSemester);
                    Student student002 = new Student("1", "Muhammed", "Topgul", Set.of(new StudentCourseRecord(lecturerCourseRecord)));
                    assertThrows(NotActiveSemesterException.class, () -> student002.dropCourse(lecturerCourseRecord));
                }),
                dynamicTest("drop course from student", () -> {
                    assumeTrue(addDropPeriodOpenSemester.isAddDropAllowed());
                    final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), addDropPeriodOpenSemester);
                    Student student002 = new Student("1", "Muhammed", "Topgul", Set.of(new StudentCourseRecord(lecturerCourseRecord)));
                    assertEquals(1, student002.getStudentCourseRecords().size());
                    student002.dropCourse(lecturerCourseRecord);
                    assertTrue(student002.getStudentCourseRecords().isEmpty());
                })
        );
    }
}
