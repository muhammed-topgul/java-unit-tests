package com.muhammedtopgul.model;

import com.muhammedtopgul.enumeration.Term;
import lombok.*;

import java.time.LocalDate;

import static com.muhammedtopgul.enumeration.Term.*;

/**
 * @author muhammed-topgul
 * @since 11/09/2022 01:51
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Semester {
    private final int year;
    private final Term term;
    private int addDropPeriodInWeek = 2;

    public Semester() {
        final LocalDate now = LocalDate.now();
        this.year = now.getYear();
        this.term = term(now.getMonthValue());
    }

    public Semester(LocalDate localDate) {
        this.year = localDate.getYear();
        this.term = term(localDate.getMonthValue());
    }

    private Term term(int monthValue) {
        if (monthValue >= FALL.startMonth || monthValue < SPRING.startMonth) {
            return FALL;
        } else if (monthValue >= SPRING.startMonth && monthValue < SUMMER.startMonth) {
            return SPRING;
        }

        return SUMMER;
    }

    public boolean isActive() {
        return this.equals(new Semester());
    }

    public boolean isAddDropAllowed() {
        if (!isActive()) {
            return false;
        }

        final LocalDate endOfAddDropPeriod = LocalDate.of(this.getYear(), this.getTerm().getStartMonth(), 1)
                .plusWeeks(addDropPeriodInWeek);

        return !LocalDate.now().isAfter(endOfAddDropPeriod);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof final Semester semester)) {
            return false;
        }

        return semester.getYear() == this.getYear() && semester.getTerm() == this.getTerm();
    }

    @Override
    public int hashCode() {
        return 31 * Integer.parseInt(getYear() + String.valueOf(getTerm().getStartMonth()));
    }

    @Override
    public String toString() {
        return this.getTerm().name() + " of " + this.getYear();
    }
}
