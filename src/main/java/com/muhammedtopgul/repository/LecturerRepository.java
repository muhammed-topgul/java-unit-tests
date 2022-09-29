package com.muhammedtopgul.repository;

import com.muhammedtopgul.model.Course;
import com.muhammedtopgul.model.Lecturer;
import com.muhammedtopgul.model.Semester;

import java.util.Optional;

/**
 * @author muhammed-topgul
 * @since 30/09/2022 00:08
 */
public interface LecturerRepository {
    Optional<Lecturer> findByCourseAndSemester(Course course, Semester semester);
}
