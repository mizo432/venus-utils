package org.venuspj.util.convert;

import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public abstract class MonthDayConversions {

  public static String getPattern(Locale locale) {
    return null;

  }

  public static MonthDay toMonthDay(String value, String pattern) {
    if (value == null || value.isEmpty()) {
      return null;
    }
    return MonthDay.parse(value, DateTimeFormatter.ofPattern(pattern));
  }

}
