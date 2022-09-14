package com.muhammedtopgul.junit.levelA.domain;

import com.muhammedtopgul.exception.NotActiveSemesterException;
import com.muhammedtopgul.model.Course;
import com.muhammedtopgul.model.Lecturer;
import com.muhammedtopgul.model.LecturerCourseRecord;
import com.muhammedtopgul.model.Semester;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author muhammed-topgul
 * @since 15/09/2022 00:57
 */
@DisplayName("Level A (Beginner) Lecturer Tests")
public class LecturerTest {
    private Lecturer lecturer;
    private LecturerCourseRecord lecturerCourseRecord;

    @BeforeEach
    void setUp() {
        lecturer = new Lecturer();
        lecturerCourseRecord = new LecturerCourseRecord(new Course(), new Semester());
    }

    @Test
    @DisplayName("When a lecturer course record is added to lecturer then lecturer course record size increase")
    void whenACourseIsAddedToLecturerThenLecturerCourseSizeIncrease() {
        assertEquals(0, lecturer.getLecturerCourseRecords().size());
        lecturer.addLecturerCourseRecord(lecturerCourseRecord);
        assertEquals(1, lecturer.getLecturerCourseRecords().size());
    }

    @Test
    @DisplayName("Lecturer course record has lecturer info when added to a lecturer")
    void lecturerCourseRecordHasLecturerInfoWhenAddedToALecturer() {
        lecturer.addLecturerCourseRecord(lecturerCourseRecord);
        assertEquals(lecturer, lecturerCourseRecord.getLecturer());
    }

    @Test
    @DisplayName("Throws illegal argument exception when a null course is added to lecturer")
    void throwsIllegalArgumentExceptionWhenANullCourseIsAddedToLecturer() {
        lecturerCourseRecord = new LecturerCourseRecord(null, new Semester());
        IllegalArgumentException illegalArgumentException = assertThrows(
                IllegalArgumentException.class,
                () -> lecturer.addLecturerCourseRecord(lecturerCourseRecord)
        );
        assertEquals("Can't add a null course to lecturer", illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("Throws not active semester exception when a course is added for not active semester to lecturer")
    void throwsNotActiveSemesterExceptionWhenACourseIsAddedForNotActiveSemesterToLecturer() {
        Semester activeSemester = new Semester();
        LocalDate lastYear = LocalDate.of(activeSemester.getYear() - 1, 1, 1);
        Semester notActiveSemester = new Semester(lastYear);

        lecturerCourseRecord = new LecturerCourseRecord(new Course(), notActiveSemester);
        NotActiveSemesterException notActiveSemesterException = assertThrows(
                NotActiveSemesterException.class,
                () -> lecturer.addLecturerCourseRecord(lecturerCourseRecord)
        );
        assertEquals(notActiveSemester.toString(), notActiveSemesterException.getMessage());
    }
}
