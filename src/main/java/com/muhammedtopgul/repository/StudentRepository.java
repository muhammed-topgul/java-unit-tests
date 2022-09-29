package com.muhammedtopgul.repository;

import com.muhammedtopgul.model.Student;

import java.util.Optional;

/**
 * @author muhammed-topgul
 * @since 30/09/2022 00:09
 */
public interface StudentRepository {
    Optional<Student> findById(String id);

    Student save(Student student);

    void delete(Student student);
}
