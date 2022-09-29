package com.muhammedtopgul.repository;

import com.muhammedtopgul.model.Course;
import com.muhammedtopgul.model.Department;

import java.util.Optional;

/**
 * @author muhammed-topgul
 * @since 30/09/2022 00:10
 */
public interface CourseRepository {
    Optional<Course> findByExample(Course course);

    Optional<Course> findByDepartmentAndCodeAndName(Department department, String code, String name);
}
