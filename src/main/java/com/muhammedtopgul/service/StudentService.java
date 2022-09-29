package com.muhammedtopgul.service;

import com.muhammedtopgul.enumeration.Grade;
import com.muhammedtopgul.model.Course;
import com.muhammedtopgul.model.Semester;
import com.muhammedtopgul.model.Student;
import com.muhammedtopgul.model.Transcript;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author muhammed-topgul
 * @since 30/09/2022 00:10
 */
public interface StudentService {
    void addCourse(String studentId, Course course);

    void addCourse(String studentId, Course course, Semester semester);

    void dropCourse(String studentId, Course course);

    void addGrade(String studentId, Course course, Grade grade);

    boolean isTakeCourse(String studentId, Course course);

    BigDecimal gpa(String studentId);

    List<Transcript> transcript(String studentId);

    Optional<Student> findStudent(String studentId);

    void deleteStudent(String studentId);
}
