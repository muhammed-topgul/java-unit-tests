package com.muhammedtopgul.model;

import com.muhammedtopgul.enumeration.Grade;
import com.muhammedtopgul.exception.NoCourseFoundForStudentException;
import com.muhammedtopgul.exception.NotActiveSemesterException;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * @author muhammed-topgul
 * @since 11/09/2022 01:52
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    @Builder.Default
    private Set<StudentCourseRecord> studentCourseRecords = new HashSet<>();
    private Department department;

    public Student(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        if (studentCourseRecords == null) {
            studentCourseRecords = new HashSet<>();
        }
    }

    public void addCourse(LecturerCourseRecord lecturerCourseRecord) {
        if (lecturerCourseRecord == null) {
            throw new IllegalArgumentException("Can't add course with null lecturer course record");
        }
        final StudentCourseRecord studentCourseRecord = new StudentCourseRecord(lecturerCourseRecord);
        studentCourseRecords.add(studentCourseRecord);
    }

    public void dropCourse(LecturerCourseRecord lecturerCourseRecord) {
        if (lecturerCourseRecord == null) {
            throw new IllegalArgumentException("Can't drop course with null lecturer course record");
        }

        final StudentCourseRecord studentCourseRecordWillBeDropped = studentCourseRecords.stream()
                .filter(studentCourseRecord -> studentCourseRecord.getLecturerCourseRecord().equals(lecturerCourseRecord))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no student course record for given lecturer course record"));

        if (!studentCourseRecordWillBeDropped.getLecturerCourseRecord().getSemester().isAddDropAllowed()) {
            throw new NotActiveSemesterException("Add drop period is closed for the semester");
        }

        studentCourseRecords.remove(studentCourseRecordWillBeDropped);
    }

    public boolean isTakeCourse(Course course) {
        return studentCourseRecords.stream()
                .map(StudentCourseRecord::getLecturerCourseRecord)
                .map(LecturerCourseRecord::getCourse)
                .anyMatch(course1 -> course1.equals(course));
    }

    public void addGrade(LecturerCourseRecord lecturerCourseRecord, Grade grade) {
        final StudentCourseRecord studentCourseRecord1 = studentCourseRecords.stream()
                .filter(studentCourseRecord -> studentCourseRecord.getLecturerCourseRecord().equals(lecturerCourseRecord))
                .findAny()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                new NoCourseFoundForStudentException(
                                        String.format("Student didn't take any course for lecturer course record: %s",
                                                lecturerCourseRecord == null ? "null" : lecturerCourseRecord.getCourse().getCode()))

                        )

                );
        studentCourseRecord1.setGrade(grade);
    }

    public BigDecimal gpa() {
        int totalCredit = 0;
        BigDecimal weightedGpa = BigDecimal.ZERO;

        for (StudentCourseRecord studentCourseRecord : studentCourseRecords) {
            totalCredit += courseCredit(studentCourseRecord);
            weightedGpa = weightedGpa.add(BigDecimal.valueOf(courseCredit(studentCourseRecord)).multiply(courseGrade(studentCourseRecord)));
        }

        return weightedGpa.divide(BigDecimal.valueOf(totalCredit), 2, RoundingMode.HALF_UP);
    }

    private int courseCredit(StudentCourseRecord studentCourseRecord) {
        return studentCourseRecord.getLecturerCourseRecord().getCredit();
    }

    private BigDecimal courseGrade(StudentCourseRecord studentCourseRecord) {
        return studentCourseRecord.getGrade().getGradeInNumber();
    }

    @Override
    public String toString() {
        return String.format("Id:%s => %s %s", getId(), getName(), getSurname());
    }
}
