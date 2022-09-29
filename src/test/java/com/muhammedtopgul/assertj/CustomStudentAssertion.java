package com.muhammedtopgul.assertj;

import com.muhammedtopgul.model.Student;
import org.assertj.core.api.AbstractAssert;

import java.util.Objects;

/**
 * @author muhammed-topgul
 * @since 29/09/2022 22:27
 */
public class CustomStudentAssertion extends AbstractAssert<CustomStudentAssertion, Student> {
    public CustomStudentAssertion(Student student) {
        super(student, CustomStudentAssertion.class);
    }

    public static CustomStudentAssertion assertThat(Student actual) {
        return new CustomStudentAssertion(actual);
    }

    public CustomStudentAssertion hasName(String name) {
        isNotNull();
        if (!Objects.equals(name, actual.getName())) {
            failWithMessage("Expected student's name %s but was found %s.", name, actual.getName());
        }
        return this;
    }
}
