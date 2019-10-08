package org.venuspj.util.beans.converter;

import java.time.MonthDay;
import java.time.YearMonth;

public class YearMonthConverter implements Converter<YearMonth> {

    private final String pattern;


    public YearMonthConverter(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean isTarget(Class clazz) {
        return clazz == YearMonth.class;
    }

    @Override
    public String getAsString(YearMonth value) {
        return null;
    }

    @Override
    public YearMonth getAsObject(String value) {
        return null;
    }
}
