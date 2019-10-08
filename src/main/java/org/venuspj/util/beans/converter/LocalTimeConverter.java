package org.venuspj.util.beans.converter;

import java.time.LocalTime;

public class LocalTimeConverter implements Converter<LocalTime> {

    private final String pattern;

    public LocalTimeConverter(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean isTarget(Class clazz) {
        return clazz == LocalTime.class;
    }

    @Override
    public String getAsString(LocalTime value) {
        return null;
    }

    @Override
    public LocalTime getAsObject(String value) {
        return null;
    }
}
