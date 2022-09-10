package com.muhammedtopgul.enumeration;

/**
 * @author muhammed-topgul
 * @since 11/09/2022 02:02
 */
public enum Term {
    FALL(9), SPRING(2), SUMMER(6);

    public int startMonth;

    Term(int startMonth) {
        this.startMonth = startMonth;
    }

    public int getStartMonth() {
        return startMonth;
    }
}
