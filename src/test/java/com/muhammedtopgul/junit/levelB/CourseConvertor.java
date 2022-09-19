package com.muhammedtopgul.junit.levelB;

import com.muhammedtopgul.model.Course;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

/**
 * @author muhammed-topgul
 * @since 19/09/2022 12:07
 */
public class CourseConvertor extends SimpleArgumentConverter {
    @Override
    protected Object convert(Object o, Class<?> aClass) throws ArgumentConversionException {
        return new Course(((String) o));
    }
}
