package com.muhammedtopgul.service;

import com.muhammedtopgul.model.Course;
import com.muhammedtopgul.model.Lecturer;
import com.muhammedtopgul.model.Semester;

import java.util.Optional;

/**
 * @author muhammed-topgul
 * @since 30/09/2022 00:07
 */
public interface LecturerService {
    Optional<Lecturer> findLecturer(Course course, Semester semester);
}