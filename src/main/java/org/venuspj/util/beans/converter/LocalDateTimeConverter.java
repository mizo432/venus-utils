package org.venuspj.util.beans.converter;

import java.time.LocalDateTime;

public class LocalDateTimeConverter implements Converter<LocalDateTime> {

    private final String pattern;

    public LocalDateTimeConverter(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean isTarget(Class clazz) {
        return clazz == LocalDateTime.class;

    }

    @Override
    public String getAsString(LocalDateTime value) {
        return null;
    }

    @Override
    public LocalDateTime getAsObject(String value) {
        return null;
    }
}
