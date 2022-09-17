package com.muhammedtopgul.model;

import com.muhammedtopgul.enumeration.CourseType;
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
public class Course {
    private String code;
    private String name;
    private String description;
    private CourseType courseType;
    private int credit;
    private Department department;

    public Course(String code) {
        this.code = code;
    }

    public Course(int code) {
        this(String.valueOf(code));
    }

    public Course(String code, CourseType courseType) {
        this.code = code;
        this.courseType = courseType;
    }

    public static Course createNewCourse(String courseCode) {
        return new Course(courseCode);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof final Course course2)) {
            return false;
        }
        return course2.getCode().equals(this.getCode());
    }

    @Override
    public int hashCode() {
        return 31 * this.getCode().hashCode();
    }

    @Override
    public String toString() {
        return code + "-" + name;
    }
}
