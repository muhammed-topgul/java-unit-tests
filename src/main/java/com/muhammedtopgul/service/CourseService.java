package com.muhammedtopgul.service;

import com.muhammedtopgul.model.Course;
import com.muhammedtopgul.model.Department;

import java.util.Optional;

/**
 * @author muhammed-topgul
 * @since 30/09/2022 00:06
 */
public interface CourseService {
    Optional<Course> findCourse(Course course);

    Optional<Course> findCourse(Department department, String code, String name);
}
