package com.muhammedtopgul.model;

import com.muhammedtopgul.enumeration.Grade;
import lombok.*;

/**
 * @author muhammed-topgul
 * @since 30/09/2022 00:11
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transcript {
    private Course course;
    private int credit;
    private Semester semester;
    private Grade grade;
}
