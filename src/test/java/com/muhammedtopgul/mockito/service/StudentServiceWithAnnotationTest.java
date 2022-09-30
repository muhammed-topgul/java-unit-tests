package com.muhammedtopgul.mockito.service;

import com.muhammedtopgul.model.*;
import com.muhammedtopgul.repository.StudentRepository;
import com.muhammedtopgul.service.CourseService;
import com.muhammedtopgul.service.LecturerService;
import com.muhammedtopgul.service.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;


/**
 * @author muhammed-topgul
 * @since 30/09/2022 17:16
 */
@DisplayName("Mockito Student Service with Annotation Tests")
class StudentServiceWithAnnotationTest {
    // Mock
    @Mock
    private CourseService courseService;

    @Mock
    private LecturerService lecturerService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private Lecturer lecturer;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

    @Captor
    private ArgumentCaptor<Student> studentArgumentCaptor;

    @Test
    void addCourse() {
        Course course = new Course("101");
        Semester semester = new Semester();

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

    @Test
    void deleteStudent() {
        Student student = new Student("1", "Muhammed", "Topgul");

        Mockito.when(studentRepository.findById(anyString()))
                .thenAnswer(invocation -> Optional.of(student));

        doNothing()
                .doThrow(new IllegalArgumentException("There is no student in repo"))
                .doAnswer(invocation -> {
                    Student argument = invocation.getArgument(0);
                    System.out.printf("Student(%s) will be deleted.%n", argument);
                    return null;
                }).when(studentRepository).delete(student);

        studentService.deleteStudent("1");
        assertThatIllegalArgumentException().isThrownBy(() -> studentService.deleteStudent("1"))
                .withMessageContaining("There is no student");
        studentService.deleteStudent("1");

        Mockito.verify(studentRepository, Mockito.times(3)).findById(stringArgumentCaptor.capture());
        Mockito.verify(studentRepository, Mockito.times(3)).delete(studentArgumentCaptor.capture());

        assertThat(stringArgumentCaptor.getAllValues())
                .hasSize(3)
                .containsOnly("1", "1", "1");

        assertThat(studentArgumentCaptor.getAllValues())
                .hasSize(3)
                .containsOnly(student);
    }
}