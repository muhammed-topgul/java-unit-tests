package com.muhammedtopgul.mockito.service;

import com.muhammedtopgul.model.Course;
import com.muhammedtopgul.model.Lecturer;
import com.muhammedtopgul.model.Semester;
import com.muhammedtopgul.repository.LecturerRepository;
import com.muhammedtopgul.service.LecturerService;
import com.muhammedtopgul.service.LecturerServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


/**
 * @author muhammed-topgul
 * @since 30/09/2022 00:24
 */
@DisplayName("Mockito Lecturer Service Tests")
class LecturerServiceTest {
    @Test
    void findLecturer() {
        Semester semester = new Semester();
        Course course = new Course("101");
        Lecturer lecturer = new Lecturer();

        LecturerRepository lecturerRepository = Mockito.mock(LecturerRepository.class);
        Mockito.when(lecturerRepository.findByCourseAndSemester(course, semester))
                .thenReturn(Optional.of(lecturer));

        LecturerService lecturerService = new LecturerServiceImpl(lecturerRepository);
        Optional<Lecturer> lecturerOptional = lecturerService.findLecturer(course, semester);

        assertThat(lecturerOptional)
                .isPresent()
                .get()
                .isSameAs(lecturer);

        Mockito.verify(lecturerRepository).findByCourseAndSemester(course, semester);
    }
}