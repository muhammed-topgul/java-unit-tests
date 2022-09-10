package com.muhammedtopgul.enumeration;

import java.math.BigDecimal;

/**
 * @author muhammed-topgul
 * @since 11/09/2022 02:10
 */

public enum Grade {
    A1(BigDecimal.valueOf(4)),
    A2(BigDecimal.valueOf(3.5)),
    B1(BigDecimal.valueOf(3)),
    B2(BigDecimal.valueOf(2.5)),
    C(BigDecimal.valueOf(2)),
    D(BigDecimal.valueOf(1.5)),
    E(BigDecimal.ONE),
    F(BigDecimal.ZERO);

    private BigDecimal gradeInNumber;

    Grade(BigDecimal gradeInNumber) {
        this.gradeInNumber = gradeInNumber;
    }

    public BigDecimal getGradeInNumber() {
        return gradeInNumber;
    }
}