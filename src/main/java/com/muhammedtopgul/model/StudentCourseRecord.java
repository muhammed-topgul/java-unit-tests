package com.muhammedtopgul.model;

import com.muhammedtopgul.enumeration.Grade;
import lombok.*;

/**
 * @author muhammed-topgul
 * @since 11/09/2022 01:52
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseRecord {
    private LecturerCourseRecord lecturerCourseRecord;
    private Grade grade;
    private CourseReview courseReview;
    private Student student;

    public StudentCourseRecord(LecturerCourseRecord lecturerCourseRecord) {
        this.lecturerCourseRecord = lecturerCourseRecord;
    }
}
