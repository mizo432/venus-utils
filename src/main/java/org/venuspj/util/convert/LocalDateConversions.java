package org.venuspj.util.convert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public abstract class LocalDateConversions {

    public static String getPattern(Locale locale) {
        return "yyyy/MM/dd";

    }

    public static LocalDate toLocalDate(String value, String pattern) {
        return LocalDate.parse(value, DateTimeFormatter.ofPattern(pattern));

    }
}
