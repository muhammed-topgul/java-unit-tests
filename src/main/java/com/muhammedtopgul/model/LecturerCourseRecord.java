package com.muhammedtopgul.model;

import lombok.*;

/**
 * @author muhammed-topgul
 * @since 11/09/2022 01:51
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LecturerCourseRecord {
    private Course course;
    private int credit;
    private Semester semester;
    private Lecturer lecturer;

    public LecturerCourseRecord(Course course, Semester semester) {
        this.semester = semester;
        this.course = course;
    }
}
