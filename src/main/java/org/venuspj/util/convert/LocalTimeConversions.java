package org.venuspj.util.convert;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public abstract class LocalTimeConversions {

  public static String getPattern(Locale aDefault) {
    return null;

  }

  public static LocalTime toLocalTime(String value, String pattern) {
    if (value == null || value.isEmpty()) {
      return null;
    }
    return LocalTime.parse(value, DateTimeFormatter.ofPattern(pattern));
  }
}
