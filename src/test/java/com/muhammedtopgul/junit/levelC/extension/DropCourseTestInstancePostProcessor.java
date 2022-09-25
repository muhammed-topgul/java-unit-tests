package com.muhammedtopgul.junit.levelC.extension;

import com.muhammedtopgul.junit.levelC.StudentTestWithInstancePostProcessorTest;
import com.muhammedtopgul.model.Semester;
import com.muhammedtopgul.model.Student;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author muhammed-topgul
 * @since 25/09/2022 23:20
 */
public class DropCourseTestInstancePostProcessor implements TestInstancePostProcessor {
    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
        StudentTestWithInstancePostProcessorTest dropCourse = (StudentTestWithInstancePostProcessorTest) testInstance;
        dropCourse.student001 = new Student("1", "Muhammed", "Topgul");
        dropCourse.addDropPeriodOpenSemester = addDropPeriodOpenSemester();
        dropCourse.addDropPeriodClosedSemester = addDropPeriodClosedSemester();
        dropCourse.notActiveSemester = notActiveSemester();
    }

    private Semester addDropPeriodOpenSemester() {
        final Semester activeSemester = new Semester();
        final LocalDate semesterDate = LocalDate.of(activeSemester.getYear(), activeSemester.getTerm().getStartMonth(), 1);
        final LocalDate now = LocalDate.now();
        activeSemester.setAddDropPeriodInWeek(Long.valueOf(semesterDate.until(now, ChronoUnit.WEEKS) + 1).intValue());
        return activeSemester;
    }

    private Semester addDropPeriodClosedSemester() {
        final Semester activeSemester = new Semester();
        activeSemester.setAddDropPeriodInWeek(0);
        if (LocalDate.now().getDayOfMonth() == 1) {
            activeSemester.setAddDropPeriodInWeek(-1);
        }
        return activeSemester;
    }

    private Semester notActiveSemester() {
        final Semester activeSemester = new Semester();
        return new Semester(LocalDate.of(activeSemester.getYear() - 1, 1, 1));
    }
}
