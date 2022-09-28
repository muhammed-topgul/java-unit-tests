package com.muhammedtopgul.assertj;

import com.muhammedtopgul.model.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author muhammed-topgul
 * @since 28/09/2022 23:31
 */
@DisplayName("AssertJ Student Tests")
public class StudentTest {
    @Test
    void createStudent() {
        final Student student = new Student("1", "Muhammed", "Topgul");

        assertThat(student.getName())
                .as("Student's name %s", student.getName())
                .doesNotContainOnlyWhitespaces()
                .isNotEmpty()
                .isNotBlank()
                .isEqualTo("Muhammed")
                .isEqualToIgnoringCase("muhammed")
                .isSameAs("Muhammed")
                .isIn("Muhammed", "John", "Sam")
                .isNotIn("James", "Brown")
                .startsWith("Mu")
                .doesNotContain("J")
                .contains("ham")
                .contains(List.of("u", "h", "d"))
                .hasSize(8)
                .matches("^M\\w{6}d$")
        ;
    }
}
