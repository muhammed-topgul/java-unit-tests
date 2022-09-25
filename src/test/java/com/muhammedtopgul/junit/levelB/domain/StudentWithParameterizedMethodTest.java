package com.muhammedtopgul.junit.levelB.domain;

import com.muhammedtopgul.enumeration.CourseType;
import com.muhammedtopgul.model.Course;
import com.muhammedtopgul.model.LecturerCourseRecord;
import com.muhammedtopgul.model.Semester;
import com.muhammedtopgul.model.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.provider.*;

import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

/**
 * @author muhammed-topgul
 * @since 18/09/2022 01:23
 */
@DisplayName("Level B (Intermediate Level) Student with Parameterized Method Test")
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

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class MethodSourceTest {
        private int studentCourseSize = 0;

        @BeforeAll
        void setUp() {
            student = new Student("1", "Muhammed", "Topgul");
        }

        @ParameterizedTest(name = "{index}. New Course ({arguments}) Record Added with Code")
        @MethodSource(value = "getCourseCodes")
        void addCourseToStudent(String courseCode) {
            LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course(courseCode), new Semester());
            student.addCourse(lecturerCourseRecord);
            studentCourseSize++;
            assertEquals(studentCourseSize, student.getStudentCourseRecords().size());
            assertTrue(student.isTakeCourse(new Course(courseCode)));
        }

        private Stream<String> getCourseCodes() {
            return Stream.of("101", "102", "103");
        }

        @ParameterizedTest(name = "{index}. New Course ({arguments}) Record Added with Code")
        @MethodSource(value = "getCourseCodesAndTypes")
        void addCourseToStudent(String courseCode, CourseType courseType) {
            Course course = new Course(courseCode, courseType);
            LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);
            studentCourseSize++;
            assertEquals(studentCourseSize, student.getStudentCourseRecords().size());
            assertTrue(student.isTakeCourse(new Course(courseCode)));
            assumingThat(courseCode.equals("101") || courseCode.equals("103"),
                    () -> assertEquals(CourseType.MANDATORY, courseType)
            );
            assumingThat(courseCode.equals("102"),
                    () -> assertEquals(CourseType.ELECTIVE, courseType)
            );
        }

        private Stream<Arguments> getCourseCodesAndTypes() {
            return Stream.of(
                    Arguments.of("101", CourseType.MANDATORY),
                    Arguments.of("102", CourseType.ELECTIVE),
                    Arguments.of("103", CourseType.MANDATORY)
            );
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class CsvSourceTest {
        private int studentCourseSize = 0;

        @BeforeAll
        void setUp() {
            student = new Student("1", "Muhammed", "Topgul");
        }

        @ParameterizedTest(name = "{index}. New Course ({arguments}) Record Added with Code")
        @CsvSource(value = {"101,MANDATORY", "102,ELECTIVE", "103,MANDATORY"})
        void addCourseToStudent(String courseCode, CourseType courseType) {
            Course course = new Course(courseCode, courseType);
            LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);
            studentCourseSize++;
            assertEquals(studentCourseSize, student.getStudentCourseRecords().size());
            assertTrue(student.isTakeCourse(new Course(courseCode)));
            assumingThat(courseCode.equals("101") || courseCode.equals("103"),
                    () -> assertEquals(CourseType.MANDATORY, courseType)
            );
            assumingThat(courseCode.equals("102"),
                    () -> assertEquals(CourseType.ELECTIVE, courseType)
            );
        }

        @ParameterizedTest(name = "{index}. New Course ({arguments}) Record Added with Code")
        @CsvFileSource(resources = "/courseCodeAndTypes.csv", numLinesToSkip = 1)
        void addCourseToStudentWithCsvFile(String courseCode, CourseType courseType) {
            Course course = new Course(courseCode, courseType);
            LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);
            studentCourseSize++;
            assertEquals(studentCourseSize, student.getStudentCourseRecords().size());
            assertTrue(student.isTakeCourse(new Course(courseCode)));
            assumingThat(courseCode.equals("101") || courseCode.equals("103"),
                    () -> assertEquals(CourseType.MANDATORY, courseType)
            );
            assumingThat(courseCode.equals("102"),
                    () -> assertEquals(CourseType.ELECTIVE, courseType)
            );
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class ArgumentSourceTest {
        private int studentCourseSize = 0;

        @BeforeAll
        void setUp() {
            student = new Student("1", "Muhammed", "Topgul");
        }

        @ParameterizedTest(name = "{index}. New Course ({arguments}) Record Added with Code")
        @ArgumentsSource(value = CourseCodeAndTypeProvider.class)
        void addCourseToStudent(String courseCode, CourseType courseType) {
            Course course = new Course(courseCode, courseType);
            LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);
            studentCourseSize++;
            assertEquals(studentCourseSize, student.getStudentCourseRecords().size());
            assertTrue(student.isTakeCourse(new Course(courseCode)));
            assumingThat(courseCode.equals("101") || courseCode.equals("103"),
                    () -> assertEquals(CourseType.MANDATORY, courseType)
            );
            assumingThat(courseCode.equals("102"),
                    () -> assertEquals(CourseType.ELECTIVE, courseType)
            );
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class ParameterTypeConversionTest {
        @BeforeAll
        void setUp() {
            student = new Student("1", "Muhammed", "Topgul");
        }

        @ParameterizedTest
        @ValueSource(strings = {"MANDATORY", "ELECTIVE"})
        void addCourseToStudent(CourseType courseType) {
            Course course = Course.builder().code(String.valueOf(new Random().nextInt(200))).courseType(courseType).build();
            LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);
            assertFalse(student.getStudentCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
        }

        @ParameterizedTest
        @ValueSource(strings = {"101", "102"})
        void addCourseToStudent(Course course) {
            LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);
            assertFalse(student.getStudentCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
        }

        @ParameterizedTest
        @ValueSource(strings = {"101", "102"})
        void addCourseToStudentWithConverter(@ConvertWith(CourseConvertor.class) Course course) {
            LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);
            assertFalse(student.getStudentCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
        }

        @ParameterizedTest
        @ValueSource(strings = {"01.09.2018", "01.01.2018", "01.06.2018"})
        void addCourseToStudentWithLocalDate(@JavaTimeConversionPattern("dd.MM.yyyy") LocalDate localDate) {
            Course course = Course.builder().code(String.valueOf(new Random().nextInt(200))).build();
            LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester(localDate));
            student.addCourse(lecturerCourseRecord);
            assertFalse(student.getStudentCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
        }
    }
}
