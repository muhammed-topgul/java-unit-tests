package com.muhammedtopgul.junit.levelB.domain;

import com.muhammedtopgul.model.LecturerCourseRecord;
import com.muhammedtopgul.model.Student;
import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * @author muhammed-topgul
 * @since 15/09/2022 01:37
 */
@DisplayName("Level B (Intermediate) Student with Nested Tests")
public class StudentWithNestedTest {
    private Student student001;
    private Student student002;
    private Student student003;

    @BeforeEach
    void setUp() {
        student001 = Student.builder()
                .id("1")
                .name("Muhammed")
                .surname("Topgul")
                .build();

        student002 = Student.builder()
                .id("2")
                .name("John")
                .surname("Doe")
                .build();

        student003 = student002;
    }

    @Nested
    @DisplayName("Create Student")
    class CreateStudent {
        @Test
        @Tag("createStudent")
        @DisplayName("Test every student must have an id, name and surname.")
        void shouldCreateStudentWithIdNameAndSurname() {
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

            assertSame(student003, student002);
            assertNotSame(student001, student002);
        }

        @Test
        @Tag("createStudent")
        @DisplayName("Test every student must have an id, name and surname with grouped assertions.")
        void shouldCreateStudentWithIdNameAndSurnameWithGroupedAssertions() {
            assertAll("Student's id, name and surname check",
                    () -> assertEquals("Muhammed", student001.getName()),
                    () -> assertEquals("Topgul", student001.getSurname(), "Student's surname"),
                    () -> assertNotEquals("2", student001.getId(), "Student's id")
            );

            assertAll("Student's name character check",
                    () -> assertTrue(student001.getName().startsWith("M")),
                    () -> assertTrue(student001.getName().startsWith("Muh")),
                    () -> assertFalse(student002.getName().startsWith("Muh"))
            );

            assertAll("Dependent assertions",
                    () -> {
                        Object[] studentsNameArray = Stream.of(student001, student002)
                                .map(Student::getName)
                                .toArray();

                        assertArrayEquals(new String[]{student001.getName(), student002.getName()},
                                studentsNameArray);
                    },
                    () -> {
                        assertSame(student003, student002);
                        assertNotSame(student001, student002);
                    }
            );
        }

        @Test
        @Tag("createStudent")
        @DisplayName("Test student creation only development machine.")
        void shouldCreateStudentWithNameAndSurnameAtDevelopmentMachine() {
            assumeTrue(
                    System.getProperty("ENV") != null,
                    "Aborting Test: System property ENV doesn't exist!"
            );

            assumeTrue(
                    System.getProperty("ENV").equals("dev"),
                    "Aborting Test: Not on a developer machine!"
            );

            assertAll("Student information",
                    () -> assertEquals("Muhammed", student001.getName()),
                    () -> assertEquals("Topgul", student001.getSurname()),
                    () -> assertEquals("1", student001.getId())
            );
        }

        @Test
        @Tag("createStudent")
        @DisplayName("Test student creation at different environments.")
        void shouldCreateStudentWithNameAndSurnameWithSpecificEnvironment() {
            final String env = System.getProperty("ENV");
            assumingThat(env != null && env.equals("dev"), () -> {
                student001.addCourse(new LecturerCourseRecord());
                assertEquals(1, student001.getStudentCourseRecords().size());
            });

            assertAll("Student information",
                    () -> assertEquals("Muhammed", student001.getName()),
                    () -> assertEquals("Topgul", student001.getSurname()),
                    () -> assertEquals("1", student001.getId())
            );
        }
    }


    @Nested
    @DisplayName("Add Course")
    class AddCourse {
        @Test
        @Tags({
                @Tag("addCourse"),
                @Tag("exceptional")
        })
        @DisplayName("Got an exception when add a null lecturer course record to student.")
        void throwsExceptionWhenAddToNullCourseToStudent() {
            assertThrows(IllegalArgumentException.class, () -> student001.addCourse(null));

            assertThrows(
                    IllegalArgumentException.class,
                    () -> student001.addCourse(null),
                    "Throws IllegalArgumentException");

            IllegalArgumentException illegalArgumentException = assertThrows(
                    IllegalArgumentException.class,
                    () -> student001.addCourse(null)
            );
            assertEquals("Can't add course with null lecturer course record", illegalArgumentException.getMessage());
        }

        @Test
        @Tag("addCourse")
        @DisplayName("Add course to a student lecturer course record timeout.")
        void addCourseToStudentWithATimeConstraint() {
            assertTimeout(Duration.ofMillis(10), () -> {
                // Nothing will be done and this code run under 10ms
            });

            final String result = assertTimeout(Duration.ofMillis(10), () -> {
                // Return a string and this code run under 10ms
                return "Some staring result";
            });
            assertEquals("Some staring result", result);

            LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord();
            assertTimeout(Duration.ofMillis(6), () -> student001.addCourse(lecturerCourseRecord));

            assertTimeoutPreemptively(Duration.ofMillis(6), () -> student001.addCourse(lecturerCourseRecord));
        }
    }

    @Nested
    @DisplayName("Drop Course from Student")
    class DropCourseFromStudent {
        @TestFactory
        Stream<DynamicTest> dropCourseFromStudentFactory() {
            final Student student = new Student("1", "Muhammed", "Topgul");
            return Stream.of(
                    dynamicTest("Throws IllegalArgumentException for null lecturer course record",
                            () -> {
                                assertThrows(IllegalArgumentException.class, () -> student.dropCourse(null));
                            }),
                    dynamicTest("Throws IllegalArgumentException if the student did not register course before",
                            () -> {
                                assertThrows(IllegalArgumentException.class, () -> student.dropCourse(new LecturerCourseRecord()));
                            })
            );
        }
    }
}
