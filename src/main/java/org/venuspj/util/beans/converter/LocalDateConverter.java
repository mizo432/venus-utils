package org.venuspj.util.beans.converter;

import org.venuspj.util.beans.EmptyRuntimeException;
import org.venuspj.util.convert.DateConversions;
import org.venuspj.util.convert.LocalDateConversions;
import org.venuspj.util.convert.StringConversions;
import org.venuspj.util.strings2.Strings2;

import java.time.LocalDate;
import java.util.Date;

public class LocalDateConverter implements Converter<LocalDate> {

    private final String pattern;

    public LocalDateConverter(String pattern) {
        if (Strings2.isEmpty(pattern)) {
            throw new EmptyRuntimeException("pattern");
        }
        this.pattern = pattern;
    }

    @Override
    public String getAsString(LocalDate value) {
        return StringConversions.toString(value, pattern);

    }

    @Override
    public LocalDate getAsObject(String value) {
        if (Strings2.isEmpty(value)) {
            return null;
        }
        return LocalDateConversions
                .toLocalDate(value, pattern);

    }

    @Override
    public boolean isTarget(Class<?> clazz) {
        return clazz == LocalDate.class;
    }
}
