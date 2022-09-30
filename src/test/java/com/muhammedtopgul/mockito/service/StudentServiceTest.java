package com.muhammedtopgul.mockito.service;

import com.muhammedtopgul.model.*;
import com.muhammedtopgul.repository.StudentRepository;
import com.muhammedtopgul.service.CourseService;
import com.muhammedtopgul.service.LecturerService;
import com.muhammedtopgul.service.StudentService;
import com.muhammedtopgul.service.StudentServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;


/**
 * @author muhammed-topgul
 * @since 30/09/2022 16:48
 */
@DisplayName("Mockito Student Service Tests")
class StudentServiceTest {
    @Test
    void addCourse() {
        Course course = new Course("101");
        Semester semester = new Semester();

        // Mock
        CourseService courseService = Mockito.mock(CourseService.class);
        LecturerService lecturerService = Mockito.mock(LecturerService.class);
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        Lecturer lecturer = Mockito.mock(Lecturer.class);

        // When
        Mockito.when(studentRepository.findById(any()))
                .thenReturn(Optional.of(new Student("1", "Muhammed", "Topgul")));
        Mockito.when(courseService.findCourse(any()))
                .thenReturn(Optional.of(course));
        Mockito.when(lecturerService.findLecturer(any(), any()))
                .thenReturn(Optional.of(lecturer));
        Mockito.when(lecturer.lecturerCourseRecord(any(), any()))
                .thenReturn(new LecturerCourseRecord(course, semester));

        // Operation
        StudentService studentService = new StudentServiceImpl(courseService, lecturerService, studentRepository);
        studentService.addCourse("1", course, semester);

        Optional<Student> optionalStudent = studentService.findStudent("1");

        assertThat(optionalStudent)
                .as("Student Test")
                .isPresent()
                .get()
                .matches(student -> student.isTakeCourse(course));

        // Verify
        Mockito.verify(courseService).findCourse(course);
        Mockito.verify(courseService, Mockito.times(1)).findCourse(course);
        Mockito.verify(courseService, Mockito.atLeast(1)).findCourse(course);
        Mockito.verify(courseService, Mockito.atMost(1)).findCourse(course);

        Mockito.verify(studentRepository, Mockito.times(2)).findById(any(String.class));
        Mockito.verify(studentRepository, Mockito.atLeast(1)).findById(any(String.class));
        Mockito.verify(studentRepository, Mockito.atMost(2)).findById(any());

        Mockito.verify(lecturerService).findLecturer(any(Course.class), any(Semester.class));

        Mockito.verify(lecturer).lecturerCourseRecord(argThat(arg -> arg.getCode().equals("101")), any(Semester.class));
    }
}