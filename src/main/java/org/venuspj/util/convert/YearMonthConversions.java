package org.venuspj.util.convert;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public abstract class YearMonthConversions {

  public static String getPattern(Locale aDefault) {
    return null;
  }

  public static YearMonth toYearMonth(String value, String pattern) {
    if (value == null || value.isEmpty()) {
      return null;
    }
    return YearMonth.parse(value, DateTimeFormatter.ofPattern(pattern));
  }

}
