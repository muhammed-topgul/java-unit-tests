package com.muhammedtopgul.service;

import com.muhammedtopgul.model.Course;
import com.muhammedtopgul.model.Lecturer;
import com.muhammedtopgul.model.Semester;
import com.muhammedtopgul.repository.LecturerRepository;

import java.util.Optional;

/**
 * @author muhammed-topgul
 * @since 30/09/2022 00:07
 */
public class LecturerServiceImpl implements LecturerService {
    private final LecturerRepository lecturerRepository;

    public LecturerServiceImpl(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public Optional<Lecturer> findLecturer(Course course, Semester semester) {
        if (course == null || semester == null) {
            throw new IllegalArgumentException("Can't find lecturer without course and semester");
        }
        return lecturerRepository.findByCourseAndSemester(course, semester);
    }
}
