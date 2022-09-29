package com.muhammedtopgul.assertj;

import com.muhammedtopgul.model.*;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * @author muhammed-topgul
 * @since 28/09/2022 23:31
 */
@DisplayName("AssertJ Student Tests")
public class StudentTest {
    @Test
    void createStudent() {
        final Student student = new Student("1", "Muhammed", "Topgul");

        assertThat(student.getName())
                .as("Student's name %s", student.getName())
                .doesNotContainOnlyWhitespaces()
                .isNotEmpty()
                .isNotBlank()
                .isEqualTo("Muhammed")
                .isEqualToIgnoringCase("muhammed")
                .isSameAs("Muhammed")
                .isIn("Muhammed", "John", "Sam")
                .isNotIn("James", "Brown")
                .startsWith("Mu")
                .doesNotContain("J")
                .contains("ham")
                .contains(List.of("u", "h", "d"))
                .hasSize(8)
                .matches("^M\\w{6}d$")
        ;
    }

    @Test
    void addCourseToStudent() {
        final Student muhammed = new Student("1", "Muhammed", "Topgul", LocalDate.of(1997, 1, 1));
        final Student john = new Student("2", "John", "Doe", LocalDate.of(1998, 1, 1));
        final Student sam = new Student("3", "Sam", "Sun", LocalDate.of(1984, 1, 1));
        final Student jack = new Student("4", "Jack", "William", LocalDate.of(1976, 1, 1));
        final Student brown = new Student("5", "Brown", "Wen", LocalDate.of(1999, 1, 1));
        final Student ketty = new Student("6", "Ketty", "Johnson", LocalDate.of(1997, 1, 1));

        final List<Student> students = List.of(muhammed, john, sam, jack, ketty);

        assertThat(students)
                .as("Student's List")
                .isNotNull()
                .isNotEmpty()
                .hasSize(5)
                .contains(muhammed, sam, jack)
                .containsOnly(muhammed, jack, sam, john, ketty)
                .containsExactlyInAnyOrder(muhammed, jack, sam, john, ketty)
                .containsExactly(muhammed, john, sam, jack, ketty);


        assertThat(students)
                .filteredOn(student -> student.getBirthDate().until(LocalDate.now(), ChronoUnit.YEARS) >= 30)
                .hasSize(2)
                .containsOnly(sam, jack);


        assertThat(students)
                .filteredOn("birthDate", in(LocalDate.of(1997, 1, 1)))
                .hasSize(2)
                .containsOnly(muhammed, ketty);


        assertThat(students)
                .extracting(Student::getName) // like Stream.map
                .filteredOn(name -> name.contains("a"))
                .hasSize(3)
                .containsOnly("Muhammed", "Sam", "Jack");


        assertThat(students)
                .filteredOn(student -> student.getName().contains("a"))
                .extracting(Student::getName, Student::getSurname)
                .containsOnly(
                        Tuple.tuple("Muhammed", "Topgul"),
                        Tuple.tuple("Sam", "Sun"),
                        Tuple.tuple("Jack", "William")
                );

        final LecturerCourseRecord lecturerCourseRecord101 = new LecturerCourseRecord(new Course("101"), new Semester());
        final LecturerCourseRecord lecturerCourseRecord102 = new LecturerCourseRecord(new Course("102"), new Semester());
        final LecturerCourseRecord lecturerCourseRecord103 = new LecturerCourseRecord(new Course("103"), new Semester());

        muhammed.addCourse(lecturerCourseRecord101);
        muhammed.addCourse(lecturerCourseRecord103);

        sam.addCourse(lecturerCourseRecord101);
        sam.addCourse(lecturerCourseRecord102);
        sam.addCourse(lecturerCourseRecord103);


        assertThat(students)
                .filteredOn(student -> student.getName().equals("Muhammed") || student.getName().equals("Sam"))
                .flatExtracting(Student::getStudentCourseRecords)
                .hasSize(5)
                .filteredOn(studentCourseRecord -> studentCourseRecord.getLecturerCourseRecord().getCourse().getCode().equals("101"))
                .hasSize(2);
    }

    @Test
    void anotherCreateStudentTest() {
        final Student muhammed = new Student("1", "Muhammed", "Topgul");
        final Student john = new Student("2", "John", "Topgul");

        assertThat(muhammed)
                .as("Check Student Info")
                .isNotNull()
                .hasSameClassAs(john)
                .isExactlyInstanceOf(Student.class)
                .isInstanceOf(Person.class)
                .isNotEqualTo(john)
                .isEqualToComparingOnlyGivenFields(john, "surname")
                .isEqualToIgnoringGivenFields(john, "id", "name", "birthDate")
                .matches(student -> student.getName().equals("Muhammed"))
                .hasFieldOrProperty("name")
                // .hasNoNullFieldsOrProperties()
                .extracting(Student::getName, Student::getSurname)
                .containsOnly("Muhammed", "Topgul");

        CustomStudentAssertion.assertThat(muhammed)
                .as("Student Custom Assertion")
                .hasName("Muhammed");
    }

    @Test
    void addCourseToStudentWithExceptionalCases() {
        final Student muhammed = new Student("1", "Muhammed", "Topgul");

        assertThatThrownBy(() -> muhammed.addCourse(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Can't add course with")
                .hasStackTraceContaining("Student");

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> muhammed.addCourse(null))
                .withMessageContaining("Can't add course with")
                .withNoCause();

        assertThatIllegalArgumentException()
                .isThrownBy(() -> muhammed.addCourse(null))
                .withMessageContaining("Can't add course with")
                .withNoCause();

        assertThatCode(() -> muhammed.addCourse(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Can't add course with");
    }
}
