package com.muhammedtopgul.model;

import com.muhammedtopgul.enumeration.CourseRate;
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
public class CourseReview {
    private CourseRate courseRate;
    private String comments;
    private StudentCourseRecord studentCourseRecord;
}
