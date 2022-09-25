package com.muhammedtopgul.junit.levelB.domain;

import com.muhammedtopgul.enumeration.CourseType;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

/**
 * @author muhammed-topgul
 * @since 18/09/2022 02:07
 */
public class CourseCodeAndTypeProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of("101", CourseType.MANDATORY),
                Arguments.of("102", CourseType.ELECTIVE),
                Arguments.of("103", CourseType.MANDATORY)
        );
    }
}
