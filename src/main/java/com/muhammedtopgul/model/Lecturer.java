package com.muhammedtopgul.model;

import com.muhammedtopgul.exception.NotActiveSemesterException;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author muhammed-topgul
 * @since 11/09/2022 01:51
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lecturer {
    private String id;
    private String name;
    private String surname;
    private String title;
    private Set<LecturerCourseRecord> lecturerCourseRecords = new HashSet<>();
    private Department department;

    public void addLecturerCourseRecord(LecturerCourseRecord lecturerCourseRecord) {
        if (lecturerCourseRecord.getCourse() == null) {
            throw new IllegalArgumentException("Can't add a null course to lecturer");
        }

        if (!lecturerCourseRecord.getSemester().isActive()) {
            throw new NotActiveSemesterException(lecturerCourseRecord.getSemester().toString());
        }

        lecturerCourseRecord.setLecturer(this);
        this.lecturerCourseRecords.add(lecturerCourseRecord);
    }

    public LecturerCourseRecord lecturerCourseRecord(Course course, Semester semester) {
        return lecturerCourseRecords.stream()
                .filter(e -> e.getCourse().equals(course) && e.getSemester().equals(semester))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Can't find lecturer course record for course<%s> and semester<%s>", course, semester)));
    }

}
