package org.venuspj.util.beans.converter;

import java.time.LocalTime;
import java.time.MonthDay;

public class MonthDayConverter implements Converter<MonthDay> {

    private final String pattern;

    public MonthDayConverter(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean isTarget(Class clazz) {
        return clazz == MonthDay.class;

    }

    @Override
    public String getAsString(MonthDay value) {
        return null;
    }

    @Override
    public MonthDay getAsObject(String value) {
        return null;
    }
}
