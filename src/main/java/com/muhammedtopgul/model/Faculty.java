package com.muhammedtopgul.model;

import lombok.*;

import java.util.Set;

/**
 * @author muhammed-topgul
 * @since 11/09/2022 01:50
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Faculty {
    private String code;
    private String name;
    private Set<Department> departments;
}
